import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';

// Component Layout tạm thời (sẽ tách ra sau)
const Layout = ({ children }: { children: React.ReactNode }) => (
  <div style={{ display: 'flex', height: '100vh', width: '100vw' }}>
    {/* Sidebar Placeholder */}
    <div style={{ width: 245, background: '#1a2e25', color: '#fff', padding: 20 }}>
      <h3>FMS SALE</h3>
    </div>
    
    {/* Main Content */}
    <div style={{ flex: 1, display: 'flex', flexDirection: 'column' }}>
      {/* TopBar Placeholder */}
      <div style={{ height: 56, background: '#fff', borderBottom: '1px solid #e8e6df' }}></div>
      {/* Content */}
      <div style={{ flex: 1, padding: 24, overflow: 'auto' }}>
        {children}
      </div>
    </div>
  </div>
);

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout><Navigate to="/customers" replace /></Layout>} />
      <Route path="/customers" element={<Layout><h2>Quản lý Khách Hàng (Đang xây dựng)</h2></Layout>} />
      <Route path="/bom" element={<Layout><h2>Sản phẩm mẫu & BOM (Đang xây dựng)</h2></Layout>} />
    </Routes>
  );
}

export default App;
