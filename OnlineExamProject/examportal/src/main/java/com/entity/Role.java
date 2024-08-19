package com.entity;

import java.util.HashSet;
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
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roll_id;	
	private String roll_name;
	
	@OneToMany(mappedBy = "role",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> user_role=new HashSet<>();
	
	
	
	

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Role(int roll_id, String roll_name) {
		super();
		this.roll_id = roll_id;
		this.roll_name = roll_name;
	}
	
	public Set<UserRole> getUser_role() {
		return user_role;
	}

	public void setUser_role(Set<UserRole> user_role) {
		this.user_role = user_role;
	}

	@Override
	public String toString() {
		return "Role [roll_id=" + roll_id + ", roll_name=" + roll_name + "]";
	}
	
	

	public int getRoll_id() {
		return roll_id;
	}
	public void setRoll_id(int roll_id) {
		this.roll_id = roll_id;
	}
	public String getRoll_name() {
		return roll_name;
	}
	public void setRoll_name(String roll_name) {
		this.roll_name = roll_name;
	}
	
	

}
