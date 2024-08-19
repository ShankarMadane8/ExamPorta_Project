package com.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.exam.Question;
import com.entity.exam.Quiz;
import com.entitydao.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQusestion(Question question) {
		Question question2 = questionRepository.save(question);
		return question2;
	}

	@Override
	public Question updateQuestion(Question question) {
		Question question2 = questionRepository.save(question);
		return question2;
	}

	@Override
	public Set<Question> getQuestions() {
		List<Question> list = questionRepository.findAll();
		return new LinkedHashSet<>(list);
	}

	@Override
	public Question getQuestion(int quesId) {
		Optional<Question> optional = questionRepository.findById(quesId);
		return optional.get();
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		Set<Question> quiz2 = questionRepository.findQuestionByQuiz(quiz);
		return quiz2;
	}

	@Override
	public void deleteQuestion(int quesId) {
		questionRepository.deleteById(quesId);
		
	}

}
