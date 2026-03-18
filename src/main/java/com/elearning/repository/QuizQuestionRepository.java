// src/main/java/com/elearning/repository/QuizQuestionRepository.java
package com.elearning.repository;

import com.elearning.entity.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {

    // Find all questions for a quiz ordered by question order
    List<QuizQuestion> findByQuizIdOrderByQuestionOrder(Long quizId);

    // Get question count for a quiz
    @Query("SELECT COUNT(qq) FROM QuizQuestion qq WHERE qq.quiz.id = :quizId")
    Long countQuestionsByQuizId(@Param("quizId") Long quizId);

    // Find specific question in a quiz
    QuizQuestion findByQuizIdAndQuestionId(Long quizId, Long questionId);

    // Delete all questions for a quiz
    void deleteByQuizId(Long quizId);

    // Check if a question is used in any quiz
    boolean existsByQuestionId(Long questionId);
}