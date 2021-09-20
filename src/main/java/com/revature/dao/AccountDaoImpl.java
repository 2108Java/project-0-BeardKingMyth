package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.util.ConnectionFactory;

public class AccountDaoImpl implements AccountDao {

	ConnectionFactory connectionFactory = new ConnectionFactory();
	
	@Override
	public List<Account> selectAccountByUserId(int id) {

		String sql = "SELECT * FROM todo_table WHERE fk_user_id = ?";
		List<Account> userAccounts = new ArrayList<>();
		
		try {
			Connection connection = connectionFactory.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				userAccounts.add(
						new Account(rs.getInt("account_id"),
								rs.getString("account_type"), 
								rs.getDouble("account_balance"))
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userAccounts;
	}

}
