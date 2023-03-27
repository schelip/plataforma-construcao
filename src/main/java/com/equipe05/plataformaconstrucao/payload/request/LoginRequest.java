package com.equipe05.plataformaconstrucao.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    private @NotBlank String username;
    private @NotBlank String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
