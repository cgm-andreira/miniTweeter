package com.cgm.miniTweeter2.contract;

import java.util.List;

import com.cgm.miniTweeter2.dbObjects.Message;
import com.cgm.miniTweeter2.dbObjects.User;

public interface MessageDataStore {
//	Message getMessageById(int id);
	void addMessage(Message newMessage);
	void removeMessage(Message messageToRemove);
	List<Message> getUserMessage(User user);
}
