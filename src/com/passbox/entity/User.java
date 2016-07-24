package com.passbox.entity;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -8049048030831369480L;
	private String user_id;
	private String username;//用户名
	private String password;//密码
	private String privite_key;//用户私钥
	private String email;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public String getPrivite_key() {
		return privite_key;
	}
	public void setPrivite_key(String privite_key) {
		this.privite_key = privite_key;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
