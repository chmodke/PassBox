package com.passbox.entity;

import java.io.Serializable;

public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	private String book_id;
	private String user_id;
	private String account_name;//账户名
	private String account_passwd;//账户密码
	private String account_desc;//账户描述
	private String account_website;//账户网站
	private String account_phone;//账户手机
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public String getAccount_name() {
		return account_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_passwd() {
		return account_passwd;
	}
	public void setAccount_passwd(String account_passwd) {
		this.account_passwd = account_passwd;
	}
	public String getAccount_desc() {
		return account_desc;
	}
	public void setAccount_desc(String account_desc) {
		this.account_desc = account_desc;
	}
	public String getAccount_website() {
		return account_website;
	}
	public void setAccount_website(String account_website) {
		this.account_website = account_website;
	}
	public String getAccount_phone() {
		return account_phone;
	}
	public void setAccount_phone(String account_phone) {
		this.account_phone = account_phone;
	}
	
}
