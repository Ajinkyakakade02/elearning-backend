package com.elearning.repository;

import com.elearning.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByUserId(Long userId);

    Optional<Enrollment> findByUserIdAndCourseId(Long userId, Long courseId);

    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.course.id = :courseId")
    long countByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT e FROM Enrollment e WHERE e.completedAt IS NOT NULL")
    List<Enrollment> findCompletedEnrollments();

    @Query("SELECT e FROM Enrollment e WHERE e.completedAt IS NULL")
    List<Enrollment> findInProgressEnrollments();

    @Query("SELECT e FROM Enrollment e ORDER BY e.enrolledAt DESC")
    List<Enrollment> findTopByOrderByEnrolledAtDesc(org.springframework.data.domain.Pageable pageable);

    default List<Enrollment> findTopByOrderByEnrolledAtDesc(int limit) {
        return findTopByOrderByEnrolledAtDesc(org.springframework.data.domain.PageRequest.of(0, limit));
    }

    @Query("SELECT AVG(e.progress) FROM Enrollment e WHERE e.course.id = :courseId")
    Double getAverageProgressForCourse(@Param("courseId") Long courseId);

    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.course.id = :courseId AND e.progress = 100")
    long countCompletedForCourse(@Param("courseId") Long courseId);

    @Query("SELECT e FROM Enrollment e WHERE e.lastAccessed < :date")
    List<Enrollment> findInactiveEnrollments(@Param("date") java.time.LocalDateTime date);
}