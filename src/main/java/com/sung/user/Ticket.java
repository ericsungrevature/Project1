package com.sung.user;

public class Ticket {
	private int id;
	private String username;
	private double value;
	private String status;
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}
	public double getValue() {return value;}
	public String getValueString() {return String.format("$%.2f", value);}
	public void setValue(double value) {this.value = Math.floor(value * 100) / 100;}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
}
