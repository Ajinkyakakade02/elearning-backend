package com.elearning.controller;

import com.elearning.dto.response.QuizResponse;
import com.elearning.entity.Quiz;
import com.elearning.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(QuizResponse.fromEntity(quiz));
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<?> getQuizzesByLesson(@PathVariable Long lessonId) {
        List<Quiz> quizzes = quizService.getQuizzesByLesson(lessonId);
        List<QuizResponse> responses = quizzes.stream()
                .map(QuizResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}