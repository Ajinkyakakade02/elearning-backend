package com.elearning.repository;

import com.elearning.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByUserIdAndLessonId(Long userId, Long lessonId);
    List<Progress> findByUserIdAndCourseId(Long userId, Long courseId);
    Long countByUserIdAndCourseIdAndIsCompletedTrue(Long userId, Long courseId);
}