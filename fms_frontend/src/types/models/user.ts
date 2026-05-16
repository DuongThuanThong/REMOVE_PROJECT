import type {Role} from "../../constants/roles.ts";

export interface UserSummary {
    id: number;
    username: string;
    email:string;
    phoneNumeber: string;
    roles: Role;
    isActive: boolean;
}