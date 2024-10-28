package com.example.thymeleaf.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.thymeleaf.model.Category;
import com.example.thymeleaf.service.CategoryService;
import com.example.thymeleaf.serviceimpl.CategoryServiceImpl;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService = new CategoryServiceImpl();

    @GetMapping
    public String viewHomePage(Model model, 
                               @RequestParam(defaultValue = "1") int page, 
                               @RequestParam(defaultValue = "") String keyword) {
        List<Category> categories;
        try {
        	categories = categoryService.getAllCategories(page, keyword);
        } catch (SQLException e) {
            e.printStackTrace();
            
            return "error"; 
        }


        model.addAttribute("categories", categories);

        return "categories/index";
    }

    @GetMapping("/new")
    public String showNewCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/new";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        try {
            categoryService.addCategory(category);
        } catch (SQLException e) {
            e.printStackTrace();
            
            return "error"; 
        }
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") String id, Model model) {
        Category category;
        try {
            category = categoryService.getCategoryById(id);
            model.addAttribute("category", category);
        } catch (SQLException e) {
            e.printStackTrace();
            
            return "error"; 
        }
        return "categories/edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id) {
        try {
            categoryService.deleteCategory(id);
        } catch (SQLException e) {
            e.printStackTrace();
            
            return "error"; 
        }
        return "redirect:/categories";
    }
}
