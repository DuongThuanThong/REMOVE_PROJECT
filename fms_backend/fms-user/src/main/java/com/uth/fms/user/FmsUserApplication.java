package com.uth.fms.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.uth.fms"}) //Khai báo chỉ định các pakage cần được quét tìm Bean
public class FmsUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmsUserApplication.class, args);
    }

}
