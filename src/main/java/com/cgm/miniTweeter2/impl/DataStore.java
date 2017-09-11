package com.cgm.miniTweeter2.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cgm.miniTweeter2.contract.CommonDataStore;
import com.cgm.miniTweeter2.dbObjects.Message;
import com.cgm.miniTweeter2.dbObjects.User;
import com.cgm.miniTweeter2.repository.MessageDAO;
import com.cgm.miniTweeter2.repository.UserDAO;

public class DataStore implements CommonDataStore {
	@Autowired
	MessageDAO messageDAO;
	@Autowired
	UserDAO userDAO;
	
	@Override
	public void addMessage(Message newMessage) {
		messageDAO.save(newMessage);
	}

	@Override
	public void removeMessage(Message messageToRemove) {
		messageDAO.remove(messageToRemove);
	}

	@Override
	public List<Message> getUserMessage(User user) {
		List<Message> outputList = new ArrayList<Message>();
		
		
		
		return outputList;
	}

	@Override
	public void addUser(User newUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(User userToRemove) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsersByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsersByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFriend(User user, User friend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFriend(User user, User friend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getUserFriends(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> getUserAndFriendsMessages(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
