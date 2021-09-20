package com.revature.presentation;

import java.util.Scanner;

import org.apache.log4j.Logger;

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
		
		System.out.println("Please login before you do anything!");
		System.out.println("Username");
		
		String username = sc.nextLine();
		
		System.out.println("Password:");
		String password = sc.nextLine();
		
		boolean authenticated = auth.authenticate(username,password);
		User u = new User();
		
		if(authenticated) {
			
			 u = auth.getUser(username);
			
		}else {
			System.out.println("Invalid login!");
			System.out.println("Try again!");
		}
		
		return u;
		
	}
	
	public boolean registerMenu() {return false;};
	
	private static final Logger loggy = Logger.getLogger(PresentationImpl.class);

	@Override
	public void display() {
		Scanner sc = new Scanner(System.in);
		
		welcomeMessage();
		loggy.info("User sees the welcome menu");
		
		boolean loggingIn = true;
		User user = new User();
		Account account = new Account();
		while(loggingIn) {
			user = loginMenu(sc);
			
			if(user != null) {
				loggingIn = false;
				loggy.info("User logged in!");
			}else {
				loggy.warn("User failed to login");
			}
		}
		
		
		
		System.out.println("Welcome Account holder! What can we do for you today!");
		boolean running = true;
		
		
		while(running) {
			
			System.out.println("Here are your options!");
			System.out.println("1) Open an account");
			System.out.println("2) Check balance");
			System.out.println("3) Make a deposit");
			System.out.println("4) Make a withdrawal");
			System.out.println("0) Exit the application ");
			
			String input = sc.nextLine();
			
			switch(input) {
			
			case "1": 
				newAccountMenu(account, sc);
				break;
			case "2":
				addTodoMenu(user, sc);
				break;
			case "3":
				deleteTodoMenu(user);
				break;
			case "4":
				completeTodoMenu(user);
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

	private void newAccountMenu(Account account, Scanner sc) {
		// TODO Auto-generated method stub
		
	}
}
