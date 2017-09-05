package com.cgm.miniTweeter2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.miniTweeter2.classes.DBManager;
import com.cgm.miniTweeter2.classes.User;

@Controller
public class UserController {
	@Autowired DBManager dbManager;
	
	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public ModelAndView userProfile(HttpServletRequest req, @PathVariable String username) {
		ModelAndView mav = new ModelAndView("user");
		User currentUser = (User) req.getSession().getAttribute("user");
		mav.addObject("userRelationship","<a href=\"/miniTweeter2/addFriend/" + username + "\">Add friend</a>");
		if(username.equals(currentUser.getUsername())) {
			mav.addObject("userRelationship", "This is your profile!");
		} else for(User iterator : currentUser.getFriends()) {
			if(username.equals(iterator.getUsername())) {
				mav.addObject("userRelationship", "Is already your friend.");
				break;
			}
		}
		User user = dbManager.getUser(username);
		mav.addObject(user);
		return mav;
	}
	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public ModelAndView friends(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("friends");
		User currentUser = (User) req.getSession().getAttribute("user");
		mav.addObject("friends", currentUser.getFriends());
		return mav;
	}
	@RequestMapping(value = "/addFriend/{username}", method = RequestMethod.GET)
	public ModelAndView addFriend(HttpServletRequest req, @PathVariable String username)
	{
		ModelAndView mav = new ModelAndView("redirect:/friends");
		User currentUser = (User) req.getSession().getAttribute("user");
		currentUser.addFriend(dbManager.getUser(username));
		return mav;
	}
}
