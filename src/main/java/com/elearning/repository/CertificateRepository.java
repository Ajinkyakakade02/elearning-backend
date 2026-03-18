package com.elearning.repository;

import com.elearning.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    // Find all certificates for a user
    List<Certificate> findByUserId(Long userId);

    // Find certificate for specific course and user
    Optional<Certificate> findByUserIdAndCourseId(Long userId, Long courseId);

    // Find by certificate number
    Optional<Certificate> findByCertificateNumber(String certificateNumber);

    // Check if user has certificate for course
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    // Get all certificates for a course
    List<Certificate> findByCourseId(Long courseId);

    // Count certificates issued
    Long countByCourseId(Long courseId);

    // Get recent certificates
    @Query("SELECT c FROM Certificate c ORDER BY c.issuedAt DESC")
    List<Certificate> findRecentCertificates(@Param("limit") int limit);
}