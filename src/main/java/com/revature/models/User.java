package com.revature.models;

import java.util.List;

public class User {
	private int id;
	private String username;
	private String password;
	
	//personal detais
	private String name;
	private List<Account> accountList;
	
	public User() {
		super();
	}

	public User(int id, String username, String password, String name, List<Account> accountList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.accountList = accountList;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setTodoList(List<Account> accountList) {
		this.accountList = accountList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", accountList="
				+ accountList + "]";
	}
}
