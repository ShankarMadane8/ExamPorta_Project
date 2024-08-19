package com.entitydao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
