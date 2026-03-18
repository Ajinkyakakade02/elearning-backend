package com.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String role = "student";

    @Column(name = "avatar_url")
    private String avatarUrl;

    private String bio;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "user")
    private List<QuizAttempt> quizAttempts = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        this.role = "student";
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Fixed hasPurchasedQuiz method - now checks quizId instead of quiz object
    public boolean hasPurchasedQuiz(Long quizId) {
        return quizAttempts.stream()
                .anyMatch(attempt -> attempt.getQuizId() != null &&
                        attempt.getQuizId().equals(quizId));
    }
}