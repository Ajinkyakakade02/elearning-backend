package com.elearning.controller;

import com.elearning.dto.response.ApiResponse;
import com.elearning.entity.Progress;
import com.elearning.security.JwtTokenProvider;
import com.elearning.service.ProgressService;
import com.elearning.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProgressController {

    private final ProgressService progressService;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/lesson/{lessonId}/track")
    public ResponseEntity<?> trackProgress(
            @PathVariable Long lessonId,
            @RequestParam int watchDuration,
            @RequestParam int lastPosition,
            @RequestHeader("Authorization") String token) {

        try {
            String jwt = token.substring(7);
            String email = tokenProvider.getEmailFromToken(jwt);
            Long userId = userService.findByEmail(email).get().getId();

            Progress progress = progressService.trackProgress(
                    userId, lessonId, watchDuration, lastPosition
            );
            return ResponseEntity.ok(progress);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/lesson/{lessonId}/complete")
    public ResponseEntity<?> markLessonCompleted(
            @PathVariable Long lessonId,
            @RequestHeader("Authorization") String token) {

        try {
            String jwt = token.substring(7);
            String email = tokenProvider.getEmailFromToken(jwt);
            Long userId = userService.findByEmail(email).get().getId();

            Progress progress = progressService.markLessonCompleted(userId, lessonId);
            return ResponseEntity.ok(progress);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getCourseProgress(
            @PathVariable Long courseId,
            @RequestHeader("Authorization") String token) {

        try {
            String jwt = token.substring(7);
            String email = tokenProvider.getEmailFromToken(jwt);
            Long userId = userService.findByEmail(email).get().getId();

            List<Progress> progress = progressService.getCourseProgress(userId, courseId);
            int percentage = progressService.calculateCourseProgress(userId, courseId);

            Map<String, Object> response = new HashMap<>();
            response.put("progress", progress);
            response.put("percentage", percentage);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}