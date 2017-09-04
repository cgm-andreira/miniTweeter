package com.cgm.miniTweeter2.classes;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private String name;
	private ArrayList<Message> messages;
	private ArrayList<User> friends;
	private ArrayList<User> following;
	
	public User() {
		messages = new ArrayList<Message>();
		friends = new ArrayList<User>();
		following = new ArrayList<User>();
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
	public ArrayList<Message> getMessages(){
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
	public void addFriends(User user) {
		friends.add(user);
	}
	public ArrayList<User> getFriends(){
		return friends;
	}
	public ArrayList<User> getFollowing(){
		return following;
	}
	public void addFollowing(User user) {
		following.add(user);
	}
	public void removeFollowing(User user) {
		following.remove(user);
	}
}
