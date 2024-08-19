package com.entitydao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
