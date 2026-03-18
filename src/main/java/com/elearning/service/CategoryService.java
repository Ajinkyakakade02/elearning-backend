package com.elearning.service;

import com.elearning.entity.Category;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        // Generate slug from name
        String slug = category.getName().toLowerCase()
                .replaceAll("[^a-z0-9]", "-")
                .replaceAll("-+", "-");
        category.setSlug(slug);
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    public Category getCategoryBySlug(String slug) {
        return categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with slug: " + slug));
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = getCategoryById(id);
        category.setName(categoryDetails.getName());
        if (categoryDetails.getName() != null) {
            String slug = categoryDetails.getName().toLowerCase()
                    .replaceAll("[^a-z0-9]", "-")
                    .replaceAll("-+", "-");
            category.setSlug(slug);
        }
        category.setIcon(categoryDetails.getIcon());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}