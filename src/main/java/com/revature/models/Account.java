package com.revature.models;

public class Account {
	private int id;
	private String accountType;
	private double balance;
	
	public Account() {
		super();
	}
	public Account(int id, String accountType, double balance) {
		super();
		this.setId(id);
		this.setAccountType(accountType);
		this.setBalance(balance);
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
		return "Account [id=" + id + ", accountType=" + accountType + ", balance=" + balance + "]";
	}

}
