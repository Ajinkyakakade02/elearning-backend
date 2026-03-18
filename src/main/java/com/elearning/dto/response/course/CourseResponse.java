package com.elearning.dto.response.course;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CourseResponse {
    private Long id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private String level;
    private Integer durationHours;
    private Integer totalLessons;
    private BigDecimal price;
    private BigDecimal rating;
    private Integer totalStudents;
    private String instructorName;
    private Long instructorId;        // Add this field
    private String categoryName;
    private Long categoryId;           // Add this field
    private Boolean isPublished;
}