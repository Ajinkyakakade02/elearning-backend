package com.elearning.service;

import com.elearning.entity.Enrollment;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public Enrollment enrollUser(Long userId, Long courseId) {
        // Check if already enrolled
        if (enrollmentRepository.existsByUserIdAndCourseId(userId, courseId)) {
            throw new RuntimeException("Already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(userId);
        enrollment.setCourseId(courseId);
        enrollment.setProgress(0);
        enrollment.setCompletedLessons("");

        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getUserEnrollments(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }

    public Enrollment getEnrollment(Long userId, Long courseId) {
        return enrollmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
    }

    public void updateProgress(Long enrollmentId, Integer progress, String completedLessons) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        enrollment.setProgress(progress);
        enrollment.setCompletedLessons(completedLessons);
        enrollment.setLastAccessed(LocalDateTime.now());

        if (progress >= 100) {
            enrollment.setCompletedAt(LocalDateTime.now());
        }

        enrollmentRepository.save(enrollment);
    }

    public void markLessonCompleted(Long userId, Long courseId, Long lessonId) {
        Enrollment enrollment = getEnrollment(userId, courseId);

        String completedLessons = enrollment.getCompletedLessons();
        if (completedLessons == null) completedLessons = "";

        if (!completedLessons.contains(lessonId.toString())) {
            completedLessons = completedLessons + lessonId.toString() + ",";
            enrollment.setCompletedLessons(completedLessons);

            // Update progress based on total lessons (you'd need to get this from CourseService)
            // int totalLessons = courseService.getCourseById(courseId).getTotalLessons();
            // int completedCount = completedLessons.split(",").length;
            // int progress = (completedCount * 100) / totalLessons;
            // enrollment.setProgress(progress);

            enrollment.setLastAccessed(LocalDateTime.now());
            enrollmentRepository.save(enrollment);
        }
    }
}