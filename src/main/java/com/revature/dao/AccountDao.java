package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;


public interface AccountDao {

	List<Account> selectAccountByUserId(User u);
	
	public double selectBalanceByAccountID(Account a);
	
	public boolean updateBalanceByAccountID(Account a, double d);

	void addNewAccount(Account account, int id);

	void startNewTransfer(int id, int id2, double transfer);

	void acceptTransfer(int id, int id2);
	
}
