import {create} from "zustand"
import {persist} from "zustand/middleware";
import type {Role} from "../constants/roles.ts";
import type {UserSummary} from "../types/models/user.ts"; //Tự động lưu state vào LocalStorage

//Dữ liệu lưu trữ
export interface AuthState {
    role: Role | null;
    user: UserSummary | null;
    isAuthenticated: boolean;
    setAuthenticated: (role: Role, user: UserSummary) => void;
    logout: () => void;
}

//Tạo store lưu những dữ liệu theo interface trên
export const useAuthStore = create<AuthState>()(
    persist(
        (set) => ({
            // --- Giá trị ban đầu ---
            role: null,
            user: null,
            isAuthenticated: false,

            // --- Đăng nhập thành công ---
            setAuthenticated: (role, user) => set({
                role,
                user,
                isAuthenticated: true
            }),

            //Đăng xuất
            logout: () => set({role: null, user: null, isAuthenticated: false}),
        }),
        {
            name: 'fms-auth-storage', // Tên key lưu trong localStorage
            partialize: (state) => ({ 
                role: state.role, 
                user: state.user, 
                isAuthenticated: state.isAuthenticated 
            }),
        }
    )
);

