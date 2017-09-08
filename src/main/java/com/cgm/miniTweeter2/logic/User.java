package com.cgm.miniTweeter2.logic;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private String name;
	private String about;

	@JsonIgnore
	private List<Message> messages;
	@JsonIgnore
	private List<User> friends;

	public User() {
		messages = new ArrayList<Message>();
		friends = new ArrayList<User>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return this.about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void addMessage(Message message) {
		messages.add(message);
	}

	public void addMessage(String message) {
		Message newMessage = new Message();
		newMessage.setMessage(message);
		newMessage.setUser(this);
		messages.add(newMessage);
	}

	public void addFriend(User user) {
		friends.add(user);
	}

	public void removeFriend(User user) {
		friends.remove(user);
	}

	public List<User> getFriends() {
		return friends;
	}

	public List<User> getFollowing() {
		return friends;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", about=" + about
				+ ", messages=" + messages + ", friends=" + friends + "]";
	}
}
