package com.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "instructor_id")
    private Long instructorId;

    // Add instructor name field
    @Column(name = "instructor_name")
    private String instructorName;

    private String level;

    @Column(name = "duration_hours")
    private Integer durationHours;

    @Column(name = "total_lessons")
    private Integer totalLessons = 0;

    @Column(name = "is_published")
    private Boolean isPublished = false;

    private Double rating = 0.0;

    @Column(name = "total_students")
    private Integer totalStudents = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}