package com.elearning.repository;

import com.elearning.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByIsPublishedTrue();

    List<Course> findByCategoryId(Long categoryId);

    List<Course> findByLevel(String level);

    List<Course> findByInstructorId(Long instructorId);

    List<Course> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);

    List<Course> findByIsPublishedTrueAndCategoryId(Long categoryId);

    List<Course> findByIsPublishedTrueAndLevel(String level);
}