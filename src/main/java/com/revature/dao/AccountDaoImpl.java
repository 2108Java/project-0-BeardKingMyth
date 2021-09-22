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

public class AccountDaoImpl implements AccountDao {

	ConnectionFactory connectionFactory = new ConnectionFactory();
	
	@Override
	public List<Account> selectAccountByUserId(User u) {

		String sql = "SELECT * FROM account_table WHERE fk_user_id = ?";
		List<Account> userAccounts = new ArrayList<>();
		
		try {
			Connection connection = connectionFactory.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, u.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				userAccounts.add(
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
		
		return userAccounts;
	}

	@Override
	public double selectBalanceByAccountID(Account a) {
		String sql = "SELECT account_balance FROM account_table WHERE account_id = ?";
		double balance = 0;
		try {
			Connection connection = connectionFactory.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, a.getId());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				balance = rs.getDouble("account_balance");
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

	@Override
	public boolean updateBalanceByAccountID(Account a, double d) {
		String sql = "UPDATE account_table SET account_balance = ? WHERE account_id = ?";
		try {
			Connection connection = connectionFactory.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setDouble(1, d);
			ps.setInt(2, a.getId());
			
			ps.executeQuery();
			
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void addNewAccount(Account account, int id) {
		String sql = "INSERT INTO account_table (fk_user_id, account_type, account_balance, is_approved) VALUES (?,?,?, ?)";
		
		PreparedStatement ps;
		
		try {
			Connection connection = connectionFactory.getConnection();
			
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.setString(2, account.getAccountType());
			ps.setDouble(3, account.getBalance());
			ps.setBoolean(4, account.isApproval());
			
			ps.executeQuery();
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void startNewTransfer(int id, int id2, double transfer) {
		String sql = "INSERT INTO pending_transfers (sending_account_id, recieving_account_id, transfer_amount) VALUES (?,?,?)";
		
		PreparedStatement ps;
		
		try {
			Connection connection = connectionFactory.getConnection();
			
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.setInt(2, id2);
			ps.setDouble(3, transfer);
			
			ps.executeQuery();
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sql = "SELECT account_balance FROM account_table WHERE account_id = ?";
		double balance = 0;
		try {
			Connection connection = connectionFactory.getConnection();
			
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				balance = rs.getDouble("account_balance");
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "UPDATE account_table SET account_balance = ? WHERE account_id = ?";
		balance = balance - transfer;
		try {
			Connection connection = connectionFactory.getConnection();
			
			ps = connection.prepareStatement(sql);
			
			ps.setDouble(1, balance);
			ps.setInt(2, id);
			
			ps.executeQuery();
			
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void acceptTransfer(int id, int id2) {
		String sql = "SELECT account_balance FROM account_table WHERE account_id = ?";
		
		PreparedStatement ps;

		
		double balance = 0;
		try {
			Connection connection = connectionFactory.getConnection();
			
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				balance = rs.getDouble("account_balance");
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "SELECT transfer_amount FROM pending_transfers WHERE recieving_account_id = ? AND sending_account_id = ?";
		double transfer = 0;
		
		try {
			Connection connection = connectionFactory.getConnection();
			
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.setInt(2, id2);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				transfer = rs.getDouble("transfer_amount");
			}
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "UPDATE account_table SET account_balance = ? WHERE account_id = ?";
		balance = balance + transfer;
		try {
			Connection connection = connectionFactory.getConnection();
			
			ps = connection.prepareStatement(sql);
			
			ps.setDouble(1, balance);
			ps.setInt(2, id);
			
			ps.executeQuery();
			
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sql = "DELETE FROM pending_transfers WHERE recieving_account_id = ? AND sending_account_id = ?";
		
		try {
			Connection connection = connectionFactory.getConnection();
			
			ps = connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			ps.setInt(2, id2);
			
			ps.executeQuery();
			
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
