// src/components/auth/LoginLeftBanner.tsx
import ImgLogin from '../../../../assets/ImgLogin/Good team-bro.svg';
import ImgWeb from '../../../../assets/logo_web.png';
export function LoginLeftBanner() {
    return (
        <aside className="hidden lg:flex lg:w-[45%] flex-col justify-between px-12 py-12 bg-sidebar text-white relative overflow-hidden">

            {/* Logo */}
            <div className="flex items-center gap-3 relative z-10">
                <div className="w-13 h-13 rounded-xl flex items-center justify-center bg-primary-dark border border-primary-hover shadow-lg shadow-primary-shadow">
                    {/*<div className="w-5 h-5 rounded border-2 border-primary-accent" />*/}
                    <img
                        src={ImgWeb}
                        className="w-13 h-12 rounded border-2 "/>
                </div>
                <div>
                    <p className="font-bold text-xl tracking-widest text-white">FMS-ARTHUR</p>
                    <p className="text-[10px] tracking-[0.2em] uppercase text-muted">
                        CÔNG TY TNHH NỘI THẤT GỖ ARTHUR
                    </p>
                </div>
            </div>

            {/* === SVG === */}
            <div className="flex-1 flex items-center justify-center relative z-10 py-4">
                <img
                    src={ImgLogin}
                    alt="img login"
                    className="w-full max-w-[400px] h-auto drop-shadow-2xl"
                />
            </div>

            {/* === FOOTER === */}
            <div className="relative z-10 flex items-center justify-between">
                <p className="text-xs text-muted">
                    © 2026 FMS-Nhóm 4
                </p>
                <div className="flex gap-2 items-center">
                    <span className="w-2 h-2 rounded-full bg-primary-accent animate-pulse" />
                    <span className="text-[10px] text-primary-accent">Say "Yes" with AI</span>
                </div>
            </div>
        </aside>
    );
}