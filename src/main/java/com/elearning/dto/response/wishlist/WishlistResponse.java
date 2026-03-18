package com.elearning.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistResponse {
    private Long id;
    private Long userId;
    private Long courseId;
    private String courseTitle;
    private String courseDescription;
    private String courseThumbnail;
    private String instructorName;
    private String level;
    private Integer durationHours;
    private Integer totalLessons;
    private Double rating;
    private Integer totalStudents;
    private String userFullName;
    private String userEmail;
    private LocalDateTime createdAt;
}