package com.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.exam.Question;
import com.entity.exam.Quiz;
import com.service.QuestionService;
import com.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<Question> addQusetion(@RequestBody Question question) {
		Question question2 = questionService.addQusestion(question);
		return ResponseEntity.ok(question2);
	}

	@PutMapping("/{quesId}")
	public ResponseEntity<Question> updateQusetion(@RequestBody Question question, @PathVariable int quesId) {
		question.setQuesId(quesId);
		Question question2 = questionService.updateQuestion(question);
		return ResponseEntity.ok(question2);
	}

	@GetMapping("/")
	public ResponseEntity<?> getQusetions() {
		System.out.println("get questions");
		Set<Question> set = questionService.getQuestions();
		return ResponseEntity.ok(set);
	}

	@GetMapping("/{quesId}")
	public ResponseEntity<Question> getQusetion(@PathVariable int quesId) {
		Question question = questionService.getQuestion(quesId);
		return ResponseEntity.ok(question);
	}
	
	//get Questions With Answer
	@GetMapping("/all/quiz/{qid}")
	public ResponseEntity<?> getQusetionOfQuizWithAnswe(@PathVariable int qid) {

		Quiz quiz = quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestions();
		// Set<Question> set = questionService.getQuestionsOfQuiz(quiz);
		List<Question> list = new ArrayList<>(questions);
		System.out.println("******* total Question present in Quiz:" + list.size() + " diplay question:"
				+ quiz.getNumberOfQuestions() + " *****");
		if (list.size() > quiz.getNumberOfQuestions()) {
			list = list.subList(0, quiz.getNumberOfQuestions());
			System.out.println(list.size());
		}

		System.err.println(list);

		return ResponseEntity.ok(list);
	}

	
	//get Question without answer
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQusetionOfQuiz(@PathVariable int qid) {

		Quiz quiz = quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestions();
		// Set<Question> set = questionService.getQuestionsOfQuiz(quiz);
		List<Question> list = new ArrayList<>(questions);
		System.out.println("******* total Question present in Quiz:" + list.size() + " diplay question:"
				+ quiz.getNumberOfQuestions() + " *****");
		
		
		if (list.size() > quiz.getNumberOfQuestions()) {		
			list = list.subList(0, quiz.getNumberOfQuestions());
			System.out.println(list.size());
		}
		
		for(Question question:list) {
			question.setAnswer(null);
		}

		System.err.println(list);

		return ResponseEntity.ok(list);
	}

	@DeleteMapping("/{quesId}")
	public String deleteQusetion(@PathVariable int quesId) {
		questionService.deleteQuestion(quesId);
		return "delete Qusetion";
	}

	// eval Quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {

		System.out.println(questions);

		double marksGot = 0.0;
		int correctAnswer = 0;
		int attempted = 0;
		double marksSingle=0.0;

		for(Question q:questions) {
			String givenAnswer = q.getGivenAnswer();
			Question question = questionService.getQuestion(q.getQuesId());
			System.out.println(givenAnswer + "==" + question.getAnswer());
		    if(question.getAnswer().equals(q.getGivenAnswer())) {
		    	correctAnswer++;
		    	double size = questions.size();
		    	marksSingle=questions.get(0).getQuiz().getMaxMarks()/size;
                marksGot+=marksSingle;
		    }
		    if(q.getGivenAnswer()!=null) {
		    	attempted++;
		    }
		}
		double size = questions.size();
		System.out.println(marksSingle+"=" +questions.get(0).getQuiz().getMaxMarks()/size);
		Map<Object, Object> map=Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted);
		return ResponseEntity.ok(map);

	}

}
