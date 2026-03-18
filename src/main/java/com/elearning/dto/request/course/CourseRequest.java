package com.elearning.dto.request.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CourseRequest {
    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private String thumbnailUrl;
    private String level;
    private Integer durationHours;
    private Integer totalLessons;
    private BigDecimal price;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
}