package com.cgm.miniTweeter2.logic;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgm.miniTweeter2.contract.LoginValidatorInterface;
import com.cgm.miniTweeter2.contract.UserDataStore;
import com.cgm.miniTweeter2.dbObjects.User;

@Service
public class LoginValidator implements LoginValidatorInterface{
	public User validateLogin(Login login, UserDataStore userStore) {
		if(login == null) {
			return null;
		}
		User requestedUsername = userStore.getUserByUsername(login.getUsername());
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
