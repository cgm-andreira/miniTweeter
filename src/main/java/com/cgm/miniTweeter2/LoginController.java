package com.cgm.miniTweeter2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.miniTweeter2.classes.DBManager;
import com.cgm.miniTweeter2.classes.Login;
import com.cgm.miniTweeter2.classes.LoginValidator;
import com.cgm.miniTweeter2.classes.User;

@Controller
public class LoginController {
	@Autowired HttpSession httpSession;
	@Autowired DBManager dbManager;
	@Autowired LoginValidator loginValidator;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	@RequestMapping(value = "/processLogin", method = RequestMethod.POST)
	public ModelAndView processLogin(HttpServletRequest req, @ModelAttribute("login") Login login) {
		ModelAndView mav = null;
		User user = loginValidator.validateUser(login);
		if(user != null) {
			mav = new ModelAndView("home");
			
			req.getSession().setAttribute("userName", user.getName());
			req.getSession().setAttribute("user", user);
			mav.addObject("userName", user.getName());
			mav.addObject("messages", dbManager.getMessages(user));
			
		} else {
			String message = "Username or password error, please try again!";
			mav = new ModelAndView("login");
			mav.addObject("message", message);
		}
		return mav;
	}
}
