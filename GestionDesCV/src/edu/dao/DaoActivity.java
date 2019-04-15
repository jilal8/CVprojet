package edu.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import edu.model.Activity;

@Stateless(name = "daoActivity")
public class DaoActivity extends DaoAbstract<Activity> {

	@PostConstruct
    public void init() {
        System.out.println("----> init " + this + " with " + em);
    }
	
	public Activity addActivity(Activity a) {
		return add(a);
	}

	public Activity findActivityById(long id) {
		return findById(Activity.class, id);
	}

	public Activity updateActivity(Activity a) {
		return update(a);
	}

	public void removeActivity(Activity a) {
		remove(a);
	} 
	
	public List<Activity> findAllActivities() {
	    return findAll("From Activity", Activity.class);
	}
}
