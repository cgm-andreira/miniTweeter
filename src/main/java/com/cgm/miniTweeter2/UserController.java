package com.cgm.miniTweeter2;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.miniTweeter2.DTO.UserDTO;
import com.cgm.miniTweeter2.contract.CommonDataStore;

//import com.cgm.miniTweeter2.logic.DBManager;
//import com.cgm.miniTweeter2.logic.User;

@Controller
public class UserController {
	@Autowired
	CommonDataStore dataStore;

	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
	public ModelAndView userProfile(HttpServletRequest req, @PathVariable String username) {
		ModelAndView mav = new ModelAndView("user");
		UserDTO currentUser = dataStore.getUserByUsername((String) req.getSession().getAttribute("username"));
		UserDTO user = dataStore.getUserByUsername(username);

		String action = "addFriend";

		mav.addObject("userRelationship", "<a href=\"/miniTweeter2/" + action + "/" + username + "\">Add friend</a>");
		if (username.equals(currentUser.getUsername())) {
			mav.addObject("userRelationship", "This is your profile!");
		} else
			for (UserDTO iterator : currentUser.getFriends()) {
				if (username.equals(iterator.getUsername())) {
					// mav.addObject("userRelationship", "Is already your friend.");
					action = "removeFriend";
					mav.addObject("userRelationship",
							"<a href=\"/miniTweeter2/" + action + "/" + username + "\">Remove friend</a>");
					break;
				}
			}
		if (user == null) {
			mav = new ModelAndView("noUser");
		}
		mav.addObject(user);
		return mav;
	}

	@RequestMapping(value = "/friends", method = RequestMethod.GET)
	public ModelAndView friends(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("friends");
		UserDTO currentUser = dataStore.getUserByUsername((String) req.getSession().getAttribute("username"));

		if (currentUser != null) {
			mav.addObject("friends", currentUser.getFriends());
		}
		return mav;
	}

	@RequestMapping(value = "/findUser/{keyword}", method = RequestMethod.GET)
	public ModelAndView findUsers(HttpServletRequest req, @PathVariable String keyword) {
		ModelAndView mav = new ModelAndView("userList");
//		List<UserDTO> foundUsers = new ArrayList<UserDTO>();
		
//		List<UserDTO> userList = dataStore.findUsersByName(keyword);
//
//		if (userList != null) {
//			foundUsers.addAll(userList);
//		}
//
//		userList = dataStore.findUsersByUsername(keyword);
//
//		if (userList != null) {
//			foundUsers.addAll(userList);
//		}
//		System.out.println(foundUsers);
		
		List<UserDTO> foundUsers = dataStore.findUsersByKeyword(keyword);
		
		mav.addObject("searchResult", foundUsers);
		return mav;
	}

	@RequestMapping(value = "/addFriend/{username}", method = RequestMethod.GET)
	public ModelAndView addFriend(HttpServletRequest req, @PathVariable String username) {
		ModelAndView mav = new ModelAndView("redirect:/friends");
		UserDTO currentUser = dataStore.getUserByUsername((String) req.getSession().getAttribute("username"));

		dataStore.addFriend(currentUser, dataStore.getUserByUsername(username));

		return mav;
	}

	@RequestMapping(value = "/removeFriend/{username}", method = RequestMethod.GET)
	public ModelAndView removeFriend(HttpServletRequest req, @PathVariable String username) {
		ModelAndView mav = new ModelAndView("redirect:/friends");
		UserDTO currentUser = dataStore.getUserByUsername((String) req.getSession().getAttribute("username"));
		dataStore.removeFriend(currentUser, dataStore.getUserByUsername(username));
		return mav;
	}
}
