package edu.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.model.Person;


@Stateless(name = "daoPerson")
public class DaoPerson extends DaoAbstract<Person>{

	/*@PersistenceContext(unitName = "myDatabaseUnit")
    private EntityManager em;

    @PostConstruct
    public void init() {
        System.out.println("----> init " + this + " with " + em);
    }
 
    //CRUD fonctions
    public Person addPerson(Person p) {
		em.persist(em.contains(p) ? p : em.merge(p));
		return p;
	}

	public Person findPersonById(long id) {
		return em.find(Person.class, id);
	}

	public Person updatePerson(Person p) {
		p = em.merge(p);
		return p;
	}

	public void removePerson(Person p) {
		em.remove(em.contains(p) ? p : em.merge(p));
	} 
	public List<Person> findAllPersons() {
	    TypedQuery<Person> q = em.createQuery("FROM Person", Person.class);
	    return q.getResultList();
	}
	*/
	
	@PostConstruct
    public void init() {
        System.out.println("----> init " + this + " with " + em);
    }
	
	public Person addPerson(Person p) {
		return add(p);
	}

	public Person findPersonById(long id) {
		return findById(Person.class, id);
	}

	public Person updatePerson(Person p) {
		return update(p);
	}

	public void removePerson(Person p) {
		remove(p);
	} 
	
	//Auther fonctions
	public List<Person> findAllPersons() {
		return this.findAll("FROM Person",Person.class);
	}
	
	public List<Person> findPersonsByName(String pattern) {
	    TypedQuery<Person> q = em.createQuery("FROM Person p where p.name like '%" + pattern + "%'",Person.class);
	    return q.getResultList();
	}
	
	public List<Person> findPersonsByFirstName(String pattern) {
	    TypedQuery<Person> q = em.createQuery("FROM Person p where p.firstName like '%" + pattern + "%'",Person.class);
	    return q.getResultList();
	}
	
}
