package com.elearning.controller;

import com.elearning.dto.response.ApiResponse;
import com.elearning.entity.Enrollment;
import com.elearning.service.EnrollmentService;
import com.elearning.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/enroll/{courseId}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> enrollInCourse(
            @AuthenticationPrincipal UserDetailsImpl currentUser,
            @PathVariable Long courseId) {
        try {
            Enrollment enrollment = enrollmentService.enrollUser(currentUser.getId(), courseId);
            return ResponseEntity.ok(ApiResponse.success("Successfully enrolled in course", enrollment));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // Add this method to handle OPTIONS requests
    @RequestMapping(value = "/enroll/{courseId}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().build();
    }
}
