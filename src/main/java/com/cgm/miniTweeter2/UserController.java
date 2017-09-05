package com.cgm.miniTweeter2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.miniTweeter2.classes.DBManager;

@Controller
public class UserController {
	@Autowired DBManager dbManager;
	
	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public ModelAndView userProfile(@PathVariable String username) {
		ModelAndView mav = new ModelAndView("user");
		
		return mav;
	}
}
