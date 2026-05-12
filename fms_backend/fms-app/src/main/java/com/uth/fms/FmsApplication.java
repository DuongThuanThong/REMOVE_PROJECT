package com.uth.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Điểm khởi chạy duy nhất của toàn bộ hệ thống FMS (Modular Monolith).
 * scanBasePackages = "com.uth.fms" giúp Spring Boot tự động quét
 * và đăng ký tất cả Bean từ mọi module (common, user, order, ...).
 */
@SpringBootApplication(scanBasePackages = "com.uth.fms")
public class FmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmsApplication.class, args);
    }
}
