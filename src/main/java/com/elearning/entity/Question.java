package com.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quiz_id")
    private Long quizId;

    @Column(name = "question_id", unique = true)
    private String questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false)
    private String difficulty;

    @Column(nullable = false)
    private String topic;

    @Column(length = 1000)
    private String url;

    @Column(name = "question_text", length = 2000)
    private String questionText;

    @Column(name = "option_a", length = 1000)
    private String optionA;

    @Column(name = "option_b", length = 1000)
    private String optionB;

    @Column(name = "option_c", length = 1000)
    private String optionC;

    @Column(name = "option_d", length = 1000)
    private String optionD;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(length = 2000)
    private String explanation;

    private Integer points = 1;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "quiz_id", insertable = false, updatable = false)
    @JsonIgnore
    private Quiz quiz;

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