package com.elearning.dto.response;

import com.elearning.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponse {
    private Long id;
    private Long lessonId;
    private String title;
    private String description;
    private Integer timeLimitMinutes;
    private Integer passingScore;
    private Integer totalAttempts;
    private Double averageScore;
    private List<QuestionResponse> questions;
    private LocalDateTime createdAt;

    // REMOVED: isPaid field

    public static QuizResponse fromEntity(Quiz quiz) {
        if (quiz == null) {
            return null;
        }

        QuizResponse response = QuizResponse.builder()
                .id(quiz.getId())
                .lessonId(quiz.getLessonId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .timeLimitMinutes(quiz.getTimeLimitMinutes())
                .passingScore(quiz.getPassingScore())
                .totalAttempts(quiz.getTotalAttempts())
                .averageScore(quiz.getAverageScore())
                .createdAt(quiz.getCreatedAt())
                .build();

        // Optionally map questions if needed
        // if (quiz.getQuestions() != null) {
        //     response.setQuestions(quiz.getQuestions().stream()
        //             .map(QuestionResponse::fromEntity)
        //             .collect(Collectors.toList()));
        // }

        return response;
    }
}