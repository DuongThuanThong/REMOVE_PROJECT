import axios, {type AxiosError, type AxiosResponse, type InternalAxiosRequestConfig} from 'axios';
import {useAuthStore} from "../stores/authStores.ts";

/**
 * Tạo một phiên bản custome axios để :
 * - Mọi api call đều dùng thằng này để tự động gắn cookie (withCredentials)
 * - Tự động gọi API refresh khi lỗi 401
 */
const axiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080/api/v1',
    timeout: 10000, //Nếu chờ tới 10 giây k nhận được request báo lỗi
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true // Cho phép gửi và nhận Cookie
});

// INTERCEPTOR REQUEST
axiosInstance.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
        return config;
    },
    (error: AxiosError) => Promise.reject(error)
);

let isRefreshing = false;
let failedQueue: Array<{
    resolve: () => void;
    reject: (error: any) => void;
}> = [];

const processQueue = (error: any) => {
    failedQueue.forEach((prom) => {
        if (error) {
            prom.reject(error);
        } else {
            prom.resolve(); 
        }
    });
    failedQueue = []; 
};

/**
 * INTERCEPTOR RESPONSE
 */
axiosInstance.interceptors.response.use(
    (response: AxiosResponse) => response,
    async (error: AxiosError) => {
        const originalRequest = error.config as InternalAxiosRequestConfig & { _retry?: boolean };

        // BẮT LỖI 401 (Token hết hạn / Không hợp lệ)
        if (error.response?.status === 401 && !originalRequest._retry) {

            // Nếu chính API refresh bị 401 -> Refresh token cũng hết hạn -> Bắt đăng nhập lại
            if (originalRequest.url?.includes('/auth/refresh')) {
                useAuthStore.getState().logout();
                const currentUrl = window.location.pathname + window.location.search;
                window.location.href = `/login?redirect=${encodeURIComponent(currentUrl)}`;
                return Promise.reject(error);
            }
            
            originalRequest._retry = true;

            if (isRefreshing) {
                return new Promise<void>(function (resolve, reject) {
                    failedQueue.push({ resolve: () => resolve(), reject });
                })
                .then(() => {
                    return axiosInstance(originalRequest);
                })
                .catch((err) => Promise.reject(err));
            }

            isRefreshing = true;

            try {
                // Gọi API xin token mới (backend sẽ tự đọc refreshToken từ Cookie và trả về accessToken Cookie mới)
                await axios.post(`${axiosInstance.defaults.baseURL}/auth/refresh`, {}, {
                    withCredentials: true // Phải gửi kèm cookie
                });

                processQueue(null);

                // Gọi lại API cũ, cookie mới sẽ tự động được gửi đi
                return axiosInstance(originalRequest);

            } catch (refreshError) {
                // Refresh thất bại
                processQueue(refreshError);
                useAuthStore.getState().logout();
                window.location.href = '/login';
                return Promise.reject(refreshError);
            } finally {
                isRefreshing = false;
            }
        }
        return Promise.reject(error);
    }
);

export default axiosInstance;
