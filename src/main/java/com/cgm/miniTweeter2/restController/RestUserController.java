package com.cgm.miniTweeter2.restController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.miniTweeter2.DTO.UserDTO;
import com.cgm.miniTweeter2.contract.CommonDataStore;

@RestController
@RequestMapping(value = "/restUser")
public class RestUserController {
	@Autowired
	private CommonDataStore dataStore;
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public String addFriend(@RequestBody String username, HttpServletRequest req) {
		String status = "failed";
		UserDTO currentUser = dataStore.getUserByUsername((String)req.getSession().getAttribute("username"));
		if(currentUser != null) {
			UserDTO friend = dataStore.getUserByUsername(username);
			
			dataStore.addFriend(currentUser, friend);
			
			status = "ok";
		}
		return status;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public String removeFriend(@RequestBody String username, HttpServletRequest req) {
		String status = "failed";
		UserDTO currentUser = dataStore.getUserByUsername((String)req.getSession().getAttribute("username")); 
		if(currentUser != null) {
			UserDTO friend = dataStore.getUserByUsername(username);
			
			dataStore.removeFriend(currentUser, friend);
			
			status = "ok";
		}
		return status;
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public @ResponseBody UserDTO userDetails(@PathVariable String username, HttpServletRequest req) {
		return dataStore.getUserByUsername(username);
	}
	
	@RequestMapping(value = "find/{username}", method = RequestMethod.GET, produces = "application/json")
	public List<UserDTO> findUser(@PathVariable String username, HttpServletRequest req) {
		Set<UserDTO> foundUsers = new HashSet<UserDTO>();
		List<UserDTO> userList = new ArrayList<UserDTO>();
		
		userList = dataStore.findUsersByName(username);
		if(userList != null) {
			foundUsers.addAll(userList);
		}
		
		userList = dataStore.findUsersByUsername(username);
		if(userList != null) {
			foundUsers.addAll(userList);
		}
		
		return null;
	}
}
