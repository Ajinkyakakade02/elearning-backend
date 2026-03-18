package com.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "lessons")
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "lesson_order", nullable = false)
    private Integer lessonOrder;

    @Column(name = "is_preview")
    private Boolean isPreview = false;

    // COMMENT OUT THIS LINE TEMPORARILY
    // @OneToOne(mappedBy = "lesson")
    // private Quiz quiz;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}