package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class EmployeeDaoImpl implements EmployeeDao {
	
	ConnectionFactory connectionFactory = new ConnectionFactory();

	@Override
	public void updateUserApproval(User u, boolean b) {

		String sql = "UPDATE user_table SET is_approved = ? WHERE user_id = ?";
		
		PreparedStatement ps;
		try {
			
			Connection connection = connectionFactory.getConnection();
			
						
			ps = connection.prepareStatement(sql);
			
			ps.setBoolean(1, b);
			ps.setInt(2, u.getId());
			
			ps.executeQuery();
			
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAccountApproval(Account a, boolean b) {

		String sql = "UPDATE account_table SET is_approved = ? WHERE account_id = ?";
		
		PreparedStatement ps;
		try {
			
			Connection connection = connectionFactory.getConnection();
			
						
			ps = connection.prepareStatement(sql);
			
			ps.setBoolean(1, b);
			ps.setInt(2, a.getId());
			
			ps.executeQuery();
			
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Account> viewUserAccounts(User u) {
		List<Account> a = new ArrayList<>();
		
		String sql = "SELECT * FROM account_table WHERE fk_user_id = ?";
		
		PreparedStatement ps;
		
		try {
			
			Connection connection = connectionFactory.getConnection();
			
						
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, u.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				a.add(
						new Account(rs.getInt("account_id"),
								rs.getString("account_type"), 
								rs.getDouble("account_balance"),
								rs.getBoolean("is_approved"))
						);
			}
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<User> viewUsers() {
		List<User> u = new ArrayList<>();
		
		String sql = "SELECT * FROM user_table";

		PreparedStatement ps;
		
		try {
			
			Connection connection = connectionFactory.getConnection();
			
						
			ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				u.add(
						new User(rs.getInt("user_id"),
								rs.getString("username"), 
								rs.getString("user_password"),
								rs.getString("f_name"),
								rs.getString("l_name"),
								rs.getBoolean("is_approved"),
								rs.getBoolean("is_employee"))
						);
			}
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

}
