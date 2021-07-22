package com.gunjan768.restful.restful_demo.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource; 

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(
			path = "/hello-world-bean",
			consumes = MediaType.ALL_VALUE,
			produces = {"application/json"}
	)
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	// "/hello-world/path-variable/in28minutes"
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}

	// Internalization is done using information (Locale) that comes through header "Accept-Language" where value of this header is the Locale
	// user is in like for example for USA: "us".
	// @GetMapping(path = "/hello-world-internationalized")
	// public String helloWorldInternationalized(
	// 	@RequestHeader(name = "Accept-Language", required = false) Locale locale
	// ) {
	// 	return messageSource.getMessage("good.morning.message", null, locale);
	// }

	// Accept-Language for France is fr
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {

		// Here we have used "LocaleContextHolder.getLocale()" to get the locale and avoided using request header.
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}