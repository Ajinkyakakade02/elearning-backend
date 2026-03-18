package com.elearning.controller;

import com.elearning.entity.Question;
import com.elearning.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {  // Changed from QuestionController to QuestionsController

    private final QuestionService questionService;

    // Get all questions
    @GetMapping
    public ResponseEntity<?> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    // Get question by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    // Get questions by topic
    @GetMapping("/topic/{topic}")
    public ResponseEntity<?> getQuestionsByTopic(@PathVariable String topic) {
        try {
            List<Question> questions = questionService.getQuestionsByTopic(topic);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    // Get questions by platform
    @GetMapping("/platform/{platform}")
    public ResponseEntity<?> getQuestionsByPlatform(@PathVariable String platform) {
        try {
            List<Question> questions = questionService.getQuestionsByPlatform(platform);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    // Get questions by difficulty
    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<?> getQuestionsByDifficulty(@PathVariable String difficulty) {
        List<Question> questions = questionService.getQuestionsByDifficulty(difficulty);
        return ResponseEntity.ok(questions);
    }

    // Get questions by topic and platform
    @GetMapping("/topic/{topic}/platform/{platform}")
    public ResponseEntity<?> getQuestionsByTopicAndPlatform(
            @PathVariable String topic,
            @PathVariable String platform) {
        try {
            List<Question> questions = questionService.getQuestionsByTopicAndPlatform(topic, platform);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    // Get questions by topic and difficulty
    @GetMapping("/topic/{topic}/difficulty/{difficulty}")
    public ResponseEntity<?> getQuestionsByTopicAndDifficulty(
            @PathVariable String topic,
            @PathVariable String difficulty) {
        List<Question> questions = questionService.getQuestionsByTopicAndDifficulty(topic, difficulty);
        return ResponseEntity.ok(questions);
    }

    // Get questions with multiple filters
    @GetMapping("/filter")
    public ResponseEntity<?> getQuestionsWithFilters(
            @RequestParam(required = false) String topic,
            @RequestParam(required = false) String platform,
            @RequestParam(required = false) String difficulty) {
        List<Question> questions = questionService.getQuestionsWithFilters(topic, platform, difficulty);
        return ResponseEntity.ok(questions);
    }

    // Search questions
    @GetMapping("/search")
    public ResponseEntity<?> searchQuestions(@RequestParam String q) {
        List<Question> questions = questionService.searchQuestions(q);
        return ResponseEntity.ok(questions);
    }

    // Get all topics
    @GetMapping("/topics")
    public ResponseEntity<?> getAllTopics() {
        List<String> topics = questionService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

    // Get all platforms
    @GetMapping("/platforms")
    public ResponseEntity<?> getAllPlatforms() {
        List<String> platforms = questionService.getAllPlatforms();
        return ResponseEntity.ok(platforms);
    }

    // Get statistics
    @GetMapping("/stats")
    public ResponseEntity<?> getStatistics() {
        Map<String, Object> stats = questionService.getStatistics();
        return ResponseEntity.ok(stats);
    }
}