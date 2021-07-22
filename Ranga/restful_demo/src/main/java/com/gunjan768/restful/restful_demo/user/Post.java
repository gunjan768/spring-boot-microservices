package com.gunjan768.restful.restful_demo.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	
	// One of the important thing in JAP is that by default, this will directly try to fetch the user. User will try to fetch the post and post would
	// try to fetch the user. So making it lazy in which it will not fetch the user unless we call it to fetch the user. Whenever we will convert
	// User to JSON, the posts would come by default. In User, there is tag called Post (List<Posts>) where the posts of the user come by default.
	// When we have Post, we only want the details of the Post, we don't the details of the User. So when we try and get the details of the User, it
	// try to fetch the details of User and again User will try to fetch Post and hence recursive infinite loop would happen. So we want to ignore
	// fetching the details of the User when we try to fetch Post by using @@JsonIgnore (ignore fetching User whenever Post will get convert to JSON).
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return String.format("Post [id=%s, description=%s]", id, description);
	}
	
}
