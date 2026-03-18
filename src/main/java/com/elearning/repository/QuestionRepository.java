package com.elearning.repository;

import com.elearning.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // Find questions by topic
    List<Question> findByTopic(String topic);

    // Find questions by platform
    List<Question> findByPlatform(String platform);

    // Find questions by difficulty
    List<Question> findByDifficulty(String difficulty);

    // Find questions by topic and platform
    List<Question> findByTopicAndPlatform(String topic, String platform);

    // Find questions by topic and difficulty
    List<Question> findByTopicAndDifficulty(String topic, String difficulty);

    // Find questions by platform and difficulty
    List<Question> findByPlatformAndDifficulty(String platform, String difficulty);

    // Find questions by topic, platform, and difficulty
    List<Question> findByTopicAndPlatformAndDifficulty(String topic, String platform, String difficulty);

    // Custom query to search questions by title (case insensitive)
    @Query("SELECT q FROM Question q WHERE LOWER(q.title) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Question> searchByTitle(@Param("searchTerm") String searchTerm);

    // Get distinct topics
    @Query("SELECT DISTINCT q.topic FROM Question q ORDER BY q.topic")
    List<String> findDistinctTopics();

    // Get distinct platforms
    @Query("SELECT DISTINCT q.platform FROM Question q ORDER BY q.platform")
    List<String> findDistinctPlatforms();

    // Count questions by topic
    @Query("SELECT q.topic, COUNT(q) FROM Question q GROUP BY q.topic")
    List<Object[]> countQuestionsByTopic();

    // Count questions by platform
    @Query("SELECT q.platform, COUNT(q) FROM Question q GROUP BY q.platform")
    List<Object[]> countQuestionsByPlatform();
}