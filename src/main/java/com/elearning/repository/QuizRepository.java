package com.elearning.repository;

import com.elearning.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByLessonId(Long lessonId);

    // OPTION A: Remove this method if you don't need it
    // @Query("SELECT q FROM Quiz q WHERE q.isPaid = false")
    // List<Quiz> findAllFreeQuizzes();

    // OPTION B: Replace with a method that returns all quizzes
    // Since all quizzes are now free, this can just return findAll()
    default List<Quiz> findAllQuizzes() {
        return findAll();
    }

    @Query("SELECT COUNT(qa) FROM QuizAttempt qa WHERE qa.quizId = :quizId")
    int countAttemptsByQuizId(@Param("quizId") Long quizId);

    @Query("SELECT AVG(qa.score) FROM QuizAttempt qa WHERE qa.quizId = :quizId")
    Double getAverageScoreByQuizId(@Param("quizId") Long quizId);
}