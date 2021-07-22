package com.gunjan768.restful.restful_demo.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// The HAL browser will then be available on http://localhost:8080/. JSON Hypertext Application Language, or HAL, is a simple format that gives a
// consistent and easy way to hyperlink between resources in our API. Including HAL within our REST API makes it much more explorable to users as
// well as being essentially self-documenting. It works by returning data in JSON format which outlines relevant information about the API. The HAL
// model revolves around two simple concepts. 1) Resources, which contain: Links to relevant URIs, Embedded Resources, State. 2) Links: A target
// URI, a relation or rel to the link, a few other optional properties to help with depreciation, content negotiation, etc

@ApiModel(description = "All details about the user")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 2, message = "Name should have at least 2 characters")
	@ApiModelProperty(notes = "Name should have at least 2 characters")
	private String name;

	@Past
	@ApiModelProperty(notes = "Birth date should be in the past")
	private Date birthDate;
	
	// mappedBy: The field that owns the relationship. Required unless the relationship is unidirectional. We don't want to create the relationship
	// in both user and post. We would actually create the relationship column of user in post. mappedBy="user" where "user" is the field name.
	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	protected User() {

	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}

}
