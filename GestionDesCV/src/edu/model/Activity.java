package edu.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


@Entity
@Table(name = "ACTIVITY")
public class Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Basic(optional = false)
	@Column(length = 200, nullable = false)
	private String nature;

	@Basic(optional = false)
	@Column(length = 200, nullable = false)
	private String title;

	@Basic(optional = false)
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date year;

	@Column(length = 500)
	private String descriptif;

	@Column(length = 300)
	private String website;

	@Version
	private long version = 0;

	@ManyToOne(optional = true)
	@JoinColumn(name = "id_person")
	private Person person;

	public Activity() {
		super();
	}

	public Activity(String nature, String title, Date year, String descriptif, String website) {
		super();
		this.nature = nature;
		this.title = title;
		this.year = year;
		this.descriptif = descriptif;
		this.website = website;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
		person.getActivities().add(this);
	}
	
	@Override
	public String toString() {
		return "Activity[id=" + getId() + ", Nature=" + nature + ", Title=" + title + ", Year=" + year + 
				",Descriptif=" + descriptif + ",Website=" + website + ",Version=" + getVersion() + "]";
	}
}
