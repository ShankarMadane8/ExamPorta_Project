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
import com.entity.exam.Quiz;
import com.service.QuizServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private QuizServiceImpl quizServiceImpl;
	
	
	//add Quiz
	@PostMapping("/")
	public Quiz addQuiz(@RequestBody Quiz quiz) {
		System.err.println("RequestBody: "+quiz);
		Quiz quiz2 = quizServiceImpl.addQuiz(quiz);
		System.err.println("---return quiz-- "+quiz2);
		return quiz2;
	}
	
	
	

	//update quiz
	@PutMapping("/{qid}")
	public ResponseEntity<?> updateQuiz(@PathVariable int qid, @RequestBody Quiz quiz) {
		quiz.setqId(qid);
		Quiz quiz2 = quizServiceImpl.updateQuiz(quiz);	  
		return ResponseEntity.ok(quiz2);
	}
	
	//get all Quiz
	@GetMapping("/")
	public ResponseEntity<?> getQuizzes() {
		 System.out.println("get all Quiz....");
		 Set<Quiz> set = quizServiceImpl.getQuizzes();
		return ResponseEntity.ok(set);
	}
	
	//get single Quiz
	@GetMapping("/{qid}")
	public ResponseEntity<?> getQuiz(@PathVariable int qid) {
		 System.out.println("get single quiz.....");
		  Quiz quiz = quizServiceImpl.getQuiz(qid);	  
		return ResponseEntity.ok(quiz);
	}
	
	
	//delete Quiz
	@DeleteMapping("/{qid}")
	public void deleteQuiz(@PathVariable int qid) {
	    quizServiceImpl.deleteQuiz(qid);	  
		
	}

}
