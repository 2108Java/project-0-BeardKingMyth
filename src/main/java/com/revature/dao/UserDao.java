package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	User selectUserByUsername(String username);
	
	void addNewUser(User u);
	
	List<String> selectAllUsernames();
}
