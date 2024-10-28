package com.example.thymeleaf.serviceimpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.thymeleaf.dao.CategoryDAO;
import com.example.thymeleaf.model.Category;
import com.example.thymeleaf.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

	@Override
	public List<Category> getAllCategories(int page, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return categoryDAO.getAllCategories(page, keyword);
	}

	@Override
	public Page<Category> findByNameContaining(String keyword,
			org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable pageable) {
		// TODO Auto-generated method stub
		return categoryDAO.findByNameContaining(keyword, null);
	}

	@Override
	public Page<Category> findAll(
			org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable pageable) {
		// TODO Auto-generated method stub
		return categoryDAO.findAll(null);
	}

	@Override
	public void addCategory(Category category) throws SQLException {
		categoryDAO.addCategory(category);
	}

	@Override
	public void updateCategory(Category category) throws SQLException {
		// TODO Auto-generated method stub
		categoryDAO.updateCategory(category);
	}

	@Override
	public void deleteCategory(String id) throws SQLException {
		// TODO Auto-generated method stub
		categoryDAO.deleteCategory(id);
	}

	@Override
	public Category getCategoryById(String id) throws SQLException {
		// TODO Auto-generated method stub
		return categoryDAO.getCategoryById(id);
	}

    
}
