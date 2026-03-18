package com.elearning.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "certificates")
@Data
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "certificate_url", length = 500)
    private String certificateUrl;

    @Column(name = "certificate_number", unique = true, length = 100)
    private String certificateNumber;

    @Column(name = "issued_at")
    private LocalDateTime issuedAt;

    @PrePersist
    protected void onCreate() {
        issuedAt = LocalDateTime.now();
        if (certificateNumber == null) {
            certificateNumber = generateCertificateNumber();
        }
    }

    private String generateCertificateNumber() {
        return "CERT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase() +
                "-" + System.currentTimeMillis();
    }
}