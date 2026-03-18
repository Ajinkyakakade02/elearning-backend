package com.elearning.service;

import com.elearning.entity.Question;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    // Get all questions
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // Get question by ID
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id: " + id));
    }

    // Get questions by topic
    public List<Question> getQuestionsByTopic(String topic) {
        List<Question> questions = questionRepository.findByTopic(topic);
        if (questions.isEmpty()) {
            throw new ResourceNotFoundException("No questions found for topic: " + topic);
        }
        return questions;
    }

    // Get questions by platform
    public List<Question> getQuestionsByPlatform(String platform) {
        List<Question> questions = questionRepository.findByPlatform(platform);
        if (questions.isEmpty()) {
            throw new ResourceNotFoundException("No questions found for platform: " + platform);
        }
        return questions;
    }

    // Get questions by difficulty
    public List<Question> getQuestionsByDifficulty(String difficulty) {
        List<Question> questions = questionRepository.findByDifficulty(difficulty);
        if (questions.isEmpty()) {
            throw new ResourceNotFoundException("No questions found for difficulty: " + difficulty);
        }
        return questions;
    }

    // Get questions by topic and platform
    public List<Question> getQuestionsByTopicAndPlatform(String topic, String platform) {
        List<Question> questions = questionRepository.findByTopicAndPlatform(topic, platform);
        if (questions.isEmpty()) {
            throw new ResourceNotFoundException("No questions found for topic: " + topic + " and platform: " + platform);
        }
        return questions;
    }

    // Get questions by topic and difficulty
    public List<Question> getQuestionsByTopicAndDifficulty(String topic, String difficulty) {
        return questionRepository.findByTopicAndDifficulty(topic, difficulty);
    }

    // Get questions by platform and difficulty
    public List<Question> getQuestionsByPlatformAndDifficulty(String platform, String difficulty) {
        return questionRepository.findByPlatformAndDifficulty(platform, difficulty);
    }

    // Get questions with filters
    public List<Question> getQuestionsWithFilters(String topic, String platform, String difficulty) {
        if (topic != null && platform != null && difficulty != null) {
            return questionRepository.findByTopicAndPlatformAndDifficulty(topic, platform, difficulty);
        } else if (topic != null && platform != null) {
            return questionRepository.findByTopicAndPlatform(topic, platform);
        } else if (topic != null && difficulty != null) {
            return questionRepository.findByTopicAndDifficulty(topic, difficulty);
        } else if (platform != null && difficulty != null) {
            return questionRepository.findByPlatformAndDifficulty(platform, difficulty);
        } else if (topic != null) {
            return questionRepository.findByTopic(topic);
        } else if (platform != null) {
            return questionRepository.findByPlatform(platform);
        } else if (difficulty != null) {
            return questionRepository.findByDifficulty(difficulty);
        } else {
            return questionRepository.findAll();
        }
    }

    // Search questions by title
    public List<Question> searchQuestions(String searchTerm) {
        return questionRepository.searchByTitle(searchTerm);
    }

    // Get all distinct topics
    public List<String> getAllTopics() {
        return questionRepository.findDistinctTopics();
    }

    // Get all distinct platforms
    public List<String> getAllPlatforms() {
        return questionRepository.findDistinctPlatforms();
    }

    // Get statistics
    public Map<String, Object> getStatistics() {
        List<Object[]> topicCounts = questionRepository.countQuestionsByTopic();
        List<Object[]> platformCounts = questionRepository.countQuestionsByPlatform();

        Map<String, Long> topicStats = topicCounts.stream()
                .collect(Collectors.toMap(
                        arr -> (String) arr[0],
                        arr -> (Long) arr[1]
                ));

        Map<String, Long> platformStats = platformCounts.stream()
                .collect(Collectors.toMap(
                        arr -> (String) arr[0],
                        arr -> (Long) arr[1]
                ));

        return Map.of(
                "totalQuestions", questionRepository.count(),
                "byTopic", topicStats,
                "byPlatform", platformStats
        );
    }
}