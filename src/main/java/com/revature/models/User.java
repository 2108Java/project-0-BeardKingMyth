package com.revature.models;

import java.util.List;

public class User {
	private int id;
	private String username;
	private String password;
	
	//personal detais
	private String fName;
	private String lName;
	private List<Account> accountList;
	private boolean isEmployee = false;
	private boolean approval = false;
	
	public User() {
		super();
	}

	public User(int id, String username, String password, String fName, String lName, boolean approval, boolean isEmployee) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.approval = approval;
		this.isEmployee = isEmployee;
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

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", first name=" + fName + ", last name=" + lName + ", accountList="
				+ accountList + "]";
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approved) {
		this.approval = approved;
		
	}
}
