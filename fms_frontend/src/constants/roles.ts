export const ROLES = {
    ADMIN: 'ADMIN',
    DIRECTOR: 'DIRECTOR',
    SALE: 'SALE',
    PRODUCTION_MANAGER: 'PRODUCTION_MANAGER',
    DESIGNER: 'DESIGNER',
    TEAM_LEADER: 'TEAM_LEADER',
    WAREHOUSE: 'WAREHOUSE',
} as const;

//Type từ Object trên
export type Role = (typeof ROLES)[keyof typeof ROLES];

// Label hiển thị cho từng role (Dùng cho UI, Dropdown, Menu)
export const ROLE_LABELS: Record<Role, string> = {
    [ROLES.ADMIN]: 'Quản trị viên',
    [ROLES.DIRECTOR]: 'Giám đốc',
    [ROLES.SALE]: 'Nhân viên kinh doanh',
    [ROLES.PRODUCTION_MANAGER]: 'Trưởng phòng Sản xuất',
    [ROLES.DESIGNER]: 'Thợ vẽ / Thiết kế',
    [ROLES.TEAM_LEADER]: 'Tổ trưởng',
    [ROLES.WAREHOUSE]: 'Thủ kho',
};


// Mảng options dành cho select box của Ant Design
export const ROLE_OPTIONS = Object.entries(ROLE_LABELS).map(([value, label]) => ({
    value,
    label,
}));