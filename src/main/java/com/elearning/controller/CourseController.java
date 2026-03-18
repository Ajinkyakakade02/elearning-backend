package com.elearning.controller;

import com.elearning.dto.response.ApiResponse;
import com.elearning.dto.response.CourseResponse;
import com.elearning.entity.Course;
import com.elearning.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/public")
    public ResponseEntity<?> getAllPublishedCourses() {
        List<Course> courses = courseService.getPublishedCourses();
        List<CourseResponse> responses = courses.stream()
                .map(CourseResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            return ResponseEntity.ok(CourseResponse.fromEntity(course));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(404)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/public/category/{categoryId}")
    public ResponseEntity<?> getCoursesByCategory(@PathVariable Long categoryId) {
        List<Course> courses = courseService.getCoursesByCategory(categoryId);
        List<CourseResponse> responses = courses.stream()
                .map(CourseResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/public/level/{level}")
    public ResponseEntity<?> getCoursesByLevel(@PathVariable String level) {
        List<Course> courses = courseService.getCoursesByLevel(level);
        List<CourseResponse> responses = courses.stream()
                .map(CourseResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/public/search")
    public ResponseEntity<?> searchCourses(@RequestParam String q) {
        List<Course> courses = courseService.searchCourses(q);
        List<CourseResponse> responses = courses.stream()
                .map(CourseResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }


}