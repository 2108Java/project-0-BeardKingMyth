package com.revature;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.presentation.Presentation;
import com.revature.presentation.PresentationImpl;
import com.revature.service.Authenticator;
import com.revature.service.AuthenticatorImpl;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;

public class MainDriver {

	public static void main(String[] args) {
		UserDao uDao = new UserDaoImpl();
		AccountDao aDao = new AccountDaoImpl();
//		//UserDao uDao = new BetterImpl();
//		
//		System.out.println(uDao.selectUserByUsername("fake"));
//		
//		
//		User u = uDao.selectUserByUsername("fake");
//		
//		u.setTodoList(tDao.selectActivitesByUserId(u.getId()));
//		
//		System.out.println(u);
		
		Authenticator auth = new AuthenticatorImpl(uDao,aDao);
		
//		System.out.println(auth.getUser("fake"));
		
		Presentation presentation = new PresentationImpl(auth);
		
		presentation.display();
	}

}
