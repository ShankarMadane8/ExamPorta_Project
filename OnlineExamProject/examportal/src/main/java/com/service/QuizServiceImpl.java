package com.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.exam.Quiz;
import com.entitydao.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService{
	
	@Autowired
	private QuizRepository quizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		Quiz quiz2 = quizRepository.save(quiz);
		return quiz2;
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		Quiz quiz2 = quizRepository.save(quiz);
		return quiz2;
	}

	@Override
	public Set<Quiz> getQuizzes() {
		List<Quiz> list = quizRepository.findAll();
		return new HashSet<>(list);
	}

	@Override
	public Quiz getQuiz(int qid) {
		Optional<Quiz> optional = quizRepository.findById(qid);
		return optional.get();
	}

	@Override
	public void deleteQuiz(int qid) {
		quizRepository.deleteById(qid);
		
	}

}
