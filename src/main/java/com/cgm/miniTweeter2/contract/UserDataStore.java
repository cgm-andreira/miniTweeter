package com.cgm.miniTweeter2.contract;

import java.util.List;

import com.cgm.miniTweeter2.DTO.UserDTO;
//import com.cgm.miniTweeter2.dbObjects.User;

public interface UserDataStore {
	
	void addUser(UserDTO newUser);
	void removeUser(UserDTO userToRemove);
	UserDTO getUserById(int id);
	UserDTO getUserByUsername(String username);
	List<UserDTO> findUsersByName(String name);
	List<UserDTO> findUsersByUsername(String username);
}
