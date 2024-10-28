package com.example.thymeleaf.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.example.thymeleaf.model.Category;

public interface CategoryService {
	
	List<Category> getAllCategories(int page, String keyword) throws SQLException;
	
	Page<Category> findByNameContaining(String keyword, Pageable pageable);

	Page<Category> findAll(Pageable pageable);

    void addCategory(Category category) throws SQLException;

    void updateCategory(Category category) throws SQLException;

    void deleteCategory(String id) throws SQLException;

    Category getCategoryById(String id) throws SQLException;
}
