package com.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.exam.Category;
import com.service.CategoryServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	
	// add category
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category category1 = categoryServiceImpl.addCategory(category);
		return ResponseEntity.ok(category1);
	}
	
	//update category
	@PutMapping("/{cid}")
	public ResponseEntity<?> updateCategory(@PathVariable int cid, @RequestBody Category category) {
		category.setcId(cid);
		Category category1 = categoryServiceImpl.updateCategory(category);	  
		return ResponseEntity.ok(category1);
	}
	
	//get all category
	@GetMapping("/")
	public ResponseEntity<?> getCategories() {
		 Set<Category> set = categoryServiceImpl.getCategories();
		return ResponseEntity.ok(set);
	}
	
	//get single category
	@GetMapping("/{cid}")
	public ResponseEntity<?> getCategory(@PathVariable int cid) {
		  Category category = categoryServiceImpl.getCategory(cid);	  
		return ResponseEntity.ok(category);
	}
	
	
	//delete category
	@DeleteMapping("/{cid}")
	public String deleteCategory(@PathVariable int cid) {
	    categoryServiceImpl.deleteCategory(cid);	  
		return "delete Category....";
	}
	
	
	
	
	
	
	
		
	

}
