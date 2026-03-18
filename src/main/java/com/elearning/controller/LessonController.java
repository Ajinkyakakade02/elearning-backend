package com.elearning.controller;

import com.elearning.dto.response.ApiResponse;
import com.elearning.entity.Lesson;
import com.elearning.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getCourseLessons(@PathVariable Long courseId) {
        List<Lesson> lessons = lessonService.getCourseLessons(courseId);
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLessonById(@PathVariable Long id) {
        try {
            Lesson lesson = lessonService.getLessonById(id);
            return ResponseEntity.ok(lesson);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(404)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/course/{courseId}/preview")
    public ResponseEntity<?> getPreviewLessons(@PathVariable Long courseId) {
        List<Lesson> lessons = lessonService.getPreviewLessons(courseId);
        return ResponseEntity.ok(lessons);
    }

    @PostMapping("/course/{courseId}")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createLesson(
            @RequestBody Lesson lesson,
            @PathVariable Long courseId) {

        try {
            Lesson savedLesson = lessonService.createLesson(lesson, courseId);
            return ResponseEntity.ok(savedLesson);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateLesson(
            @PathVariable Long id,
            @RequestBody Lesson lessonDetails) {

        try {
            Lesson updatedLesson = lessonService.updateLesson(id, lessonDetails);
            return ResponseEntity.ok(updatedLesson);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(404)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id) {
        try {
            lessonService.deleteLesson(id);
            return ResponseEntity.ok(ApiResponse.success("Lesson deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(404)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}