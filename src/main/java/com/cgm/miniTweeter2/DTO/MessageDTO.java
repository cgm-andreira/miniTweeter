package com.cgm.miniTweeter2.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MessageDTO {
	private long id;
	private String message;
	@JsonIgnore
	private UserDTO user;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
