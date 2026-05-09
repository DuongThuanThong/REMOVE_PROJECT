package com.uth.fms.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration //Tạo bean
@EnableJpaAuditing (auditorAwareRef = "auditorProvider")
public class JpaConfig {

}
