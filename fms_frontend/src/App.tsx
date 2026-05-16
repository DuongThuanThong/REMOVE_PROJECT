import LoginPage from "./pages/auth/Login/LoginPage.tsx";
import {Route, Routes} from "react-router-dom";

function App() {
  return (
    <Routes>
            {/* Public routes */}
        <Route path="/login" element={<LoginPage />} />
    </Routes>
  );
}

export default App;
