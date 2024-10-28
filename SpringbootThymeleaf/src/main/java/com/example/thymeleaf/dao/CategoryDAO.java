package com.example.thymeleaf.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.thymeleaf.model.Category;

public interface CategoryDAO {
    List<Category> getAllCategories(int page, String keyword) throws SQLException;

    void addCategory(Category category) throws SQLException;

    void updateCategory(Category category) throws SQLException;

    void deleteCategory(String id) throws SQLException;

    Category getCategoryById(String id) throws SQLException;

    Page<Category> findByNameContaining(String keyword, Pageable pageable);

    Page<Category> findAll(Pageable pageable);
}
