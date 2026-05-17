/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        // Màu chủ đạo
        primary: {
          DEFAULT: '#2d6a4f', // Màu xanh đậm (cho button, link)
          hover: '#40916c',
          dark: '#1a2e25',
          active: '#1a2e25',        // Xanh lúc click
          accent: '#4ade80',        // Xanh lá sáng neon (cho thông báo/trạng thái)
          shadow : '#0a1f14',
        },
        // Màu nền
        page: '#f5f3ee',     // Nền tổng thể
        surface: '#ffffff',  // Nền card, modal
        sidebar: '#1a2e25',  // Nền sidebar
        rowHover: '#fafaf7', // Nền hover dòng table
        // Màu chữ
        title: '#1a2e25',  // Tiêu đề
        text: '#2c2c2a',     // Chữ nội dung
        text_secondary: '#5f5e5a',// Chữ phụ
        muted: '#888780',    // Chữ mờ, placeholder
        // Màu viền
        border: '#e8e6df',
        borderLight: '#f0ede6',
        divider: '#f0ede6',
        // Trạng thái
        success: {
          DEFAULT: '#166534',
          bg: '#dcfce7',
          border: '#bbf7d0',
        },
        error: {
          DEFAULT: '#e24b4a',
        },
        info: {
          DEFAULT: '#0369a1',
          bg: '#e0f2fe',
        },
        warning: {
          DEFAULT: '#92400e',
          bg: '#fef9c3',
        }
      },
      fontFamily: {
        sans: ['"Be Vietnam Pro"', 'Segoe UI', 'sans-serif'],
      },
      boxShadow: {
        'card': '0 2px 8px rgba(0,0,0,0.05)',
        'modal': '-4px 0 24px rgba(0,0,0,0.12)',
      }
      ,
      // 3. Tinh chỉnh Cỡ chữ (Typography Scale)
      fontSize: {
        // [Cỡ chữ, Chiều cao dòng (Line-height)]
        'fms-xs': ['11px', '16px'],    // Dùng cho label, tag nhỏ
        'fms-sm': ['13px', '20px'],    // Dùng cho mô tả phụ
        'fms-base': ['14px', '22px'],  // Chuẩn đọc văn bản của AntD
        'fms-lg': ['16px', '24px'],    // Tiêu đề form/card
        'fms-xl': ['20px', '28px'],    // Tiêu đề trang (Heading 2)
        'fms-2xl': ['28px', '36px'],   // Tiêu đề tổng (Heading 1)
      }
    },
  },
  corePlugins: {
    // Tắt bộ reset CSS mặc định của Tailwind để không làm vỡ giao diện nút/bảng của Ant Design
    preflight: false,
  },
  plugins: [],
}
