package com.uth.fms.common.security;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface CustomUserDetailService extends UserDetailsService {
    UserDetails loadUserByUserId(Long id);
}
