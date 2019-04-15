package edu.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long id;
	
	@Basic(optional = false)
	@Column(length = 100, nullable = false)
	private String name;
	
	@Basic(optional = false)
	@Column(length = 100, nullable = false)
	private String firstname;
	
	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date birthday;
	
	@Column(length = 300)
	private String website;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="registeredUser_id")
	private RegisteredUser registeredUser;
	
	@Version
	private long version = 0;
	
	@OneToMany(cascade = { CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "person")
	private Set<Activity> activities;

	public Person() {
		super();
	}

	public Person(String name, String firstname, Date birthday, String website, RegisteredUser registeredUser) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.birthday = birthday;
		this.website = website;
		this.registeredUser = registeredUser;
		activities = new HashSet<Activity>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String prenom) {
		this.firstname = prenom;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
		for (Activity activity : activities) {
			activity.setPerson(this);
		}
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
		activity.setPerson(this);
	}

	public void removeActivity(Activity activity) {
		activities.remove(activity);
		activity.setPerson(null);
	}
	
	@Override
	public String toString() {
		return "Person[id=" + getId() + ", Name=" + name + ", FirstName=" + firstname + ", BirthDay=" + birthday + 
				",Website=" + website + ",Version=" + getVersion() + "]";
	}
	
	@PrePersist
	public void beforeUpdate() {
		System.out.println("PrePersist of " + this);
	}

}
