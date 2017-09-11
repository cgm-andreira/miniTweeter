package com.cgm.miniTweeter2.contract;

import java.util.List;

import com.cgm.miniTweeter2.dbObjects.Message;
import com.cgm.miniTweeter2.dbObjects.User;

public interface CommonDataStore extends MessageDataStore, UserDataStore{
	void addFriend(User user, User friend);
	void removeFriend(User user, User friend);
	List<User> getUserFriends(User user);
	List<Message> getUserAndFriendsMessages(User user);
}
