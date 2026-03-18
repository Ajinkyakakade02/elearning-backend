package com.elearning.dto;

import com.elearning.entity.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {
    private Long id;
    private Long userId;
    private Long courseId;
    private String userEmail;
    private String userFullName;
    private String courseTitle;
    private Integer progress;
    private String completedLessons;
    private LocalDateTime enrolledAt;
    private LocalDateTime completedAt;
    private LocalDateTime lastAccessed;

    public static EnrollmentDTO fromEntity(Enrollment enrollment) {
        if (enrollment == null) return null;

        EnrollmentDTOBuilder builder = EnrollmentDTO.builder()
                .id(enrollment.getId())
                .userId(enrollment.getUserId())
                .courseId(enrollment.getCourseId())
                .progress(enrollment.getProgress())
                .completedLessons(enrollment.getCompletedLessons())
                .enrolledAt(enrollment.getEnrolledAt())
                .completedAt(enrollment.getCompletedAt())
                .lastAccessed(enrollment.getLastAccessed());

        // Add user information if the relationship exists
        if (enrollment.getUser() != null) {
            builder.userEmail(enrollment.getUser().getEmail())
                    .userFullName(enrollment.getUser().getFullName());
        }

        // Add course information if the relationship exists
        if (enrollment.getCourse() != null) {
            builder.courseTitle(enrollment.getCourse().getTitle());
        }

        return builder.build();
    }

    // Alternative simpler version if you don't need user/course details
    public static EnrollmentDTO simpleFromEntity(Enrollment enrollment) {
        if (enrollment == null) return null;

        return EnrollmentDTO.builder()
                .id(enrollment.getId())
                .userId(enrollment.getUserId())
                .courseId(enrollment.getCourseId())
                .progress(enrollment.getProgress())
                .completedLessons(enrollment.getCompletedLessons())
                .enrolledAt(enrollment.getEnrolledAt())
                .completedAt(enrollment.getCompletedAt())
                .lastAccessed(enrollment.getLastAccessed())
                .build();
    }
}