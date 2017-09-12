package com.cgm.miniTweeter2.contract;

import java.util.List;

import com.cgm.miniTweeter2.DTO.MessageDTO;
import com.cgm.miniTweeter2.DTO.UserDTO;
//import com.cgm.miniTweeter2.dbObjects.Message;
//import com.cgm.miniTweeter2.dbObjects.User;

public interface MessageDataStore {
//	Message getMessageById(int id);
	void addMessage(MessageDTO newMessage);
	void removeMessage(MessageDTO messageToRemove);
	List<MessageDTO> getUserMessages(UserDTO user);
}
