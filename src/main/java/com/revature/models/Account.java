package com.revature.models;

public class Account {
	private int id;
	private String accountType;
	private double balance;
	private boolean approval = false;
	
	public Account() {
		super();
	}
	public Account(int id, String accountType, double balance, boolean b) {
		super();
		this.setId(id);
		this.setAccountType(accountType);
		this.setBalance(balance);
		this.setApproval(b);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", accountType=" + accountType + ", accountApproved=" + approval + ", accountBalance=" + balance + "]";
	}
	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}

}
