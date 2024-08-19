package com.service;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.exam.Quiz;

public interface QuizService{
	
	public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizzes();
	
	public Quiz getQuiz(int qid);
	
	public void deleteQuiz(int qid);

}
