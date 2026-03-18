package com.elearning.controller;

import com.elearning.entity.Course;
import com.elearning.entity.User;
import com.elearning.entity.Wishlist;
import com.elearning.service.CourseService;
import com.elearning.service.UserService;
import com.elearning.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class WishlistController {

    private final WishlistService wishlistService;
    private final UserService userService;
    private final CourseService courseService;

    // Get current authenticated user
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Get user's wishlist
    @GetMapping
    public ResponseEntity<?> getUserWishlist() {
        User user = getCurrentUser();
        List<Wishlist> wishlistItems = wishlistService.getUserWishlist(user.getId());

        List<Map<String, Object>> response = wishlistItems.stream().map(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("courseId", item.getCourse().getId());
            map.put("courseTitle", item.getCourse().getTitle());
            map.put("instructorName", item.getCourse().getInstructorName());
            map.put("thumbnailUrl", item.getCourse().getThumbnailUrl());
            map.put("addedAt", item.getCreatedAt());
            // Price removed - courses are now free
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    // Add course to wishlist
    @PostMapping("/add/{courseId}")
    public ResponseEntity<?> addToWishlist(@PathVariable Long courseId) {
        User user = getCurrentUser();
        Course course = courseService.getCourseById(courseId);

        Wishlist wishlistItem = wishlistService.addToWishlist(user.getId(), courseId);

        Map<String, Object> response = new HashMap<>();
        response.put("id", wishlistItem.getId());
        response.put("courseId", course.getId());
        response.put("courseTitle", course.getTitle());
        response.put("instructorName", course.getInstructorName());
        response.put("thumbnailUrl", course.getThumbnailUrl());
        response.put("addedAt", wishlistItem.getCreatedAt());
        response.put("message", "Course added to wishlist successfully");

        return ResponseEntity.ok(response);
    }

    // Remove course from wishlist
    @DeleteMapping("/remove/{courseId}")
    public ResponseEntity<?> removeFromWishlist(@PathVariable Long courseId) {
        User user = getCurrentUser();
        wishlistService.removeFromWishlist(user.getId(), courseId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Course removed from wishlist successfully");

        return ResponseEntity.ok(response);
    }

    // Check if course is in wishlist
    @GetMapping("/check/{courseId}")
    public ResponseEntity<?> checkWishlist(@PathVariable Long courseId) {
        User user = getCurrentUser();
        boolean isInWishlist = wishlistService.isInWishlist(user.getId(), courseId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("isInWishlist", isInWishlist);

        return ResponseEntity.ok(response);
    }

    // Clear entire wishlist
    @DeleteMapping("/clear")
    public ResponseEntity<?> clearWishlist() {
        User user = getCurrentUser();
        wishlistService.clearWishlist(user.getId());

        Map<String, String> response = new HashMap<>();
        response.put("message", "Wishlist cleared successfully");

        return ResponseEntity.ok(response);
    }

    // Get wishlist count
    @GetMapping("/count")
    public ResponseEntity<?> getWishlistCount() {
        User user = getCurrentUser();
        int count = wishlistService.getWishlistCount(user.getId());

        Map<String, Integer> response = new HashMap<>();
        response.put("count", count);

        return ResponseEntity.ok(response);
    }
}