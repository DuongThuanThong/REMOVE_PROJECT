import {create} from "zustand"
import {persist} from "zustand/middleware/persist";
import type {Role} from "../constants/roles.ts";
import type {UserSummary} from "../types/models/user.ts"; //Tự động lưu state vào LocalStorage

//Dữ liệu lưu trữ
export interface AuthState {
    accessToken: string | null; //JWT token từ server (null là khi chưa đăng nhập)
    refreshToken: string | null;
    role: Role | null;
    user: UserSummary | null;
    isAuthenticated: boolean;
    setAuthenticated: (accessToken: string, refreshToken: string, role: Role, user: UserSummary) => void;
    updateToken: (accessTokenNew:string) => void;
    logout: () => void;
}

//Tạo store lưu những dữ liệu theo interface trên
export const useAuthStore = create<AuthState>()(
    persist(
        (set) => ({
            // --- Giá trị ban đầu ---
            accessToken: null,
            refreshToken: null,
            role: null,
            user: null,
            isAuthenticated: false,

            // --- Đăng nhập thành công ---
            setAuthenticated: (accessToken, refreshToken, role, user) => set({
                accessToken,
                refreshToken,
                role,
                user,
                isAuthenticated: true
            }),

            //Cấp lại accessToken
            updateToken: (accessTokenNew) => set ({
                   accessToken: accessTokenNew
            }),

            //Đăng xuất
            logout: () => set({accessToken: null, refreshToken: null, role: null, user: null, isAuthenticated: false}),
        }),
        {
            name: 'fms-auth-storage', // Tên key lưu trong localStorage
        }
    )
);

