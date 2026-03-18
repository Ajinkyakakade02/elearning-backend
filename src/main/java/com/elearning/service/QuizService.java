package com.elearning.service;

import com.elearning.entity.Quiz;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
    }

    public List<Quiz> getQuizzesByLesson(Long lessonId) {
        return quizRepository.findByLessonId(lessonId);
    }

    public Quiz createQuiz(Quiz quiz) {
        // REMOVED: quiz.setIsPaid(false);
        quiz.setTotalAttempts(0);
        quiz.setAverageScore(0.0);
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Long id, Quiz quizDetails) {
        Quiz quiz = getQuizById(id);

        quiz.setTitle(quizDetails.getTitle());
        quiz.setDescription(quizDetails.getDescription());
        quiz.setTimeLimitMinutes(quizDetails.getTimeLimitMinutes());
        quiz.setPassingScore(quizDetails.getPassingScore());
        // REMOVED: quiz.setIsPaid(false);

        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        Quiz quiz = getQuizById(id);
        quizRepository.delete(quiz);
    }

    public int getQuizAttempts(Long quizId) {
        return quizRepository.countAttemptsByQuizId(quizId);
    }

    public double getQuizAverageScore(Long quizId) {
        Double avg = quizRepository.getAverageScoreByQuizId(quizId);
        return avg != null ? avg : 0.0;
    }

    public void incrementQuizAttempts(Long quizId) {
        Quiz quiz = getQuizById(quizId);
        quiz.setTotalAttempts(quiz.getTotalAttempts() + 1);
        quizRepository.save(quiz);
    }

    public void updateAverageScore(Long quizId, double newScore) {
        Quiz quiz = getQuizById(quizId);
        int attempts = quiz.getTotalAttempts();
        double currentAverage = quiz.getAverageScore();

        // Calculate new average
        double updatedAverage = ((currentAverage * (attempts - 1)) + newScore) / attempts;
        quiz.setAverageScore(updatedAverage);

        quizRepository.save(quiz);
    }
}