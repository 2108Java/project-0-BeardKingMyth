package com.revature.service;

import com.revature.dao.AccountDao;
import com.revature.dao.EmployeeDao;
import com.revature.dao.UserDao;
import com.revature.models.User;

public class AuthenticatorImpl implements Authenticator{

	private UserDao uDao;
	private AccountDao aDao;
	
	public AuthenticatorImpl(UserDao uDao, AccountDao aDao) {
		super();
		this.uDao = uDao;
		this.aDao = aDao;
	}

	@Override
	public boolean authenticate(String username, String password) {
		User u = getUser(username); //might return a null!
		boolean success = false;
		
		if(u != null) {
			if(u.getPassword() != null && u.getPassword().equals(password)) {
				success = true;
			}
			
		}
		
		return success;
	}

	@Override
	public User getUser(String username) {
		User u = uDao.selectUserByUsername(username);
		return u;
	}

}
