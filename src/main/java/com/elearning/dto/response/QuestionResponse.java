package com.elearning.dto.response;

import com.elearning.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String questionId;
    private String title;
    private String platform;
    private String difficulty;
    private String topic;
    private String url;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String explanation;
    private Integer points;

    public static QuestionResponse fromEntity(Question question) {
        if (question == null) {
            return null;
        }

        return QuestionResponse.builder()
                .id(question.getId())
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .platform(question.getPlatform())
                .difficulty(question.getDifficulty())
                .topic(question.getTopic())
                .url(question.getUrl())
                .questionText(question.getQuestionText())
                .optionA(question.getOptionA())
                .optionB(question.getOptionB())
                .optionC(question.getOptionC())
                .optionD(question.getOptionD())
                .correctAnswer(question.getCorrectAnswer())
                .explanation(question.getExplanation())
                .points(question.getPoints())
                .build();
    }
}