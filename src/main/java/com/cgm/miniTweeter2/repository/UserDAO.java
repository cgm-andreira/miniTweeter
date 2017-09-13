package com.cgm.miniTweeter2.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

//import com.cgm.miniTweeter2.dbObjects.Message;
import com.cgm.miniTweeter2.dbObjects.User;

@Repository
public class UserDAO extends AbstractDAO<User> {

	public UserDAO() {
		super(User.class);
	}

	@Transactional
	public User getUserByUsername(String username) {
		User user = null;
		try {

			CriteriaBuilder cb = em().getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);

			Root<User> root = cq.from(User.class);
			cq.select(root);
			cq.where(cb.equal(root.get("username"), username));
			TypedQuery<User> q = em().createQuery(cq);
			user = q.getSingleResult();

		} catch (NoResultException e) {
		}

		return user;
	}

	@Transactional
	public User getUserById(int id) {
		User user = null;
		try {
			CriteriaBuilder cb = em().getCriteriaBuilder();
			CriteriaQuery<User> cq = cb.createQuery(User.class);

			Root<User> root = cq.from(User.class);
			cq.select(root);
			cq.where(cb.equal(root.get("id"), id));
			TypedQuery<User> q = em().createQuery(cq);
			user = q.getSingleResult();
		} catch (NoResultException e) {
		}
		return user;
	}

	@Transactional
	public List<User> findUserByName(String keyword) {
		List<User> result = new ArrayList<User>();

		CriteriaBuilder cb = em().getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);

		Root<User> root = cq.from(User.class);
		cq.select(root);
		cq.where(cb.like(root.<String>get("name"), keyword));
		TypedQuery<User> q = em().createQuery(cq);
		result = q.getResultList();

		return result;
	}

	@Transactional
	public List<User> findUserByUsername(String username) {
		List<User> result = new ArrayList<User>();

		CriteriaBuilder cb = em().getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);

		Root<User> root = cq.from(User.class);
		cq.select(root);
		cq.where(cb.like(root.<String>get("username"), username));
		TypedQuery<User> q = em().createQuery(cq);
		result = q.getResultList();

		return result;
	}

	@Transactional
	public List<User> findUserByKeyword(String keyword) {
		List<User> result = new ArrayList<User>();

		CriteriaBuilder cb = em().getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);

		Root<User> root = cq.from(User.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.or(cb.like(root.<String>get("name"), keyword)));
		predicates.add(cb.or(cb.like(root.<String>get("username"), keyword)));
		predicates.add(cb.or(cb.like(root.<String>get("about"), keyword)));

		cq.select(root).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<User> q = em().createQuery(cq);
		result = q.getResultList();

		return result;
	}
}
