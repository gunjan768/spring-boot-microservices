package com.gunjan768.restful.restful_demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

// URL: http://localhost:8080/v2/api-docs, http://localhost:8080/swagger-ui/#/
// Swagger is used to make a documentation of the resources used in the map. You can use url: "localhost:port/swagger-ui.html" to see the
// documentation of your app in a visual representation (beautifully decorated UI).
@Configuration
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact(
			"Gunjan Kumar", "http://www.gunjan768.com", "emilia@gmail.com"
	);
	
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
			"Awesome API Title",
			"Awesome API Description",
			"1.0",
			"urn:tos",
			DEFAULT_CONTACT,
			"Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0",
			Collections.emptyList()
	);

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES);
	}
}