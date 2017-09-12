package com.cgm.miniTweeter2.contract;

import java.util.List;

import com.cgm.miniTweeter2.DTO.MessageDTO;
import com.cgm.miniTweeter2.DTO.UserDTO;
//import com.cgm.miniTweeter2.dbObjects.Message;
//import com.cgm.miniTweeter2.dbObjects.User;
import com.cgm.miniTweeter2.logic.Login;

public interface CommonDataStore extends MessageDataStore, UserDataStore{
	void addFriend(UserDTO user, UserDTO friend);
	void removeFriend(UserDTO user, UserDTO friend);
	List<UserDTO> getUserFriends(UserDTO user);
	List<MessageDTO> getUserAndFriendsMessages(UserDTO user);
	UserDTO checkLogin(Login login);
}
