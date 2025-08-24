package com.cohost.dto;

import org.coHost.co_Host.dto.UserDto;

public class AuthResponse {
    private String token;
    private UserDto user; // New field to hold user details

    // Updated constructor to accept both the token and the user DTO
    public AuthResponse(String token, UserDto user) {
        this.token = token;
        this.user = user;
    }

    // Getters and Setters for both fields
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}