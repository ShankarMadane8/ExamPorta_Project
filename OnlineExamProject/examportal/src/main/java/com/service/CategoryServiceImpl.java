package com.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.exam.Category;
import com.entitydao.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		Category category1 = categoryRepository.save(category);
		return category1;
	}

	@Override
	public Category updateCategory(Category category) {
		Category category1 = categoryRepository.save(category);
		return category1;
	}

	@Override
	public Set<Category> getCategories() {
		List<Category> list = categoryRepository.findAll();
		return new LinkedHashSet<>(list);
	}

	@Override
	public Category getCategory(int cid) {
		Optional<Category> optional = categoryRepository.findById(cid);
		return optional.get();
	}

	@Override
	public void deleteCategory(int cid) {
		categoryRepository.deleteById(cid);
		
	}

}
