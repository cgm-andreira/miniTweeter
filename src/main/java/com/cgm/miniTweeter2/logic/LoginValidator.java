package com.cgm.miniTweeter2.logic;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgm.miniTweeter2.DTO.UserDTO;
import com.cgm.miniTweeter2.contract.CommonDataStore;
import com.cgm.miniTweeter2.contract.LoginValidatorInterface;
//import com.cgm.miniTweeter2.contract.UserDataStore;
//import com.cgm.miniTweeter2.dbObjects.User;

@Service
public class LoginValidator implements LoginValidatorInterface{
	@Autowired
	CommonDataStore dataStore;
	public UserDTO validateLogin(Login login) {
		if(login == null) {
			return null;
		}
		return dataStore.checkLogin(login);
	}
}
