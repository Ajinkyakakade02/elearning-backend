package com.elearning.dto.response;

import com.elearning.entity.Progress;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CourseProgressResponse {
    private List<Progress> progress;
    private int percentage;
}