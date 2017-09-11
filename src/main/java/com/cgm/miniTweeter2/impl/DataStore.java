package com.cgm.miniTweeter2.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgm.miniTweeter2.contract.CommonDataStore;
import com.cgm.miniTweeter2.dbObjects.Message;
import com.cgm.miniTweeter2.dbObjects.User;
import com.cgm.miniTweeter2.repository.MessageDAO;
import com.cgm.miniTweeter2.repository.UserDAO;

@Service
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
		return messageDAO.getMessagesByUserId(user.getId());
	}

	@Override
	public void addUser(User newUser) {
		userDAO.save(newUser);
	}

	@Override
	public void removeUser(User userToRemove) {
		userDAO.remove(userToRemove);
	}

	@Override
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	@Override
	public List<User> findUsersByName(String name) {
		//to do
		return null;
	}

	@Override
	public List<User> findUsersByUsername(String username) {
		//to do
		return null;
	}

	@Override
	public void addFriend(User user, User friend) {
		user.addFriend(friend);
	}

	@Override
	public void removeFriend(User user, User friend) {
		user.removeFriend(friend);
	}

	@Override
	public List<User> getUserFriends(User user) {
		return user.getFriends();
	}

	@Override
	public List<Message> getUserAndFriendsMessages(User user) {
		List<Message> messages = new ArrayList<Message>();
		messages.addAll(messageDAO.getMessagesByUserId(user.getId()));
		for(User friend : getUserFriends(user)) {
			messages.addAll(messageDAO.getMessagesByUserId(friend.getId()));
		}
		return messages;
	}

}
