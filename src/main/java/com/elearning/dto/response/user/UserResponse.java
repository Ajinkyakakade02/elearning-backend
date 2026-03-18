package com.elearning.dto.response.user;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String email;
    private String fullName;
    private String role;
    private String avatarUrl;
    private String bio;
    private Boolean isActive;
}