package com.service;

import java.util.Set;

import com.entity.exam.Category;

public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Set<Category> getCategories();
	
	public Category getCategory(int cid);
	
	public void deleteCategory(int cid);

}
