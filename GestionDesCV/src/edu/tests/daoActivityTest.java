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

import edu.dao.DaoActivity;
import edu.dao.DaoPerson;
import edu.model.Activity;
import edu.model.Person;
import edu.model.RegisteredUser;

public class daoActivityTest {

	static EJBContainer container;
	static DaoActivity daoActivity;
	static DaoPerson daoPerson;

	@BeforeClass
	public static void beforeAll() throws NamingException {
		container = EJBContainer.createEJBContainer();
		daoActivity = (DaoActivity) container.getContext().lookup("java:global/GestionDesCV/daoActivity");
		daoPerson = (DaoPerson) container.getContext().lookup("java:global/GestionDesCV/daoPerson");
	}

	@AfterClass
	public static void afterAll() {
		container.close();
	}

	// @Test
	public void testInjectDaoPerson() {
		Assert.assertNotNull(daoActivity);
	}

	//@Test
	public void testfindAllActivities() {

		System.err.println("\n----> Test : Insert Person With Actities");
		Person p1 = new Person("TOURE", "Moustapha", new Date(2001, 1, 1), "website1", null);
		Person p2 = new Person("DJILAL", "Hassan Mahdi", new Date(2002, 2, 2), "website2",
				new RegisteredUser("email2", "password2"));

		Activity acitivity1 = new Activity("nature1", "titre1", new Date(2010, 10, 10), "descriptid1", "website");
		Activity acitivity2 = new Activity("nature2", "titre2", new Date(2011, 11, 11), null, null);

		p1.addActivity(acitivity1);
		p1.addActivity(acitivity2);

		// Insert 2 Person objects with their activities
		daoPerson.addPerson(p1);
		daoPerson.addPerson(p2);

		// Read and display all activities
		System.err.println("\n----> Test : Read Actities");
		List<Activity> list = daoActivity.findAllActivities();
		for (Activity a : list) {
			System.out.println(a.toString());
		}

	}

}
