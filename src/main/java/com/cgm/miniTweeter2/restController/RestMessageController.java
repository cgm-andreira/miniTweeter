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

import com.cgm.miniTweeter2.DTO.MessageDTO;
import com.cgm.miniTweeter2.DTO.UserDTO;
import com.cgm.miniTweeter2.contract.CommonDataStore;

@RestController
public class RestMessageController {
	@Autowired
	private CommonDataStore dataStore;
	
	@RequestMapping(value = "/message", method = RequestMethod.PUT, consumes = "application/json")
	public String postMessage(String message, HttpServletRequest req) {
		String status = "failed";
		
		UserDTO currentUser = (UserDTO) req.getSession().getAttribute("user");
		
		if(currentUser != null) {
			MessageDTO newMessage = new MessageDTO();
			
			newMessage.setMessage(message);
			newMessage.setUser(currentUser);
			
			dataStore.addMessage(newMessage);
		}
		
		return status;
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MessageDTO> getAllMessages(HttpServletRequest req){
		List<MessageDTO> output = new ArrayList<MessageDTO>();
		
		UserDTO currentUser = (UserDTO) req.getSession().getAttribute("user"); 
		if(currentUser != null) {
			output.addAll(dataStore.getUserMessages(currentUser));
		}
		
		return output;
	}
	
	@RequestMapping(value = "/message/{username}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MessageDTO> getMessages(@PathVariable String username, HttpServletRequest req){
		List<MessageDTO> output = new ArrayList<MessageDTO>();
		
		UserDTO currentUser = dataStore.getUserByUsername((String)req.getSession().getAttribute("username"));
		if(currentUser != null) {
			output.addAll(dataStore.getUserMessages(currentUser));
		}
		
		return output;
	}
}
