package com.revature.presentation;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.service.Authenticator;

public class PresentationImpl implements Presentation{

	private Authenticator auth;
	
	public PresentationImpl(Authenticator auth) {
		this.auth = auth;
	}
	
	public void welcomeMessage() {
		System.out.println("Welcome to my Banking App! ");
		
	}
	
	public User loginMenu(Scanner sc) {
		User u = new User();
		System.out.println("Please login before you do anything!");
		System.out.println("1) Create account.");
		System.out.println("2) Sign in to customer account.");
		System.out.println("3) Sign in to employee account.");
		String s = sc.nextLine();
		switch(s) {
		case "1":
			register(sc);
		case "2":
			u = customerLoginMenu(sc);
			break;
		case "3":
			u = employeeLoginMenu(sc);
			break;
		default:
			System.out.println("Invalid Input! Please try again.");
			loginMenu(sc);
			break;			
		}
		
		return u;
	}
	
	public void register(Scanner sc) {
		UserDao uDao = new UserDaoImpl();
		boolean duplicate = false;
		String username;
		String password;
		String fName;
		String lName;
		do {
			System.out.println("Please enter a username.");
			username = sc.nextLine();
			List<String> existing = uDao.selectAllUsernames();
			for (String s : existing) {
				if (username.equals(s)) {
					duplicate = true;
					System.out.println("Sorry, that username is already in use. \n Please try again.");
					break;
				}
			} 
		} while (duplicate);
		boolean matching = false;
		do {
			System.out.println("Please enter a password.");
			password = sc.nextLine();
			System.out.println("Please reenter your password.");
			String password2 = sc.nextLine();
			if(password.equals(password2)) {
				matching = true;
			} else {
				System.out.println("Sorry, your passwords did not match. \n Please try again.");
			}
		} while (!matching);
		System.out.println("Please enter your first name.");
		fName = sc.nextLine();
		System.out.println("Please enter your last name.");
		lName = sc.nextLine();
		User u = new User();
		u.setfName(fName);
		u.setlName(lName);
		u.setPassword(password);
		u.setUsername(username);
		uDao.addNewUser(u);

	}
	
	public User customerLoginMenu(Scanner sc) {
		System.out.println("Username");
		
		String username = sc.nextLine();
		User u = new User();
		System.out.println("Password:");
		String password = sc.nextLine();
		
		boolean authenticated = auth.authenticate(username,password);
		
		if(authenticated) {
			
			u = auth.getUser(username);
			loggy.info("Customer successfully logged in");
		}else {
			System.out.println("Invalid login!");
			System.out.println("Try again!");
			loggy.warn("Customer failed to login");
			customerLoginMenu(sc);
		}
		return u;
	}

	public User employeeLoginMenu(Scanner sc) {
		System.out.println("Username");
		
		String username = sc.nextLine();
		User u = new User();
		System.out.println("Password:");
		String password = sc.nextLine();
		
		boolean authenticated = auth.authenticate(username,password);
		
		if(authenticated) {
			
			u = auth.getUser(username);
			u.setEmployee(true);
			loggy.info("Employee successfully logged in");
					
		}else {
			System.out.println("Invalid login!\nTry again!");
			loggy.warn("Employee failed to login");
			employeeLoginMenu(sc);
		}
		return u;
	}
	
	private static final Logger loggy = Logger.getLogger(PresentationImpl.class);

	@Override
	public void signInMenu(Scanner sc) {
		
		
		welcomeMessage();
		loggy.info("User sees the welcome menu");
		
		boolean loggingIn = true;
		User user = new User();
		while(loggingIn) {
			user = loginMenu(sc);
			
			if(user != null) {
				loggingIn = false;
				loggy.info("User logged in!");	
				if(user.isEmployee()) {
					employeeDisplay(sc);
				} else {
					customerDisplay(sc, user);
				}
			}else {
				loggy.warn("User failed to login");
			}
		}
	}
	
	@Override
	public void employeeDisplay(Scanner sc) {
		boolean running = true;
		User u = new User();
		Account a = new Account();
		while (running) {
			System.out.println("Welcome to the Employee portal!");
			System.out.println("What would you like to do?");
			System.out.println("1) View users");
			System.out.println("2) View user accounts.");
			System.out.println("3) Review registration.");
			System.out.println("4) Review account creation.");
			System.out.println("5) Logout");
			System.out.println("0) Exit application");
			String s = sc.nextLine();
			switch (s) {
			case "1":
				u = chooseUser(sc);
				break;
			case "2":
				viewAccounts(u);
				break;
			case "3":
				reviewRegistration(u, sc);
				break;
			case "4":
				reviewAccount(a, sc);
				break;
			case "5":
				signInMenu(sc);
				break;
			case "0":
				running = false;
				System.out.println("Thanks for using my service!");				
				break;
			default:
				System.out.println("Invalid input!\nPlease try again.");
				break;
			}
		}
	}
	
	private void reviewAccount(Account a, Scanner sc) {
		EmployeeDao eDao = new EmployeeDaoImpl();
		System.out.println("Would you like to approve this account? (true/false)");
		boolean b = Boolean.parseBoolean(sc.nextLine());
		eDao.updateAccountApproval(a, b);
		
	}

	private void reviewRegistration(User u, Scanner sc) {
		EmployeeDao eDao = new EmployeeDaoImpl();
		System.out.println("Would you like to approve this user? (true/false)");
		boolean b = Boolean.parseBoolean(sc.nextLine());
		eDao.updateUserApproval(u, b);
		
	}

	private void viewAccounts(User u) {
		EmployeeDao eDao = new EmployeeDaoImpl();
		List<Account> a = eDao.viewUserAccounts(u);
		for(Account account: a) {
			System.out.println(account.toString());
			System.out.println("\n");
		}
		
	}

	private User chooseUser(Scanner sc) {
		EmployeeDao eDao = new EmployeeDaoImpl();
		List<User> u = eDao.viewUsers();
		for(User user: u) {
			System.out.println(user.toString());
		}
		System.out.println("Which user will you be working with? (Please enter an integer.)");
		int i = Integer.parseInt(sc.nextLine()) - 1;
		return u.get(i);
	}


	@Override
	public void customerDisplay(Scanner sc, User user) {
				
		
		Account account = new Account();
		
		System.out.println("Welcome Account holder! What can we do for you today!");
		boolean running = true;
		
		
		while(running) {
			
			System.out.println("Here are your options!");
			System.out.println("1) Open an account");
			System.out.println("2) View your accounts");
			System.out.println("3) Check balance");
			System.out.println("4) Make a deposit");
			System.out.println("5) Make a withdrawal");
			System.out.println("6) Send money");
			System.out.println("7) Recieve money");
			System.out.println("0) Exit the application ");
			
			String input = sc.nextLine();
			
			switch(input) {
			
			case "1": 
				newAccountMenu(account, user, sc);
				break;
			case "2":
				account = chooseAccount(user, sc);
				break;
			case "3":
				checkBalance(account);
				break;
			case "4":
				makeDepositMenu(account, sc);
				break;
			case "5":
				makeWithdrawalMenu(account, sc);
				break;
			case "6":
				sendTransfer(account, sc);
				break;
			case "7":
				recieveTransfer(account, sc);
				break;
			case "0":
				running = false;
				System.out.println("Thanks for using my service!");
				break;
			default: 
				System.out.println("Invalid input, try again!");
				
			}
		}
	}

	private void recieveTransfer(Account account, Scanner sc) {
		AccountDao aDao = new AccountDaoImpl();
		System.out.println("What is the id of the account you are transferring from?");
		int id = Integer.parseInt(sc.nextLine());
		aDao.acceptTransfer(account.getId(), id);
		loggy.info("User successfully accepts a transfer");
	}

	private void sendTransfer(Account account, Scanner sc) {
		AccountDao aDao = new AccountDaoImpl();
		System.out.println("What is the id of the account you are transferring to?");
		int id = Integer.parseInt(sc.nextLine());
		System.out.println("How much would you like to transfer?");
		double transfer = Double.parseDouble(sc.nextLine());
		if(transfer > 0) {
			double newBalance = aDao.selectBalanceByAccountID(account) - transfer;
			if(newBalance >= 0) {
				aDao.startNewTransfer(account.getId(), id, transfer);
				loggy.info("User successfully starts a transfer");
			} else {
				System.out.println("I'm sorry, you cannot transfer more money than you have.");
				loggy.warn("User tried to transfer more than their balance");
			}
		} else {
			System.out.println("I'm sorry, you must transfer a positive value.");
			loggy.warn("User tried to transfer a negative value.");
		}
		
	}

	private Account chooseAccount(User user, Scanner sc) {
		AccountDao aDao = new AccountDaoImpl();
		List<Account> a = aDao.selectAccountByUserId(user);
		for(Account account: a) {
			System.out.println(account.toString());
			System.out.println("\n\n");
		}
		System.out.println("Which account will you be using today?");
		int i = Integer.parseInt(sc.nextLine()) - 1;
		loggy.info("Customer chooses an account to work with");

		return a.get(i);
	}

	private void makeWithdrawalMenu(Account account, Scanner sc) {
		System.out.println("How much would you like to withdraw?");
		AccountDao aDao = new AccountDaoImpl();
		double withdrawal = Double.parseDouble(sc.nextLine());
		if(withdrawal > 0) {
			double newBalance = aDao.selectBalanceByAccountID(account) - withdrawal;
			if(newBalance >= 0) {
				aDao.updateBalanceByAccountID(account, newBalance);
				loggy.info("User successfully makes a withdrawal.");
				System.out.println("Your new balance is: " + aDao.selectBalanceByAccountID(account));
			} else {
				System.out.println("I'm sorry, you cannot withdraw more money than you have.");
				loggy.warn("User tried to withdraw more than their balance");
			}
		} else {
			System.out.println("I'm sorry, you must withdraw a positive value.");
			loggy.warn("User tried to withdraw a negative value.");
		}
	}

	private void makeDepositMenu(Account account, Scanner sc) {
		System.out.println("How much would you like to deposit?");
		AccountDao aDao = new AccountDaoImpl();
		double deposit = Double.parseDouble(sc.nextLine());
		if(deposit > 0) {
			double newBalance = aDao.selectBalanceByAccountID(account) + deposit;
			aDao.updateBalanceByAccountID(account, newBalance);
			loggy.info("User successfully makes a deposit.");
			System.out.println("Your new balance is: " + aDao.selectBalanceByAccountID(account));
		} else {
			System.out.println("I'm sorry, you must deposit a positive value.");
			loggy.warn("User tried to deposit a negative value.");
		}
	}

	private void checkBalance(Account account) {
		AccountDao aDao = new AccountDaoImpl();
		System.out.println("Your balance is: " + aDao.selectBalanceByAccountID(account));
		loggy.info("User check the balance of an account");

		
	}

	private void newAccountMenu(Account account, User u, Scanner sc) {
		AccountDao aDao = new AccountDaoImpl();
		System.out.println("What type of account will this be? (Checking/Savings/Both)");
		String accountType = sc.nextLine();
		System.out.println("What will your starting balance be?");
		double accountBalance = Double.parseDouble(sc.nextLine());
		account.setAccountType(accountType);
		account.setBalance(accountBalance);
		aDao.addNewAccount(account, u.getId());
		loggy.info("User creates a new account");

	}


}
