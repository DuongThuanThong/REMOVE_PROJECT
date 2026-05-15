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
        },
        // Màu nền
        page: '#f5f3ee',     // Nền tổng thể
        surface: '#ffffff',  // Nền card, modal
        sidebar: '#1a2e25',  // Nền sidebar
        rowHover: '#fafaf7', // Nền hover dòng table
        // Màu chữ
        heading: '#1a2e25',  // Tiêu đề
        body: '#2c2c2a',     // Chữ nội dung
        secondary: '#5f5e5a',// Chữ phụ
        muted: '#888780',    // Chữ mờ, placeholder
        // Màu viền
        border: '#e8e6df',
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
    },
  },
  plugins: [],
}
