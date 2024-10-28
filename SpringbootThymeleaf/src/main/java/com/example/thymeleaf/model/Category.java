package com.example.thymeleaf.model;

public class Category {
	 
	private String categoryId;
    private String categoryName;
    private String description;
    
    public Category() {
		// TODO Auto-generated constructor stub
	}
    
    public Category(String categoryId, String categoryName, String description) {
		// TODO Auto-generated constructor stub
    	this.categoryId = categoryId;
    	this.categoryName = categoryName;
    	this.description = description;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    

}