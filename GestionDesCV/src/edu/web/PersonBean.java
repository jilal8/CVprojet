package edu.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import edu.metier.Manager;
import edu.model.Activity;
import edu.model.Person;
import edu.model.RegisteredUser;

@ManagedBean(name = "personBean")
@SessionScoped
public class PersonBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Person thePerson = new Person();
	@EJB(name = "manager")
	private Manager manager;

	@PostConstruct
	public void init() {
		System.out.println("Create " + this);
		if (manager.findAllPersons().size() == 0) {
			Person p1 = new Person("TOURE", "Moustapha", new Date(2001, 1, 1), "website1",
					new RegisteredUser("email1@gmail.com", "password1"));
			Person p2 = new Person("DJILAL", "Hassan Mahdi", new Date(2002, 2, 2), "website2",
					new RegisteredUser("email1@gmail.com", "password2"));

			Activity acitivity1 = new Activity("nature1", "titre1", new Date(2010, 10, 10), "descriptid1", "website3");
			Activity acitivity2 = new Activity("nature2", "titre2", new Date(2011, 11, 11), "descriptid2", "website4");

			p1.addActivity(acitivity1);
			p1.addActivity(acitivity2);

			// Insert 2 Person objects with their activities
			manager.addPerson(p1);
			manager.addPerson(p2);
		}
	}

	public List<Person> getPersons() {
		return manager.findAllPersons();
	}

	public Person getThePerson() {
		return thePerson;
	}

	public String showPerson(Long id) {
		thePerson = manager.findPersonById(id);
		return "/prives/showCV?faces-redirect=true";
	}

	public String savePerson() {
		manager.addPerson(thePerson);
		return "/prives/showPerson?faces-redirect=true";
	}

	public String newPerson() {
		thePerson = new Person();
		return "editCourse?faces-redirect=true";
	}
	
	public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Edited", ((Person) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Person) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}

/*
 * private List<Person> persons;
 * 
 * private List<Person> filteredPersons;
 * 
 * public void setPersons(List<Person> persons) { this.persons = persons; }
 * 
 * public List<Person> getFilteredPersons() { return filteredPersons; }
 * 
 * public void setFilteredPersons(List<Person> filteredPersons) {
 * this.filteredPersons = filteredPersons; }
 */