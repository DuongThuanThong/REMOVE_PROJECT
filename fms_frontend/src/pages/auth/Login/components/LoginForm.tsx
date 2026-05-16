import type {LoginRequest} from "../../../../types/api/request/auth.ts";
import {useState} from "react";
import {useAuthStore} from "../../../../stores/authStores.ts";

interface LoginFormValues extends LoginRequest {
    rememberMe: boolean;
}
export function LoginForm() {
    const [loading, setLoading] = useState(false);
    const setAuthenticated = useAuthStore((state) => state.setAuthenticated);

    const handleSubmit = async (values: LoginRequest) => {
        setLoading(true);
        try{
            const payload: LoginRequest = {
                email: values.email,
                password: values.password
            };
            const
        }catch(err){

        }
    }

}