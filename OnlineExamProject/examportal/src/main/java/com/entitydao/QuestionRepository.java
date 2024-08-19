package com.entitydao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.exam.Question;
import com.entity.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	public Set<Question> findQuestionByQuiz(Quiz quiz);

}
