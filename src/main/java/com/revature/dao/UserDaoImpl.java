package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserDaoImpl implements UserDao {

	ConnectionFactory connectionFactory = new ConnectionFactory();

	@Override
	public User selectUserByUsername(String username) {
		String sql = "SELECT * FROM users_table  WHERE username = ?";
			
			PreparedStatement ps;
			User u = new User();
			try {
				
				Connection connection = connectionFactory.getConnection();
				
							
				ps = connection.prepareStatement(sql);
				
				ps.setString(1, username);
				
				ResultSet rs = ps.executeQuery();
				
	//			System.out.println("Query executed!");
	//			System.out.println(ps.toString());
				
				while(rs.next()) {
					u.setId(rs.getInt("user_id"));
					u.setName(rs.getString("user_real_name"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("user_password"));
				}
				
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return u;
		}
		
}


