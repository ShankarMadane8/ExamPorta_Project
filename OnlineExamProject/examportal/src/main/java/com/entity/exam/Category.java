package com.entity.exam;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	
	private String title;
	
	private String description;
	
	@OneToMany(mappedBy = "category",  cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Quiz> quizzes= new LinkedHashSet<>();
	
	
	
	

	public Category() {
		super();
	}

	public Category(int cId, String title, String description, Set<Quiz> quizzes) {
		super();
		this.cId = cId;
		this.title = title;
		this.description = description;
		this.quizzes = quizzes;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(Set<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
	
	
	

}
