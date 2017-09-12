package com.cgm.miniTweeter2.contract;

import com.cgm.miniTweeter2.DTO.UserDTO;
import com.cgm.miniTweeter2.dbObjects.User;
import com.cgm.miniTweeter2.logic.Login;

public interface LoginValidatorInterface {
	UserDTO validateLogin(Login login);
}
