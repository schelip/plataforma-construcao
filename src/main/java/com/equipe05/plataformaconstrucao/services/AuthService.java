package com.equipe05.plataformaconstrucao.services;

import com.equipe05.plataformaconstrucao.model.ERole;
import com.equipe05.plataformaconstrucao.security.services.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class AuthService {
    public static UserDetailsImpl getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof String && authentication.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("No user logged in");
        }

        return (UserDetailsImpl) authentication.getPrincipal();
    }

    public static boolean isPrincipalModerator() {
        UserDetailsImpl userDetails = getPrincipal();

        return userDetails.getAuthorities().stream()
                .anyMatch(o -> Objects.equals(o.getAuthority(), ERole.ROLE_MODERATOR.name()));
    }

    public static boolean isPrincipalAdmin() {
        UserDetailsImpl userDetails = getPrincipal();

        return userDetails.getAuthorities().stream()
                .anyMatch(o -> Objects.equals(o.getAuthority(), ERole.ROLE_ADMIN.name()));
    }
}
