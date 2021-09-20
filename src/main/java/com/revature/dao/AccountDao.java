package com.revature.dao;

import java.util.List;

import com.revature.models.Account;


public interface AccountDao {

	List<Account> selectAccountByUserId(int id);
}
