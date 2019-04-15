package edu.metier;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import edu.dao.DaoActivity;
import edu.dao.DaoPerson;
import edu.dao.DaoRegisteredUser;
import edu.model.Activity;
import edu.model.Person;
import edu.model.RegisteredUser;

@Stateless( name = "manager")
public class Manager {
	
	@EJB(name = "daoPerson")
	private DaoPerson daoPerson;
	@EJB( name = "daoActivity")
	private DaoActivity daoActivity;
	@EJB(name = "daoRegisteredUser")
	private DaoRegisteredUser daoRegisteredUser;
	
	public void init() {
        System.out.println("----> init " + this);
    }
	
	//Access fonctions for the persons
	public Person addPerson(Person p) {
		return daoPerson.addPerson(p);
	}

	public Person findPersonById(long id) {
		return daoPerson.findPersonById(id);
	}

	public Person updatePerson(Person p) {
		return daoPerson.updatePerson(p);
	}

	public void removePerson(Person p) {
		removePerson(p);
	} 
	
	public List<Person> findAllPersons() {
		return daoPerson.findAllPersons();
	}
	
	public List<Person> findPersonsByName(String pattern) {
	    return daoPerson.findPersonsByName(pattern);
	}
	
	public List<Person> findPersonsByFirstName(String pattern) {
		 return daoPerson.findPersonsByFirstName(pattern);
	}
	
	
	//Access fonctions for the activities
	public Activity addActivity(Activity a) {
		return daoActivity.addActivity(a);
	}

	public Activity findActivityById(long id) {
		return daoActivity.findActivityById(id);
	}

	public Activity updateActivity(Activity a) {
		return daoActivity.updateActivity(a);
	}

	public void removeActivity(Activity a) {
		daoActivity.removeActivity(a);
	} 
	
	public List<Activity> findAllActivities() {
	    return daoActivity.findAllActivities();
	}
	
	//Access fonctions for the registered users
	public RegisteredUser findRegisteredUserByEmailAndPassword(String email, String password) {
		return daoRegisteredUser.findRegisteredUserByEmailAndPassword(email, password);
	}
	
}
