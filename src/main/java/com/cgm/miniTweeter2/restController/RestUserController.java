package com.cgm.miniTweeter2.restController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.miniTweeter2.logic.DBManager;
import com.cgm.miniTweeter2.logic.User;

@RestController
@RequestMapping("/user")
public class RestUserController {
	@Autowired
	private DBManager dbManager;
	
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public String addFriend(@RequestBody String username, HttpServletRequest req) {
		String status = "failed";
		User currentUser = (User) req.getSession().getAttribute("user"); 
		if(currentUser != null) {
			currentUser.removeFriend(dbManager.getUser(username));
			status = "ok";
		}
		return status;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	public String removeFriend(@RequestBody String username, HttpServletRequest req) {
		String status = "failed";
		User currentUser = (User) req.getSession().getAttribute("user"); 
		if(currentUser != null) {
			currentUser.removeFriend(dbManager.getUser(username));
			status = "ok";
		}
		return status;
	}
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public List<User> findUser(@PathVariable String username, HttpServletRequest req) {
		return dbManager.getUsers(username);
	}
}
