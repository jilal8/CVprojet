package edu.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class DaoAbstract<T> {
	
	@PersistenceContext(unitName = "myDatabaseUnit")
    protected EntityManager em;

	protected  T add(T entity) {
		em.persist(em.contains(entity) ? entity : em.merge(entity));
	    return entity;
	}
	
	protected  T findById(Class<T> clazz, Object id) {
	    return em.find(clazz, id);
	}

	protected T update(T entity) {
	    entity = em.merge(entity);
	    return entity;
	}

	protected void remove(T entity) {
	    em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	protected List<T> findAll(String query, Class<T> clazz) {
	    TypedQuery<T> q = em.createQuery(query, clazz);
	    return q.getResultList();
	}
}
