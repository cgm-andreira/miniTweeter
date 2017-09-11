package com.cgm.miniTweeter2.contract;

import java.util.List;

import com.cgm.miniTweeter2.dbObjects.User;

public interface UserDataStore {
	
	void addUser(User newUser);
	void removeUser(User userToRemove);
	User getUserById(int id);
	User getUserByUsername(String username);
	List<User> findUsersByName(String name);
	List<User> findUsersByUsername(String username);
}
