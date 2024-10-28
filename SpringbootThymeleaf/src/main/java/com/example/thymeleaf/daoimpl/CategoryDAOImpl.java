package com.example.thymeleaf.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.thymeleaf.dao.CategoryDAO;
import com.example.thymeleaf.model.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Category> getAllCategories(int page, String keyword) throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE name LIKE ? LIMIT ?, ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, "%" + keyword + "%");
            stmt.setInt(2, page * 10);
            stmt.setInt(3, 10);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getString("id"));
                category.setCategoryName(rs.getString("name"));
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public void addCategory(Category category) throws SQLException {
        String sql = "INSERT INTO category (id, name) VALUES (?, ?)";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, category.getCategoryId());
            stmt.setString(2, category.getCategoryName());
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateCategory(Category category) throws SQLException {
        String sql = "UPDATE category SET name = ? WHERE id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, category.getCategoryName());
            stmt.setString(2, category.getCategoryId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCategory(String id) throws SQLException {
        String sql = "DELETE FROM category WHERE id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Category getCategoryById(String id) throws SQLException {
        Category category = null;
        String sql = "SELECT * FROM category WHERE id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                category = new Category();
                category.setCategoryId(rs.getString("id"));
                category.setCategoryName(rs.getString("name"));
            }
        }
        return category;
    }

    @Override
    public Page<Category> findByNameContaining(String keyword, Pageable pageable) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category WHERE name LIKE ? LIMIT ?, ?";
        long totalCount = getTotalCountByKeyword(keyword);
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, "%" + keyword + "%");
            stmt.setInt(2, pageable.getPageNumber() * pageable.getPageSize());
            stmt.setInt(3, pageable.getPageSize());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getString("id"));
                category.setCategoryName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return new PageImpl<>(categories, pageable, totalCount);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category LIMIT ?, ?";
        long totalCount = getTotalCount();
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, pageable.getPageNumber() * pageable.getPageSize());
            stmt.setInt(2, pageable.getPageSize());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getString("id"));
                category.setCategoryName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return new PageImpl<>(categories, pageable, totalCount);
    }

    private long getTotalCount() {
        long count = 0;
        String sql = "SELECT COUNT(*) FROM category";
        
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            if (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private long getTotalCountByKeyword(String keyword) {
        long count = 0;
        String sql = "SELECT COUNT(*) FROM category WHERE name LIKE ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
