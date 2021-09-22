package com.revature.dao;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;

public interface EmployeeDao {

	void updateUserApproval(User u, boolean b);
	
	void updateAccountApproval(Account a, boolean b);
	
	List<Account> viewUserAccounts(User u);
	
	List<User> viewUsers();
}
