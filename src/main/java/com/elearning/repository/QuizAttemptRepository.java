package com.elearning.repository;

import com.elearning.entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {

    // Fix: Change from findByUserId to findByUser_Id
    List<QuizAttempt> findByUser_Id(Long userId);

    // Or use @Query
    @Query("SELECT qa FROM QuizAttempt qa WHERE qa.user.id = :userId")
    List<QuizAttempt> findAttemptsByUserId(@Param("userId") Long userId);

    // Find attempts by user and quiz
    List<QuizAttempt> findByUser_IdAndQuizId(Long userId, Long quizId);

    // Find latest attempt by user and quiz
    Optional<QuizAttempt> findTopByUser_IdAndQuizIdOrderByCompletedAtDesc(Long userId, Long quizId);

    // Count attempts by user and quiz
    long countByUser_IdAndQuizId(Long userId, Long quizId);

    // Get average score for a user on a specific quiz
    @Query("SELECT AVG(qa.score) FROM QuizAttempt qa WHERE qa.user.id = :userId AND qa.quizId = :quizId")
    Double getAverageScoreForUserAndQuiz(@Param("userId") Long userId, @Param("quizId") Long quizId);

    // Get all attempts for a specific quiz
    List<QuizAttempt> findByQuizId(Long quizId);

    // Get passed attempts for a user
    List<QuizAttempt> findByUser_IdAndIsPassedTrue(Long userId);

    // Get attempts within date range
    @Query("SELECT qa FROM QuizAttempt qa WHERE qa.user.id = :userId AND qa.completedAt BETWEEN :startDate AND :endDate")
    List<QuizAttempt> findAttemptsInDateRange(@Param("userId") Long userId,
                                              @Param("startDate") java.time.LocalDateTime startDate,
                                              @Param("endDate") java.time.LocalDateTime endDate);
}