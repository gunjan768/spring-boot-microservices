package com.gunjan768.restful.restful_demo.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Both @JsonIgnoreProperties (class level annotation) and @JsonIgnore (property level annotation) are used for static filtering. To
// have a dynamic filtering (for different scenario we want different properties to be ignored) use @JsonFilter. Static filtering is
// useful when we are sure that this particular property (field) is always ignored.
//@JsonIgnoreProperties(
//
// 	// Write the properties you want to ignore or not include in the response
// 	value = {
// 		"field1",
// 		"field2"
// 	}
//)
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	
	private String field1;
	
	private String field2;
	
	// @JsonIgnore annotation will not include this field as a response.
	@JsonIgnore
	private String field3;

	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
}
