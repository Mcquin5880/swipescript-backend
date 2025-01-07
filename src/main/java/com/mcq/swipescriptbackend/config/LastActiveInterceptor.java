package com.mcq.swipescriptbackend.config;

import com.mcq.swipescriptbackend.repository.AppUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class LastActiveInterceptor implements HandlerInterceptor {

    private final AppUserRepository appUserRepository;

    public LastActiveInterceptor(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();

            appUserRepository.findByUsername(username).ifPresent(user -> {
                user.setLastActive(LocalDateTime.now());
                appUserRepository.save(user);
            });
        }
        return true;
    }
}
