package com.elearning.controller;

import com.elearning.dto.response.ApiResponse;
import com.elearning.dto.response.UserResponse;
import com.elearning.entity.User;
import com.elearning.security.JwtTokenProvider;
import com.elearning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            String email = tokenProvider.getEmailFromToken(jwt);
            User user = userService.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return ResponseEntity.ok(UserResponse.fromEntity(user));
        } catch (Exception e) {
            return ResponseEntity
                    .status(401)
                    .body(ApiResponse.error("Invalid token"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(UserResponse.fromEntity(user));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(404)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(UserResponse.fromEntity(updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(404)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(404)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}