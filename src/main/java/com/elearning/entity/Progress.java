package com.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
@Data
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;  // This should be recognized now

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "is_completed")
    private Boolean isCompleted = false;

    @Column(name = "watch_duration_seconds")
    private Integer watchDurationSeconds = 0;

    @Column(name = "last_position_seconds")
    private Integer lastPositionSeconds = 0;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "last_accessed")
    private LocalDateTime lastAccessed;

    @PrePersist
    protected void onCreate() {
        lastAccessed = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastAccessed = LocalDateTime.now();
    }
}