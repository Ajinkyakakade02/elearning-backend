package com.elearning.dto.request.wishlist;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WishlistRequest {

    @NotNull(message = "Course ID is required")
    private Long courseId;
}