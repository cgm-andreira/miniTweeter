package com.cgm.miniTweeter2;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.miniTweeter2.DTO.MessageDTO;
import com.cgm.miniTweeter2.DTO.UserDTO;
import com.cgm.miniTweeter2.contract.CommonDataStore;
//import com.cgm.miniTweeter2.dbObjects.Message;
//import com.cgm.miniTweeter2.logic.DBManager;
//import com.cgm.miniTweeter2.dbObjects.User;

@Controller
public class HomeController {
	@Autowired CommonDataStore dbManager;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest req, Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("home");
		UserDTO currentUser = (UserDTO) req.getSession().getAttribute("user");
		if(currentUser != null) {
			mav.addObject("userName", currentUser.getName());
			mav.addObject("messages", dbManager.getUserAndFriendsMessages(currentUser));
		}
		return mav;
	}
	
	@RequestMapping(value = "/postMessage", method = RequestMethod.POST)
	public ModelAndView postMessage(HttpServletRequest req, @ModelAttribute("message") String message) {
		ModelAndView mav;
		UserDTO currentUser = (UserDTO) req.getSession().getAttribute("user");
		
		if (currentUser == null) {
			mav = new ModelAndView("redirect:/");
			return mav;
		}
		if(!message.equals("")) {
			mav = new ModelAndView("home");
			MessageDTO newMessage = new MessageDTO();
			newMessage.setMessage(message);
			newMessage.setUser(currentUser);
			dbManager.addMessage(newMessage);
		} else {
			mav = new ModelAndView("redirect:/");
		}
		
		mav.getModel().put("messages", dbManager.getUserAndFriendsMessages(currentUser));
		//System.out.println(dbManager.getMessages(currentUser));
		return mav;
	}
}
