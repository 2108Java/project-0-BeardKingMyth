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

public class UserDaoImpl implements UserDao {

	ConnectionFactory connectionFactory = new ConnectionFactory();

	@Override
	public User selectUserByUsername(String username) {
		String sql = "SELECT * FROM user_table  WHERE username = ?";
			
			PreparedStatement ps;
			User u = new User();
			try {
				
				Connection connection = connectionFactory.getConnection();
				
							
				ps = connection.prepareStatement(sql);
				
				ps.setString(1, username);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					u.setId(rs.getInt("user_id"));
					u.setfName(rs.getString("f_name"));
					u.setlName(rs.getString("l_name"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("user_password"));
					u.setApproval(rs.getBoolean("is_approved"));
					u.setEmployee(rs.getBoolean("is_employee"));
				}
				
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return u;
		}

	@Override
	public void addNewUser(User u) {
		String sql = "INSERT INTO user_table (f_name, l_name, username, user_password, is_approved, is_employee) VALUES (?,?,?,?,?,?)";
		
			PreparedStatement ps;
			
			try {
				Connection connection = connectionFactory.getConnection();
				
				ps = connection.prepareStatement(sql);
				
				ps.setString(1, u.getfName());
				ps.setString(2, u.getlName());
				ps.setString(3, u.getUsername());
				ps.setString(4, u.getPassword());
				ps.setBoolean(5, u.isApproval());
				ps.setBoolean(6, u.isApproval());
				
				ps.executeQuery();
				
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	@Override
	public List<String> selectAllUsernames() {
		String sql = "SELECT username FROM user_table;";
		List<String> usernames = new ArrayList<>();
		
		try {
			Connection connection = connectionFactory.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				usernames.add(new String(rs.getString("username")));
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usernames;
	}
		
}


