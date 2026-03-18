package com.elearning.dto.response;

import com.elearning.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private Long categoryId;
    private String title;
    private String description;
    private String thumbnailUrl;
    private Long instructorId;
    private String instructorName;
    private String level;
    private Integer durationHours;
    private Integer totalLessons;
    private Boolean isPublished;
    private Double rating;
    private Integer totalStudents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CourseResponse fromEntity(Course course) {
        if (course == null) return null;

        return CourseResponse.builder()
                .id(course.getId())
                .categoryId(course.getCategoryId())
                .title(course.getTitle())
                .description(course.getDescription())
                .thumbnailUrl(course.getThumbnailUrl())
                .instructorId(course.getInstructorId())
                .instructorName(course.getInstructorName())
                .level(course.getLevel())
                .durationHours(course.getDurationHours())
                .totalLessons(course.getTotalLessons())
                .isPublished(course.getIsPublished())
                .rating(course.getRating())
                .totalStudents(course.getTotalStudents())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }
}