import type {LoginRequest} from "../types/api/request/auth.ts";
import axiosInstance from "../api/axiosInstance.ts";
import type {LoginResponse} from "../types/api/response/auth.ts";

export const authService = {
    /**
     * API Đăng nhập hệ thống FMS
     * @param payload Chứa thông tin tài khoản gồm username và password
     */
    login: async (payload: LoginRequest) => {
        const response = await axiosInstance.post<{ data: LoginResponse }>(
            '/auth/login',
            payload
        );
        return response.data;
    },

    /**
     * API Đăng xuất hệ thống (Vô hiệu hóa token trên Redis)
     */
    logout: async () => {
        const response = await axiosInstance.post('/auth/logout');
        return response.data;
    }
}