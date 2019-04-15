package edu.tests;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.dao.DaoPerson;
import edu.model.Activity;
import edu.model.RegisteredUser;
import edu.model.Person;

public class DaoPersonTest {

	static EJBContainer container;
    static DaoPerson daoPerson;

    @BeforeClass
    public static void beforeAll() throws NamingException {
        container = EJBContainer.createEJBContainer();
        final String name = "java:global/GestionDesCV/daoPerson";
        daoPerson = (DaoPerson) container.getContext().lookup(name);
    }

    @AfterClass
    public static void afterAll() {
        container.close();
    }

    //@Test
    public void testInjectDaoPerson() {
        Assert.assertNotNull(daoPerson);
    }

    //@Test
    public void testInsertPerson() {
    	System.err.println("\n----> Test : Insert Person");
        Person p1 = new Person("TOURE", "Moustapha", new Date(2001,1,1),"website1", new RegisteredUser("email1","password1"));
        Person p2 = new Person("DJILAL", "Hassan Mahdi", new Date(20002,1,2),"website2", new RegisteredUser("email2","password2"));
    
        daoPerson.addPerson(p1);
        daoPerson.addPerson(p2);
    }
    
    //@Test
    public void testInsertAndReadPerson() {
    	System.err.println("\n----> Test : Insert Person");
        Person p1 = new Person("TOURE", "Moustapha", new Date(2001,1,1),"website1", new RegisteredUser("email1","password1"));
        Person p2 = new Person("DJILAL", "Hassan Mahdi", new Date(20002,1,2),"website2", new RegisteredUser("email2","password2"));
    
        //Insert 2 Person objects
        daoPerson.addPerson(p1);
        daoPerson.addPerson(p2);
        
      //Read and display all Person objects
        System.err.println("\n----> Test : Read Person");
        List<Person> list = daoPerson.findAllPersons();
		for (Person p : list) {
			System.out.println(p.toString());
		}
    }
   
    
    //@Test
    public void testInsertPersonWithActities() {
    	System.err.println("\n----> Test : Insert Person With Actities");
        Person p1 = new Person("TOURE", "Moustapha", new Date(2001,1,1),"website1", new RegisteredUser("email1@gmail.com","password1"));
        Person p2 = new Person("DJILAL", "Hassan Mahdi", new Date(2002,2,2),"website2", new RegisteredUser("email1@gmail.com","password2"));
        
        Activity acitivity1 = new Activity("nature1","titre1",new Date(2010,10,10),"descriptid1","website3");
        Activity acitivity2 = new Activity("nature2","titre2",new Date(2011,11,11),"descriptid2","website4");
        
        p1.addActivity(acitivity1);
        p1.addActivity(acitivity2);
        
      //Insert 2 Person objects with their activities
        daoPerson.addPerson(p1);
        daoPerson.addPerson(p2);
        
      //Read and display all Person objects with their activities
        System.err.println("\n----> Test : Read Person  With Actities");
        List<Person> list = daoPerson.findAllPersons();
		for (Person p : list) {
			System.out.println(p.toString());
			//Display email and password
			RegisteredUser registeredUser = p.getEmailAndPassword();
			if(registeredUser == null)
				System.out.println("Not email and password");
			else
				System.out.println(registeredUser.toString());
			//Display cars
			Set<Activity> activities = p.getActivities();
			if(activities.size() == 0)
				System.out.println("Not Activities");
			else
				for (Activity a : activities) {
					System.out.println(a.toString());
				}
			
		}
    }


}
