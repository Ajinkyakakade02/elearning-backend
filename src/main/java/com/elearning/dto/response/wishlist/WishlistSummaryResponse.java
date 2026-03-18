package com.elearning.dto.response.wishlist;

import lombok.Data;

@Data
public class WishlistSummaryResponse {
    private Long userId;
    private Long totalItems;
    private java.util.List<Long> courseIds;
}