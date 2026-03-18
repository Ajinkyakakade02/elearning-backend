package com.elearning.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CourseRequest {
    private String title;
    private String description;
    private String thumbnailUrl;
    private Long categoryId;
    private String level;
    private Integer durationHours;
    private BigDecimal price;
    private Boolean isPublished;
}