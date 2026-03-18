package com.elearning.dto.request;

import lombok.Data;

@Data
public class EnrollmentRequest {
    private Long courseId;

    public EnrollmentRequest() {}

    public EnrollmentRequest(Long courseId) {
        this.courseId = courseId;
    }
}