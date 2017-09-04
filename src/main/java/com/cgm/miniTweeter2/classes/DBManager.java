package com.cgm.miniTweeter2.classes;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class DBManager {
	HashMap<String, User> users;
	
	public DBManager() {
		users = new HashMap<String, User>();
		User user = new User();
		user.setName("Bob Marley");
		user.setUsername("spiderman");
		user.setPassword("best");
		user.addMessage("Hello world of miniTweeter");
		user.addMessage("This is my second miniTweet!");
		user.addMessage("Who has a joint?");
		
		users.put(user.getUsername(), user);
		
		user = new User();
		user.setName("Jeff Smartypants");
		user.setUsername("jeffy");
		user.setPassword("admin");
		user.addMessage("This is mah first tweet");
		user.addMessage("Does anyone know where I left my phone? - Message posted from AndroidOS");
		
		users.put(user.getUsername(), user);
		
	}
	public User getUser(String username) {
		return users.get(username);
	}
	public ArrayList<Message> getUserMessages(String username){
		return users.get(username).getMessages();
	}
	public ArrayList<Message> getMessages(User user){
		ArrayList<Message> output = new ArrayList<Message>();
		User currentUser = user;
		output.addAll(currentUser.getMessages());
		ArrayList<User> following = currentUser.getFollowing();
		for(User followedUser : following) {
			output.addAll(followedUser.getMessages());
		}
		return output;
	}
}
