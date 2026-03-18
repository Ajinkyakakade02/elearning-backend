package com.elearning.service;

import com.elearning.entity.Certificate;
import com.elearning.entity.User;
import com.elearning.entity.Course;
import com.elearning.entity.Enrollment;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.repository.CertificateRepository;
import com.elearning.repository.UserRepository;
import com.elearning.repository.CourseRepository;
import com.elearning.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Generate certificate on course completion
    @Transactional
    public Certificate generateCertificate(Long userId, Long courseId) {
        // Check if certificate already exists
        if (certificateRepository.existsByUserIdAndCourseId(userId, courseId)) {
            throw new RuntimeException("Certificate already generated for this course");
        }

        // Verify course completion
        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        if (enrollment.getProgress() < 100) {
            throw new RuntimeException("Course not completed yet");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Certificate certificate = new Certificate();
        certificate.setUser(user);
        certificate.setCourse(course);
        certificate.setCertificateNumber(generateCertificateNumber());

        // You'll implement PDF generation here
        String certificateUrl = generateCertificatePdf(user, course, certificate.getCertificateNumber());
        certificate.setCertificateUrl(certificateUrl);

        return certificateRepository.save(certificate);
    }

    // Get user's certificates
    public List<Certificate> getUserCertificates(Long userId) {
        return certificateRepository.findByUserId(userId);
    }

    // Get certificate by number
    public Certificate getCertificateByNumber(String certificateNumber) {
        return certificateRepository.findByCertificateNumber(certificateNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Certificate not found"));
    }

    // Verify certificate
    public boolean verifyCertificate(String certificateNumber) {
        return certificateRepository.findByCertificateNumber(certificateNumber).isPresent();
    }

    // Generate unique certificate number
    private String generateCertificateNumber() {
        return "CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase() +
                "-" + System.currentTimeMillis();
    }

    // Placeholder for PDF generation
    private String generateCertificatePdf(User user, Course course, String certNumber) {
        // TODO: Implement PDF generation logic
        // Return the URL where PDF is stored
        return "/certificates/" + certNumber + ".pdf";
    }
}