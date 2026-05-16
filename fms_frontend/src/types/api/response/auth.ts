import type {Role} from "../../../constants/roles.ts";
import type {UserSummary} from "../../models/user.ts";

export interface LoginResponse{
    accessToken: string;
    refreshToken: string;
    roles: Role;
    user: UserSummary;
}

export interface RegisterResponse{
    accessToken: string;
    refreshToken: string;
    roles: Role;
    user: UserSummary;
}