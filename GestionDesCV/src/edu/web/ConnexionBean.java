package edu.web;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.metier.User;

@ManagedBean
@SessionScoped
public class ConnexionBean {

	private String email;
	private String password;
	private Boolean connected = false;

	@EJB(name = "user")
	private User user;

	// validate login
	public String validateEmailAndPassword() {
		boolean valide = user.isRegistered(email, password);
		if (valide) {
			connected = true;
			return "/prives/showCV?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Passowrd", "Please enter correct username and Password"));
			return "connexion?faces-redirect=true";
		}
	}

	public void logout() {
		connected = false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getConnected() {
		return connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

}
