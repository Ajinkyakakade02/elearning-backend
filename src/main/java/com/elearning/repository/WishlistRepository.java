package com.elearning.repository;

import com.elearning.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findByUserId(Long userId);

    Optional<Wishlist> findByUserIdAndCourseId(Long userId, Long courseId);

    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    @Modifying
    @Transactional
    void deleteByUserId(Long userId);

    int countByUserId(Long userId);

    @Query("SELECT w FROM Wishlist w JOIN FETCH w.course WHERE w.user.id = :userId")
    List<Wishlist> findByUserIdWithCourse(@Param("userId") Long userId);

    @Query("SELECT COUNT(w) FROM Wishlist w WHERE w.course.id = :courseId")
    int countByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT w FROM Wishlist w WHERE w.course.id = :courseId")
    List<Wishlist> findByCourseId(@Param("courseId") Long courseId);
}