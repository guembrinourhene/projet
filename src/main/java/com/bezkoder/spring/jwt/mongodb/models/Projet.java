package com.bezkoder.spring.jwt.mongodb.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "projets")
public class Projet {

	@Id
	private String id;
	private String title;
	private String description;

	@DBRef
	private List<User> users = new ArrayList();

	public Projet() {

	}

	public Projet(String title, String description) {
		this.title = title;
		this.description = description;

	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Projet(String id, String title, String description, List<User> user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.users = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void addUser(User user) {
		this.users.add(user);
	}
	
	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + "]";
	}
}
