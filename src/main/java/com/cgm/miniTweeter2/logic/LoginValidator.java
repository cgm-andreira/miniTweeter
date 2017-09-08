package com.cgm.miniTweeter2.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginValidator {
	@Autowired DBManager dbManager;
	public User validateUser(Login login) {
		if(login == null) {
			return null;
		}
		User requestedUsername = dbManager.getUser(login.getUsername());
		if(requestedUsername == null) {
			return null;
		}
		//System.out.println("Found user " + login.getUsername() + " comparing password: " + login.getPassword());
		if(requestedUsername.getPassword().equals(login.getPassword())) {
			//System.out.println("Login password: " + requestedUsername.getPassword() + "; given password: " + login.getPassword());
			return requestedUsername;
		}
		return null;
	}
}
