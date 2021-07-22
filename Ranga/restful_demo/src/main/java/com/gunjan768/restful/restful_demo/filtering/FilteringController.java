package com.gunjan768.restful.restful_demo.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	// We want only field1 and field2 to be sent as a response and ignore field3 i.e. filtering. We have used a dynamic filtering where
	// we will always mentioned the filed(s) we want to include or exclude (ignore).
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");

		// Filter out all the properties except "field1" and "field2" where "field1" and "field2" are the name of the variables (properties)
		// (see SomeBean.java class). If the entered field name in filterOutAllExcept() doesn't match with any field in the class then
		// it will not throw an error rather that field will not be included (ass we are including the fields which we don't want to
		// filter out i.e. we want those fields to be sent as a response).
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

		// Creating a filter using an abstract class FilterProvider and SimpleFilterProvider is the implementation of it. Note the FilterProvider
		// is an abstract class and SimpleFilterProvider extends FilterProvider class. addFilter() method is a method in SimpleFilterProvider
		// class. Now to call addFilter() method we need an instance of SimpleFilterProvider class. Here "filters" can't able to call a method
		// of SimpleFilterProvider class as "filters" is a reference type variable of class FilterProvider class i.e. reference type variable of
		// parent class can only call methods of parent class only and overridden methods, but not able to call methods of child class.

		// addFilter("SomeBeanFilter", filter): "SomeBeanFilter" is the name of filter and bean of this filter must be created otherwise
		// error will be thrown saying "No filter configured with id 'SomeBeanFilter'". To configure the filter use @JsonFilter("SomeBeanFilter")
		// annotation on the class you want to filter (see SomeBean.java class)
		FilterProvider filters = (new SimpleFilterProvider()).addFilter("SomeBeanFilter", filter);

		// MappingJacksonValue class is used in filtering. It has a method setFilters() which accepts filter.
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);

		return mapping;
	}

	// Include : field2, field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBeans() {

		List<SomeBean> list = Arrays.asList(
				new SomeBean("value1", "value2", "value3"),
				new SomeBean("value12", "value22", "value32")
		);

		// Here field3 is included in the response but field "field3" is filtered out in the class by static filter technique using
		// @JsonIgnore annotation.
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);

		return mapping;
	}
}