package com.stackroute.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	public User(String string, User user, byte[] bs) {

	}

	
	
	
	
	
	











	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(length = 30)
	private String email;
	@Column(length = 30)
	private String username;
	@Column(length = 30)
	private String password;
	@Column(name="picByte")
	private byte[] profileimage;
	public byte[] getProfileimage() {
		return profileimage;
	}

	public User(int id, String email, String username, String password, byte[] profileimage, long mobileno,
			boolean status) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.profileimage = profileimage;
		this.mobileno = mobileno;
		this.status = status;
	}

	public void setProfileimage(byte[] profileimage) {
		this.profileimage = profileimage;
	}
















	private long mobileno;
	@Column(length=10,columnDefinition = "boolean default false" )
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public boolean getStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}







	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobileno() {
		return mobileno;
	}

	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password
				+ ", profileimage=" + Arrays.toString(profileimage) + ", mobileno=" + mobileno + ", status=" + status
				+ "]";
	}
	














	

	
}

