package com.cgm.miniTweeter2.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgm.miniTweeter2.DTO.MessageDTO;
import com.cgm.miniTweeter2.DTO.UserDTO;
import com.cgm.miniTweeter2.contract.CommonDataStore;
import com.cgm.miniTweeter2.dbObjects.Message;
import com.cgm.miniTweeter2.dbObjects.User;
import com.cgm.miniTweeter2.logic.Login;
import com.cgm.miniTweeter2.repository.MessageDAO;
import com.cgm.miniTweeter2.repository.UserDAO;

@Service
public class DataStore implements CommonDataStore {
	@Autowired
	MessageDAO messageDAO;
	@Autowired
	UserDAO userDAO;

	@Override
	public void addMessage(MessageDTO newMessageDTO) {
		Message newMessage = new Message();

		String message = newMessageDTO.getMessage();
		User currentUser = userDAO.getUserByUsername(newMessageDTO.getUser().getUsername());
		newMessage.setMessage(message);
		newMessage.setUser(currentUser);
		currentUser.addMessage(newMessage);
		// userDAO.update(currentUser);

		messageDAO.save(newMessage);
	}

	@Override
	public void removeMessage(MessageDTO messageToRemove) {
		messageDAO.delete(messageToRemove.getId());
	}

	@Override
	public List<MessageDTO> getUserMessages(UserDTO user) {
		List<MessageDTO> messagesDTO = new ArrayList<MessageDTO>();
		for (Message message : messageDAO.getMessagesByUserId(userDAO.getUserByUsername(user.getUsername()).getId())) {
			messagesDTO.add(message.asDTOnoReference());
		}
		return messagesDTO;
	}

	@Override
	public void addUser(UserDTO newUser) {
		User user = new User();
		user.setId(Integer.MAX_VALUE);
		user.setName(newUser.getName());
		user.setUsername(newUser.getUsername());
		userDAO.save(user);
	}

	@Override
	public void removeUser(UserDTO userToRemove) {
		userDAO.remove(userDAO.getUserByUsername(userToRemove.getUsername()));
	}

	@Override
	public UserDTO getUserById(int id) {
		return userDAO.getUserById(id).asDTOnoReference();
	}

	@Override
	public UserDTO getUserByUsername(String username) {
		User user = userDAO.getUserByUsername(username);
		if (user != null) {
			return user.asDTO();
		}
		return null;
	}

	@Override
	public List<UserDTO> findUsersByName(String name) {
		List<UserDTO> foundUsers = new ArrayList<UserDTO>();
		List<User> daoSearchResults = userDAO.findUserByName(name);
		for (User user : daoSearchResults) {
			foundUsers.add(user.asDTOnoReference());
		}
		return foundUsers;
	}

	@Override
	public List<UserDTO> findUsersByUsername(String username) {
		List<UserDTO> foundUsers = new ArrayList<UserDTO>();
		List<User> daoSearchResults = userDAO.findUserByUsername(username);
		for (User user : daoSearchResults) {
			foundUsers.add(user.asDTOnoReference());
		}
		return foundUsers;
	}

	@Override
	public void addFriend(UserDTO user, UserDTO friend) {
		User currentUser = userDAO.getUserByUsername(user.getUsername());
		User currentUserFriend = userDAO.getUserByUsername(friend.getUsername());
		if (currentUser != null && currentUserFriend != null) {
			currentUser.addFriend(currentUserFriend);
			userDAO.update(currentUser);
		}
	}

	@Override
	public void removeFriend(UserDTO user, UserDTO friend) {
		User currentUser = userDAO.getUserByUsername(user.getUsername());
		User currentUserFriend = userDAO.getUserByUsername(friend.getUsername());
		if (currentUser != null && currentUserFriend != null) {
			currentUser.removeFriend(currentUserFriend);
			userDAO.update(currentUser);
		}
	}

	@Override
	public List<UserDTO> getUserFriends(UserDTO user) {
		return userDAO.getUserByUsername(user.getUsername()).asDTO().getFriends();
	}

	@Override
	public List<MessageDTO> getUserAndFriendsMessages(UserDTO user) {
		List<MessageDTO> messages = new ArrayList<MessageDTO>();
		messages.addAll(getUserMessages(user));

		for (UserDTO friend : getUserFriends(user)) {
			for (Message message : messageDAO.getMessagesByUserId(userDAO.getUserByUsername(friend.getUsername()).getId())) {
				messages.add(message.asDTOnoReference());
			}
		}
		return messages;
	}

	@Override
	public UserDTO checkLogin(Login login) {
		User user = userDAO.getUserByUsername(login.getUsername());
		if (user == null) {
			return null;
		} else {
			if (user.getPassword().equals(login.getPassword())) {
				return user.asDTO();
			}
		}
		return null;
	}

	@Override
	public List<UserDTO> findUsersByKeyword(String keyword) {
		List<UserDTO> results = new ArrayList<UserDTO>();
		
		for(User user : userDAO.findUserByKeyword(keyword)) {
			results.add(user.asDTOnoReference());
		}
		
		return results;
	}

}
