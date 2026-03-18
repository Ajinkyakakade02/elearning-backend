package com.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Data
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lesson_id")
    private Long lessonId;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "time_limit_minutes")
    private Integer timeLimitMinutes;

    @Column(name = "passing_score")
    private Integer passingScore = 70;

    // REMOVE this line: private Boolean isPaid = false;

    @Column(name = "total_attempts")
    private Integer totalAttempts = 0;

    @Column(name = "average_score")
    private Double averageScore = 0.0;

    // Relationship to questions
    @OneToMany(mappedBy = "quiz")
    private List<Question> questions = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}