package com.cgm.miniTweeter2.DTO;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {
	private String username;
	private String name;
	private String about;
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	private List<MessageDTO> messages;
	@JsonIgnore
	private List<UserDTO> friends;
	
	public UserDTO() {
		messages = new ArrayList<MessageDTO>();
		friends = new ArrayList<UserDTO>();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MessageDTO> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}
	public List<UserDTO> getFriends() {
		return friends;
	}
	public void setFriends(List<UserDTO> friends) {
		this.friends = friends;
	}
}
