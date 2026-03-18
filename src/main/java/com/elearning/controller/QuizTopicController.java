package com.elearning.controller;

import com.elearning.entity.Question;
import com.elearning.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes/topics")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class QuizTopicController {

    private final QuestionRepository questionRepository;

    @GetMapping("/{topicId}/questions")
    public ResponseEntity<?> getQuestionsByTopic(@PathVariable String topicId) {
        try {
            // Map URL topic IDs to database topic values
            String dbTopic = mapTopicId(topicId);
            List<Question> questions = questionRepository.findByTopic(dbTopic);

            if (questions.isEmpty()) {
                return ResponseEntity.ok().body(questions); // Return empty array, not error
            }

            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error fetching questions: " + e.getMessage());
        }
    }

    private String mapTopicId(String topicId) {
        // Map URL topic IDs to database values
        switch(topicId) {
            case "arrays": return "array-string";
            case "linkedlist": return "linkedlist";
            case "stack-queue": return "stack-queue";
            case "trees-graphs": return "trees-graphs";
            case "dp": return "dp";
            case "searching-sorting": return "searching-sorting";
            default: return topicId;
        }
    }
}