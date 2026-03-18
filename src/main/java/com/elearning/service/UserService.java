package com.elearning.service;

import com.elearning.entity.User;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllActive() {
        return userRepository.findByIsActiveTrue();
    }

    public long countUsers() {
        return userRepository.count();
    }

    public void deleteUser(Long id) {
        User user = findById(id);
        user.setIsActive(false);
        userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = findById(id);

        if (userDetails.getFullName() != null) {
            user.setFullName(userDetails.getFullName());
        }
        if (userDetails.getAvatarUrl() != null) {
            user.setAvatarUrl(userDetails.getAvatarUrl());
        }
        if (userDetails.getBio() != null) {
            user.setBio(userDetails.getBio());
        }

        return userRepository.save(user);
    }

    // REMOVED: addPurchase method (was line 72 causing error)

    public List<User> searchUsers(String query) {
        return userRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }
}