package com.elearning.service;

import com.elearning.entity.Course;
import com.elearning.entity.User;
import com.elearning.entity.Wishlist;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserService userService;
    private final CourseService courseService;

    public List<Wishlist> getUserWishlist(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    @Transactional
    public Wishlist addToWishlist(Long userId, Long courseId) {
        // Check if already in wishlist
        if (wishlistRepository.existsByUserIdAndCourseId(userId, courseId)) {
            throw new RuntimeException("Course already in wishlist");
        }

        User user = userService.findById(userId);
        Course course = courseService.getCourseById(courseId);

        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setCourse(course);

        return wishlistRepository.save(wishlist);
    }

    @Transactional
    public void removeFromWishlist(Long userId, Long courseId) {
        Wishlist wishlist = wishlistRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist item not found"));

        wishlistRepository.delete(wishlist);
    }

    public boolean isInWishlist(Long userId, Long courseId) {
        return wishlistRepository.existsByUserIdAndCourseId(userId, courseId);
    }

    @Transactional
    public void clearWishlist(Long userId) {
        wishlistRepository.deleteByUserId(userId);
    }

    public int getWishlistCount(Long userId) {
        return wishlistRepository.countByUserId(userId);
    }

    public List<Wishlist> getWishlistWithCourseDetails(Long userId) {
        return wishlistRepository.findByUserIdWithCourse(userId);
    }
}