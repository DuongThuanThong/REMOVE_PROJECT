
import {
    Layout, Dropdown, Avatar,Breadcrumb
} from "antd";
import {SYSTEM_INFO} from "../../constants/configs.ts";

interface HeaderProps {
    collapsed: boolean; // Trạng thái đóng mở
    setCollapsed: (collapsed: boolean) => void;
    title:string;
}

export function Header ({
    collapsed, setCollapsed, title = SYSTEM_INFO.TITLE
}:HeaderProps){

}

