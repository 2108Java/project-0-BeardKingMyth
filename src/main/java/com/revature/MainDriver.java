package com.revature;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.presentation.Presentation;
import com.revature.presentation.PresentationImpl;
import com.revature.service.Authenticator;
import com.revature.service.AuthenticatorImpl;

import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;

public class MainDriver {

	public static void main(String[] args) {
		UserDao uDao = new UserDaoImpl();
		AccountDao aDao = new AccountDaoImpl();
		Scanner sc = new Scanner(System.in);
		
		Authenticator auth = new AuthenticatorImpl(uDao,aDao);
		
		
		Presentation presentation = new PresentationImpl(auth);
		
		presentation.signInMenu(sc);
		

	}

}
