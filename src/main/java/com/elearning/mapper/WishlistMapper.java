package com.elearning.mapper;

import com.elearning.dto.response.WishlistResponse;
import com.elearning.entity.Course;
import com.elearning.entity.User;
import com.elearning.entity.Wishlist;
import org.springframework.stereotype.Component;

@Component
public class WishlistMapper {

    public WishlistResponse toResponse(Wishlist wishlist) {
        if (wishlist == null) {
            return null;
        }

        WishlistResponse response = new WishlistResponse();
        response.setId(wishlist.getId());

        // Fix: Get IDs from the actual objects
        if (wishlist.getUser() != null) {
            response.setUserId(wishlist.getUser().getId());
            response.setUserFullName(wishlist.getUser().getFullName());
            response.setUserEmail(wishlist.getUser().getEmail());
        }

        if (wishlist.getCourse() != null) {
            response.setCourseId(wishlist.getCourse().getId());
            response.setCourseTitle(wishlist.getCourse().getTitle());
            response.setCourseDescription(wishlist.getCourse().getDescription());
            response.setCourseThumbnail(wishlist.getCourse().getThumbnailUrl());
            response.setInstructorName(wishlist.getCourse().getInstructorName());
            response.setLevel(wishlist.getCourse().getLevel());
            response.setDurationHours(wishlist.getCourse().getDurationHours());
            response.setTotalLessons(wishlist.getCourse().getTotalLessons());
            response.setRating(wishlist.getCourse().getRating());
            response.setTotalStudents(wishlist.getCourse().getTotalStudents());
        }

        response.setCreatedAt(wishlist.getCreatedAt());

        return response;
    }

    public Wishlist toEntity(User user, Course course) {
        if (user == null || course == null) {
            return null;
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setCourse(course);
        return wishlist;
    }
}