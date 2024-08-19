package com.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"userName","email"})})
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique = true)
	private String userName;
	
	private String firstName;
	private String lastName;
	private String password;
	
	private String profile;
	
	@Lob
    private byte[] image;
	
	
	@Column(unique = true)
	private String email;
	
	private String phone;
	private boolean enabled=true;
	
	
	
	
	public byte[] getImage() {
		return image;
	}



	public void setImage(byte[] image) {
		this.image = image;
	}



	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> user_role= new HashSet<UserRole>();
	
	
	
	public User() {
		super();
	}



	

	public User(int id, String userName, String firstName, String lastName, String password, String profile,
			byte[] image, String email, String phone, boolean enabled, Set<UserRole> user_role) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.profile = profile;
		this.image = image;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.user_role = user_role;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public String getProfile() {
		return profile;
	}



	public void setProfile(String profile) {
		this.profile = profile;
	}



	public Set<UserRole> getUser_role() {
		return user_role;
	}



	public void setUser_role(Set<UserRole> user_role) {
		this.user_role = user_role;
		
	}

	
	//----------------------------UserDetails----------------------------------



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Authority> set=new HashSet<>();
		
		user_role.forEach(userRole -> {
			set.add(new Authority(userRole.getRole().getRoll_name()) );
		});
		
		return set;
	}



	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
		
	
	
	
		
	

}
