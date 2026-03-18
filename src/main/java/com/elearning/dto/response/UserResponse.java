package com.elearning.dto.response;

import com.elearning.entity.User;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private String role;
    private String avatarUrl;
    private String bio;

    public static UserResponse fromEntity(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole() != null ? user.getRole().toString() : null);
        response.setAvatarUrl(user.getAvatarUrl());
        response.setBio(user.getBio());
        return response;
    }
}