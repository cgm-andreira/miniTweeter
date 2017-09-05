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
		user.setAbout("I am Robert Nesta Marley. OM was a Jamaican singer-songwriter, musician and guitarist who achieved international fame and acclaim, blending mostly reggae, ska and rocksteady in his compositions.");
		user.addMessage("Hello world of miniTweeter");
		user.addMessage("This is my second miniTweet!");
		user.addMessage("Who has a joint?");
		User user1 = user;
		
		System.out.println(user.getMessages());
		
		users.put(user.getUsername(), user);
		
		user = new User();
		user.setName("Jeff Smartypants");
		user.setUsername("jeffy");
		user.setPassword("admin");
		user.setAbout("Hi, I am new to the internets. I run around the nets looking at photos of cats.");
		user.addMessage("This is mah first tweet");
		user.addMessage("Does anyone know where I left my phone? - Message posted from AndroidOS");
		
		user.addFriend(user1);
		user1.addFriend(user);
		
		users.put(user.getUsername(), user);
		
		user = new User();
		user.setName("Johnny B");
		user.setUsername("admin");
		user.setPassword("admin");
		user.setAbout("I am the website admin.");
		user.addMessage("I am the ADMIN!");
		user.addMessage("Ini mini miny mo..");
		
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
		
		System.out.println(user.getMessages());
		
		for(User followedUser : following) {
			output.addAll(followedUser.getMessages());
		}
		return output;
	}
}
