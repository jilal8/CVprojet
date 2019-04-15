package edu.tests;

import java.util.Date;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.dao.DaoPerson;
import edu.dao.DaoRegisteredUser;
import edu.model.Person;
import edu.model.RegisteredUser;

public class DaoRegisteredUserTest {

	static EJBContainer container;
	static DaoRegisteredUser daoRegisteredUser;
	static DaoPerson daoPerson;

	@BeforeClass
	public static void beforeAll() throws NamingException {
		container = EJBContainer.createEJBContainer();
		daoRegisteredUser = (DaoRegisteredUser) container.getContext().lookup("java:global/GestionDesCV/daoRegisteredUser");
		daoPerson = (DaoPerson) container.getContext().lookup("java:global/GestionDesCV/daoPerson");
	}

	@AfterClass
	public static void afterAll() {
		container.close();
	}

	//@Test
	public void testInjectDaoPerson() {
		Assert.assertNotNull(daoRegisteredUser);
	}

	//@Test
	public void testfindRegisteredUserByEmailAndPassword() {
		System.err.println("\n----> Test : Insert Person with their email and password");
		Person p1 = new Person("TOURE", "Moustapha", new Date(2001, 1, 1), "website1",
				new RegisteredUser("email1", "password1"));
		Person p2 = new Person("DJILAL", "Hassan Mahdi", new Date(20002, 1, 2), "website2",
				new RegisteredUser("email2", "password2"));

		// Insert 2 Person objects
		daoPerson.addPerson(p1);
		daoPerson.addPerson(p2);

		System.err.println("\n----> Test : Read RegisteredUser");
		RegisteredUser ru = daoRegisteredUser.findRegisteredUserByEmailAndPassword("email2", "password2");
		if( ru != null )
			System.out.println(ru.toString());
		else
			System.out.println("Cet utilistaeur ne s'est pas inscrit");

	}
}
