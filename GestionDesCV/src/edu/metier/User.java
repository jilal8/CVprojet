package edu.metier;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import edu.model.RegisteredUser;

@Stateful( name = "user")
public class User {
	
	@EJB
	private Manager manager;

	public Boolean isRegistered(String email, String password) {
		RegisteredUser registeredUser = manager.findRegisteredUserByEmailAndPassword(email, password);
		return registeredUser == null ? false : true ;
    }

    /*@Remove
    public void logout() {
       
    }*/

}
