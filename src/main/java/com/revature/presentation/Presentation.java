package com.revature.presentation;

import java.util.Scanner;

import com.revature.models.User;

public interface Presentation {

	public void signInMenu(Scanner sc);

	void customerDisplay(Scanner sc, User user);

	void employeeDisplay(Scanner sc);
	

}
