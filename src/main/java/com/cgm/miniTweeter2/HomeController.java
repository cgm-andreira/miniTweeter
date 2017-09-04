package com.cgm.miniTweeter2;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.miniTweeter2.classes.DBManager;
import com.cgm.miniTweeter2.classes.User;

@Controller
public class HomeController {
	@Autowired HttpSession httpSession;
	@Autowired DBManager dbManager;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		ModelAndView mav = new ModelAndView("home");
		User currentUser = (User) httpSession.getAttribute("user");
		if(currentUser != null) {
			mav.addObject("userName", currentUser.getName());
			mav.addObject("messages", dbManager.getMessages(currentUser));
		}
		return mav;
	}
	
}