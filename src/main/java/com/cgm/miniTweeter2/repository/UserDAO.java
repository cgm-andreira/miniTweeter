package com.cgm.miniTweeter2.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

//import com.cgm.miniTweeter2.dbObjects.Message;
import com.cgm.miniTweeter2.dbObjects.User;

@Repository
public class UserDAO extends AbstractDAO<User>{

	public UserDAO() {
		super(User.class);
	}
	
	public User getUserByUsername(String username) {
		User user;
		
		CriteriaBuilder cb = em().getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		
		Root<User> root = cq.from(User.class);
		cq.select(root);
		cq.where(cb.equal(root.get("username"), username));
		TypedQuery<User> q = em().createQuery(cq);
		user = q.getSingleResult();
		
		return user;
	}
	
	public User getUserById(int id) {
		User user;
		
		CriteriaBuilder cb = em().getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		
		Root<User> root = cq.from(User.class);
		cq.select(root);
		cq.where(cb.equal(root.get("id"), id));
		TypedQuery<User> q = em().createQuery(cq);
		user = q.getSingleResult();
		
		return user;
	}
	
	public List<User> findUserByName(String keyword){
		List<User> result = new ArrayList<User>();
		
		CriteriaBuilder cb = em().getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		
		Root<User> root = cq.from(User.class);
		cq.select(root);
		cq.where(cb.like(root.get("name"), keyword));
		TypedQuery<User> q = em().createQuery(cq);
		result = q.getResultList();
		
		return result;
	}
}
