package com.cgm.miniTweeter2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.miniTweeter2.DTO.UserDTO;
import com.cgm.miniTweeter2.contract.CommonDataStore;
import com.cgm.miniTweeter2.contract.LoginValidatorInterface;
//import com.cgm.miniTweeter2.contract.MessageDataStore;
//import com.cgm.miniTweeter2.contract.UserDataStore;
import com.cgm.miniTweeter2.logic.Login;
//import com.cgm.miniTweeter2.logic.LoginValidator;
//import com.cgm.miniTweeter2.dbObjects.User;

@Controller
public class LoginController {
	@Autowired HttpSession httpSession;
	@Autowired LoginValidatorInterface loginValidator;
	
	@Autowired 
	CommonDataStore dataStore;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	@RequestMapping(value = "/processLogin", method = RequestMethod.POST)
	public ModelAndView processLogin(HttpServletRequest req, @ModelAttribute("login") Login login) {
		ModelAndView mav = null;
		UserDTO user = loginValidator.validateLogin(login);
		if(user != null) {
			
			req.getSession().setAttribute("name", user.getName());
			req.getSession().setAttribute("username", user.getUsername());
			req.getSession().setAttribute("userLinkAddress", user.getUsername());
			
			mav = new ModelAndView("redirect:/");
			
			mav.addObject("userName", user.getName());
			mav.addObject("messages", dataStore.getUserAndFriendsMessages(user));
		} else {
			String message = "Username or password error, please try again!";
			mav = new ModelAndView("login");
			mav.addObject("message", message);
			System.out.println("10");
		}
		return mav;
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		req.getSession().setAttribute("name", null);
		req.getSession().setAttribute("username", null);
		req.getSession().setAttribute("userLinkAddress", null);
		req.getSession().invalidate();
		return "redirect:/";
	}
}
