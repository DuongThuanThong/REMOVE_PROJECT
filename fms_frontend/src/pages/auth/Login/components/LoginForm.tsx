import type {LoginRequest} from "../../../../types/api/request/auth.ts";
import {useState} from "react";
import {useAuthStore} from "../../../../stores/authStores.ts";
import {authService} from "../../../../services/authService.ts";
import {Button, Checkbox, Divider, Form, Input, message} from "antd";
import {useLocation, useNavigate} from "react-router-dom";
import {UserOutlined} from "@ant-design/icons";

interface LoginFormValues extends LoginRequest {
    rememberMe: boolean;
}

export function LoginForm() {
    const [loading, setLoading] = useState(false);
    const location = useLocation();
    const navigate = useNavigate();
    const setAuthenticated = useAuthStore((state) => state.setAuthenticated);

    const handleSubmit = async (values: LoginFormValues) => {
        setLoading(true);
        try {
            //Đóng gói dữ liệu
            const payload: LoginRequest = {
                email: values.email,
                password: values.password
            };
            // Nếu tick "Lưu đăng nhập", lưu refreshToken vào LocalStorage

            //Gọi api từ lớp services
            const responseBody = await authService.login(payload);

            const {role, user} = responseBody.data;

            setAuthenticated(role, user);
            message.success('Đăng nhập hệ thống thành công!');

            const queryParams = new URLSearchParams(location.search);
            const redirectUrl = queryParams.get('redirect') || '/';
            navigate(redirectUrl, {replace: true});

        } catch (error: any) {
            const errorMsg = error.response?.data?.message || "Đăng nhập thất bại. Vui lòng kiểm tra lại tài khoản!";
            message.error(errorMsg);
        } finally {
            setLoading(false);
        }
    };
    return (
        <div className="w-full max-w-[420px]">
            {/* Header */}
            <div className="mb-8">
                <h2 className="text-4xl font-bold mb-2 text-title">Đăng nhập</h2>
                <p className="text-fms-sm text-muted">
                    Nhập thông tin tài khoản nội bộ để tiếp tục
                </p>
            </div>

            {/* Form đăng nhập */}
            <Form<LoginFormValues> layout="vertical" onFinish={handleSubmit}>
                {/* Input Email */}
                <Form.Item
                    name="email"
                    label={
                        <span className="text-fms-xs font-semibold tracking-wider text-fms-text uppercase">
            EMAIL ĐĂNG NHẬP
            </span>
                    }
                    rules={[{required: true, message: "Vui lòng điền email đăng nhập"}]}
                >
                    <Input
                        placeholder="example@gmail.com"
                        prefix={<UserOutlined className="text-muted mr-2"/>}
                        size="large"
                    />
                </Form.Item>

                {/* Input Password */}
                <Form.Item
                    name="password"
                    label={
                        <span className="text-fms-xs font-semibold tracking-wider text-fms-text uppercase">
              MẬT KHẨU
            </span>
                    }
                    rules={[{required: true, message: "Vui lòng điền mật khẩu"}]}
                >
                    <Input.Password
                        placeholder="••••••••••"
                        size="large"
                    />
                </Form.Item>

                {/* Checkbox Remember + Quên mật khẩu */}
                <div className="flex items-center justify-between mb-5 mt-2">
                    <Form.Item name="remember" valuePropName="checked" noStyle>
                        <Checkbox>
              <span className="text-fms-sm text-fms-text select-none">
                Lưu đăng nhập
              </span>
                        </Checkbox>
                    </Form.Item>
                    <a
                        href="#forgot"
                        className="text-fms-sm font-medium text-fms-primary hover:text-fms-hover transition-colors"
                    >
                        Quên mật khẩu?
                    </a>
                </div>

                {/* Button Submit */}
                <Form.Item>
                    <Button
                        type="primary"
                        htmlType="submit"
                        size="large"
                        loading={loading}
                        block
                        className="font-bold text-fms-sm tracking-wide !bg-fms-primary !border-fms-primary hover:!bg-fms-hover hover:!border-fms-hover"
                    >
                        ĐĂNG NHẬP HỆ THỐNG
                    </Button>
                </Form.Item>
            </Form>

            {/* Divider */}
            <Divider className="!my-5 !border-fms-border"/>

            {/* Footer */}
            <p className="text-center text-fms-xs mt-6 text-muted leading-relaxed">
                Hệ thống quản lý nội bộ bảo mật. Gặp sự cố truy cập?
                <br/>
                Vui lòng liên hệ{" "}
                <span className="text-fms-primary font-medium cursor-pointer hover:underline ">
          Bộ phận IT
        </span>{" "}
                để được trợ giúp.
            </p>
        </div>
    );
}
