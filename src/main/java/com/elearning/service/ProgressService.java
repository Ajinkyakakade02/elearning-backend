package com.elearning.service;

import com.elearning.entity.Course;
import com.elearning.entity.Lesson;
import com.elearning.entity.Progress;
import com.elearning.entity.User;
import com.elearning.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final UserService userService;
    private final CourseService courseService;
    private final LessonService lessonService;

    public Progress trackProgress(Long userId, Long lessonId, int watchDuration, int lastPosition) {
        User user = userService.findById(userId);
        Lesson lesson = lessonService.getLessonById(lessonId);
        Course course = lesson.getCourse();

        Progress progress = progressRepository.findByUserIdAndLessonId(userId, lessonId)
                .orElse(new Progress());

        progress.setUser(user);
        progress.setLesson(lesson);
        progress.setCourse(course);
        progress.setWatchDurationSeconds(watchDuration);
        progress.setLastPositionSeconds(lastPosition);
        progress.setLastAccessed(LocalDateTime.now());

        return progressRepository.save(progress);
    }

    public Progress markLessonCompleted(Long userId, Long lessonId) {
        User user = userService.findById(userId);
        Lesson lesson = lessonService.getLessonById(lessonId);

        Progress progress = progressRepository.findByUserIdAndLessonId(userId, lessonId)
                .orElse(new Progress());

        progress.setUser(user);
        progress.setLesson(lesson);
        progress.setCourse(lesson.getCourse());
        progress.setIsCompleted(true);
        progress.setCompletedAt(LocalDateTime.now());

        return progressRepository.save(progress);
    }

    public List<Progress> getCourseProgress(Long userId, Long courseId) {
        return progressRepository.findByUserIdAndCourseId(userId, courseId);
    }

    public Long getCompletedLessonsCount(Long userId, Long courseId) {
        return progressRepository.countByUserIdAndCourseIdAndIsCompletedTrue(userId, courseId);
    }

    public int calculateCourseProgress(Long userId, Long courseId) {
        Course course = courseService.getCourseById(courseId);
        Long completedCount = getCompletedLessonsCount(userId, courseId);

        if (course.getTotalLessons() == 0) return 0;
        return (int) ((completedCount * 100) / course.getTotalLessons());
    }
}