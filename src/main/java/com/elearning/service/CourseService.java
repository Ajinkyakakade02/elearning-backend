package com.elearning.service;

import com.elearning.entity.Course;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService; // Add this to get instructor details

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getPublishedCourses() {
        return courseRepository.findByIsPublishedTrue();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    public Course createCourse(Course course) {
        course.setIsPublished(false);
        course.setTotalStudents(0);
        course.setRating(0.0);
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course courseDetails) {
        Course course = getCourseById(id);

        if (courseDetails.getTitle() != null) {
            course.setTitle(courseDetails.getTitle());
        }
        if (courseDetails.getDescription() != null) {
            course.setDescription(courseDetails.getDescription());
        }
        if (courseDetails.getThumbnailUrl() != null) {
            course.setThumbnailUrl(courseDetails.getThumbnailUrl());
        }
        if (courseDetails.getInstructorName() != null) {
            course.setInstructorName(courseDetails.getInstructorName());
        }
        if (courseDetails.getLevel() != null) {
            course.setLevel(courseDetails.getLevel());
        }
        if (courseDetails.getDurationHours() != null) {
            course.setDurationHours(courseDetails.getDurationHours());
        }
        if (courseDetails.getTotalLessons() != null) {
            course.setTotalLessons(courseDetails.getTotalLessons());
        }
        if (courseDetails.getIsPublished() != null) {
            course.setIsPublished(courseDetails.getIsPublished());
        }

        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }

    public List<Course> getCoursesByCategory(Long categoryId) {
        return courseRepository.findByCategoryId(categoryId);
    }

    public List<Course> getCoursesByLevel(String level) {
        return courseRepository.findByLevel(level);
    }

    public List<Course> searchCourses(String keyword) {
        return courseRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    public void incrementStudentCount(Long courseId) {
        Course course = getCourseById(courseId);
        course.setTotalStudents(course.getTotalStudents() + 1);
        courseRepository.save(course);
    }

    public void updateRating(Long courseId, Double newRating) {
        Course course = getCourseById(courseId);
        // You might want to calculate average rating here
        course.setRating(newRating);
        courseRepository.save(course);
    }
}