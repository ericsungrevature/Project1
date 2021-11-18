package com.sung.example;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tester {
	@Id
	private String username;
	private String password;
	public Tester() {}
	public Tester(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
}
