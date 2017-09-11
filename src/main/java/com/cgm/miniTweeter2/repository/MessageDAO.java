package com.cgm.miniTweeter2.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cgm.miniTweeter2.dbObjects.Message;

@Repository
public class MessageDAO extends AbstractDAO<Message> {

	public MessageDAO() {
		super(Message.class);
	}
	
	@Transactional
	public List<Message> getMessagesByUserId(int id){
		List<Message> output = new ArrayList<Message>();
		
		CriteriaBuilder cb = em().getCriteriaBuilder();
		CriteriaQuery<Message> cq = cb.createQuery(Message.class);
		
		Root<Message> root = cq.from(Message.class);
		cq.select(root);
		cq.where(cb.equal(root.get("user_id"), id));
		TypedQuery<Message> q = em().createQuery(cq);
		output = q.getResultList();
		
		return output;
	}
	
}
