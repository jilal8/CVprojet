package edu.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REGISTEREDUSER")
public class RegisteredUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long id;
	
	@Basic(optional = false)
	@Column(length = 100, nullable = false)
	private String email;
	
	@Basic(optional = false)
	@Column(length = 50, nullable = false)
	private String password;
	
	@OneToOne(mappedBy="registeredUser")
	private Person person;
	
	public RegisteredUser() {
		super();
	}

	public RegisteredUser(String email, String passeword) {
		super();
		this.email = email;
		this.password = passeword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasseword() {
		return password;
	}

	public void setPasseword(String password) {
		this.password = password;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	   public String toString() {
	      return "User[email = " + email + ", Passeword = " + password + "]";
	   }
}
