import {Button, Result} from "antd";
import type { Role } from "../../constants/roles";
import {useAuthStore} from "../../stores/authStores.ts";
import {Navigate} from "react-router-dom";


interface RequiredRoleProps {
    allowRoles: Role[],
    children: React.ReactNode,
}

export function RequiredRole({ allowRoles, children }: RequiredRoleProps) {
    const {isAuthenticated, role} = useAuthStore();

    //Đưa user ra trang login nếu chưa đăng nhập
    if (!isAuthenticated) return <Navigate to={"/login"} replace/>

    //Không thuộc quyền
    if (!role || !allowRoles.includes(role))
        return (
            <div className="flex items-center justify-center h-screen bg-slate-50">
                <Result
                    status="403"
                    title="403"
                    subTitle="Xin lỗi, bạn không có quyền truy cập vào trang này."
                    extra={
                        <Button type="primary" onClick={() => window.history.back()}>
                            Quay lại trang trước
                        </Button>
                    }
                />
            </div>
        );
    // --- Hợp lệ → Render nội dung ---
    return <>{children}</>;
}

