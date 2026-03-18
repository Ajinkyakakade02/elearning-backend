package com.elearning.service;

import com.elearning.entity.Course;
import com.elearning.entity.Lesson;
import com.elearning.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseService courseService;

    public Lesson createLesson(Lesson lesson, Long courseId) {
        Course course = courseService.getCourseById(courseId);
        lesson.setCourse(course);

        int nextOrder = lessonRepository.findByCourseIdOrderByLessonOrderAsc(courseId).size() + 1;
        lesson.setLessonOrder(nextOrder);

        return lessonRepository.save(lesson);
    }

    public List<Lesson> getCourseLessons(Long courseId) {
        return lessonRepository.findByCourseIdOrderByLessonOrderAsc(courseId);
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));
    }

    public Lesson updateLesson(Long id, Lesson lessonDetails) {
        Lesson lesson = getLessonById(id);
        lesson.setTitle(lessonDetails.getTitle());
        lesson.setDescription(lessonDetails.getDescription());
        lesson.setVideoUrl(lessonDetails.getVideoUrl());
        lesson.setDurationMinutes(lessonDetails.getDurationMinutes());
        lesson.setIsPreview(lessonDetails.getIsPreview());
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Long id) {
        Lesson lesson = getLessonById(id);
        lessonRepository.delete(lesson);
    }

    public List<Lesson> getPreviewLessons(Long courseId) {
        return lessonRepository.findByCourseIdAndIsPreviewTrue(courseId);
    }
}