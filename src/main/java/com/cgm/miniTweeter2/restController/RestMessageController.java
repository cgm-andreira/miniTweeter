package com.cgm.miniTweeter2.restController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.miniTweeter2.logic.DBManager;
import com.cgm.miniTweeter2.logic.Message;
import com.cgm.miniTweeter2.logic.User;

@RestController
public class RestMessageController {
	@Autowired
	private DBManager dbManager;
	
	@RequestMapping(value = "/message", method = RequestMethod.PUT, consumes = "application/json")
	public String postMessage(String message, HttpServletRequest req) {
		String status = "failed";
		
		User currentUser = (User) req.getSession().getAttribute("user"); 
		if(currentUser != null) {
			currentUser.addMessage(message);
		}
		
		return status;
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Message> getAllMessages(HttpServletRequest req){
		List<Message> output = new ArrayList<Message>();
		
		User currentUser = (User) req.getSession().getAttribute("user"); 
		if(currentUser != null) {
			output.addAll(dbManager.getMessages(currentUser));
		}
		
		return output;
	}
	
	@RequestMapping(value = "/message/{username}", method = RequestMethod.GET, produces = "application/json")
	public List<Message> getMessages(@PathVariable String username, HttpServletRequest req){
		List<Message> output = new ArrayList<Message>();
		
		User currentUser = (User) req.getSession().getAttribute("user"); 
		if(currentUser != null) {
			output.addAll(dbManager.getUserMessages(username));
		}
		
		return output;
	}
}
