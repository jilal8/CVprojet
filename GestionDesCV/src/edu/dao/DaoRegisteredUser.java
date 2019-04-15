package edu.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import edu.model.RegisteredUser;

@Stateless(name = "daoRegisteredUser")
public class DaoRegisteredUser extends DaoAbstract<RegisteredUser> {

	@PostConstruct
	public void init() {
		System.out.println("----> init " + this + " with " + em);
	}

	public RegisteredUser findRegisteredUserByEmailAndPassword(String email, String password) {
		TypedQuery<RegisteredUser> q = em.createQuery(
				"FROM RegisteredUser ru where ru.email = '" + email + "' and ru.password = '" + password + "'",
				RegisteredUser.class);
		return q.getSingleResult();
	}

}
