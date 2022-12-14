package com.wannacode.microservice.model;

import java.util.List;

public class UserTask {
	private User user;
	private List<Task> tasks;
	
	
	public UserTask(User user, List<Task> tasks) {
		super();
		this.user = user;
		this.tasks = tasks;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
}
