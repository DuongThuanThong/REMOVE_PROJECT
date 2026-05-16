import axios, {type AxiosError, type AxiosResponse, type InternalAxiosRequestConfig} from 'axios';
import {useAuthStore} from "../stores/authStores.ts";


/**
 * Tạo một phiên bản custome axios để :
 * - Mọi api call đều dùng thằng này để tự động gắn token, và refresh khi lỗi 401
 */
const axiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080/api/v1',
    timeout: 10000, //Nếu chờ tới 10 giây k nhận được request báo lỗi
    headers: {
        'Content-Type': 'application/json',
    }
});

/**
 * INTERCEPTOR_REQUEST
 * Mỗi lần gọi API, tự động lấy token từ Zustand
 * và nhét vào header Authorization: Bearer <token>
 */
axiosInstance.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
        const token = useAuthStore.getState().accessToken;
        // Nếu có token -> gắn vào header
        if (token && config.headers) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error: AxiosError) => Promise.reject(error)
);


/**
 * isRefreshing: Cờ khóa
 * - true = đang gọi API refresh, các API khác phải đợi
 * - false = bình thường
 */
let isRefreshing = false;

/**
 * failedQueue: Hàng đợi các API bị "kẹt"
 * Khi đang refresh token, nếu có API khác gọi → bỏ vào đây chờ
 */
let failedQueue: Array<{
    resolve: (token: string) => void;   // Hàm "thông báo thành công"
    reject: (error: any) => void;        // Hàm "thông báo thất bại"
}> = [];

/**
 * Xử lý hàng đợi: Sau khi refresh xong, thông báo cho tất cả API đang chờ
 * @param error - Nếu có lỗi → reject tất cả
 * @param token - Token mới (nếu thành công)
 */
const processQueue = (error: any, token: string | null = null) => {
    failedQueue.forEach((prom) => {
        if (error) {
            prom.reject(error);           // Thất bại → báo lỗi
        } else {
            prom.resolve(token as string); // Thành công → trả token mới
        }
    });
    failedQueue = [];  // Dọn hàng đợi
};


/**
 * INTERCEPTOR_RESPONSE
 */
axiosInstance.interceptors.response.use(
    // --- THÀNH CÔNG: Trả response như bình thường ---
    (response: AxiosResponse) => response,

    // --- THẤT BẠI: Xử lý lỗi ---
    async (error: AxiosError) => {
        // Lấy config của API bị lỗi, thêm cờ _retry để tránh lặp vô hạn
        const originalRequest = error.config as InternalAxiosRequestConfig & { _retry?: boolean };
        // ==========================================
        // BẮT LỖI 401 (Token hết hạn / Không hợp lệ)
        // ==========================================
        if (error.response?.status === 401 && !originalRequest._retry) {

            // --- Trường hợp đặc biệt: Chính API refresh cũng bị 401 ---
            // → Refresh token cũng hết hạn → Bắt đăng nhập lại
            if (originalRequest.url?.includes('/auth/refresh')) {
                useAuthStore.getState().logout();      // Xóa hết state
                const currentUrl = window.location.pathname + window.location.search;
                window.location.href = `/login?redirect=${encodeURIComponent(currentUrl)}`;
                return Promise.reject(error);
            }
            // Đánh dấu API này đã thử retry (tránh lặp vô hạn)
            originalRequest._retry = true;

            // ==========================================
            // TRƯỜNG HỢP 1: Đang có API khác refresh rồi
            // ==========================================
            if (isRefreshing) {
                // Đưa API này vào hàng đợi, đợi token mới
                return new Promise(function (resolve, reject) {
                    failedQueue.push({ resolve, reject });
                })
                    .then((token) => {
                        // Khi có token mới → gắn vào header và gọi lại API cũ
                        if (originalRequest.headers) {
                            originalRequest.headers.Authorization = `Bearer ${token}`;
                        }
                        return axiosInstance(originalRequest);
                    })
                    .catch((err) => Promise.reject(err));  // Refresh thất bại → báo lỗi
            }


            // ==========================================
            // TRƯỜNG HỢP 2: Chưa ai refresh → Mình đi refresh
            // ==========================================
            isRefreshing = true;  // Khóa cửa, các API sau phải đợi

            try {
                // Lấy refresh token từ store
                const refreshToken = useAuthStore.getState().refreshToken;
                if (!refreshToken) throw new Error('Không tìm thấy Refresh Token');

                // Gọi API xin token mới (dùng axios gốc, không dùng axiosInstance để tránh vòng lặp interceptor)
                const res = await axios.post(`${axiosInstance.defaults.baseURL}/auth/refresh`, {
                    refreshToken: refreshToken,
                });

                // Trích xuất token mới (tùy cấu trúc response của Backend)
                const newAccessToken = res.data.data?.accessToken || res.data.accessToken;

                // Lưu token mới vào Zustand store
                useAuthStore.getState().updateToken(newAccessToken);

                // Thông báo cho các API đang chờ trong hàng đợi
                processQueue(null, newAccessToken);

                // Gắn token mới vào API hiện tại và gọi lại
                if (originalRequest.headers) {
                    originalRequest.headers.Authorization = `Bearer ${newAccessToken}`;
                }
                return axiosInstance(originalRequest);  // Retry API cũ

            } catch (refreshError) {
                // Refresh thất bại → Xóa sạch, bắt đăng nhập lại
                processQueue(refreshError, null);        // Báo lỗi cho hàng đợi
                useAuthStore.getState().logout();
                window.location.href = '/login';
                return Promise.reject(refreshError);

            } finally {
                isRefreshing = false;  // Mở khóa cờ, cho phép refresh lần sau
            }
        }
        // ==========================================
        // CÁC LỖI KHÁC (403, 404, 500...)
        // ==========================================
        // Không phải 401 → ném ra ngoài cho component tự xử lý
        return Promise.reject(error);
    }
);

export default axiosInstance;
