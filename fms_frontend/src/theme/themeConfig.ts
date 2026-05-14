import type { ThemeConfig } from 'antd';

const themeConfig: ThemeConfig = {
  token: {
    // Kiểu chữ
    fontFamily: "'Be Vietnam Pro', 'Segoe UI', sans-serif",
    
    // Màu sắc chủ đạo (Primary)
    colorPrimary: '#2d6a4f', // Màu xanh đậm
    colorPrimaryHover: '#40916c',
    colorPrimaryActive: '#1a2e25',
    
    // Màu nền (Backgrounds)
    colorBgLayout: '#f5f3ee', // Nền tổng thể
    colorBgContainer: '#ffffff', // Nền card, modal
    colorBgElevated: '#ffffff', // Nền dropdown
    
    // Màu chữ (Typography)
    colorTextHeading: '#1a2e25', // Chữ tiêu đề
    colorText: '#2c2c2a', // Chữ nội dung (body)
    colorTextSecondary: '#5f5e5a', // Chữ mô tả phụ
    colorTextQuaternary: '#888780', // Chữ mờ, placeholder
    
    // Màu viền và đường kẻ
    colorBorder: '#e8e6df',
    colorBorderSecondary: '#f0ede6',
    
    // Màu trạng thái (Semantic Colors)
    colorSuccess: '#40916c',
    colorError: '#e24b4a',
    colorWarning: '#eab308',
    colorInfo: '#0369a1',

    // Kích thước, bo góc
    borderRadius: 8,
    borderRadiusLG: 12, // Cho Modal, Card
    borderRadiusSM: 6,  // Cho Badge, Tag
  },
  components: {
    Button: {
      borderRadius: 8,
      controlHeight: 38,
      paddingInline: 18,
      fontWeight: 600,
    },
    Table: {
      headerBg: '#ffffff',
      headerColor: '#888780',
      rowHoverBg: '#fafaf7',
      borderColor: '#f0ede6',
      headerBorderRadius: 0,
    },
    Input: {
      colorBgContainer: '#f9f8f5',
      colorBorder: '#e8e6df',
      borderRadius: 8,
      controlHeight: 40,
    },
    Select: {
      colorBgContainer: '#f9f8f5',
      colorBorder: '#e8e6df',
      borderRadius: 8,
      controlHeight: 40,
    },
    Menu: {
      itemBg: 'transparent',
      itemColor: 'rgba(255,255,255,0.6)',
      itemHoverBg: 'rgba(64,145,108,0.15)',
      itemHoverColor: '#7ee8a2',
      itemSelectedBg: 'rgba(64,145,108,0.25)',
      itemSelectedColor: '#7ee8a2',
      darkItemBg: '#1a2e25',
    }
  }
};

export default themeConfig;
