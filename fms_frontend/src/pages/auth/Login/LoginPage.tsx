import {LoginLeftBanner} from "./components/LoginLeftBanner.tsx";
import {LoginForm} from "./components/LoginForm.tsx";

export default function LoginPage() {
    return (
        <div className="flex min-h-screen w-full flex-col md:flex-row font-sans antialiased">
            {/* Panel trái: Banner */}
            <LoginLeftBanner />

            {/* Panel phải: Form */}
            <main className="flex-1 flex items-center justify-center px-6 py-12 bg-fms-page">
                <LoginForm />
            </main>
        </div>
    );
}