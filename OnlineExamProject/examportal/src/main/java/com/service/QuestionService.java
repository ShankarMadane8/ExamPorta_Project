package com.service;

import java.util.Set;

import com.entity.exam.Question;
import com.entity.exam.Quiz;

public interface QuestionService {
	
	
	public Question addQusestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getQuestions();
	
	public Question getQuestion(int quesId);
	
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
		
	public void deleteQuestion(int quesId);

}
