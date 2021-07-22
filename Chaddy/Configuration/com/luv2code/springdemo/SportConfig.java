package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

// @ComponentScan("package_name"): provide the actual package name to have Spring to start scanning. This works exactly like component
// scanning. It will scan this package, find all the classes that have the @Component annotation and register them in Spring Container.

// @PropertySource("classpath:sport.properties"): will used to inject the properties from the file (here from sport.properties).

// Note: Here we have commented the @ComponentScan line, so now Spring will not scan the beans (classes annotated with @Component) 
// automatically. Hence, we have to manually look for the beans and thats what we are doing here using @Bean annotation. Also see the 
// SwimCoach.java class, there we have not annotated with @Component for the same reason discussed above.

@Configuration
// @ComponentScan("com.luv2code.springdemo")
@PropertySource("classpath:sport.properties")
public class SportConfig {
	
	// add support to resolve ${...} properties
	//
	// NOTE: THIS IS ONLY REQUIRED FOR OLD VERSIONS OF SPRING: v4.2 and lower
	//
	// This is NOT required if using Spring v4.3+
	@Bean
	public static PropertySourcesPlaceholderConfigurerpropertySourcesPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	// @Bean: defines a bean i.e. we create a new instance of this bean. The method name will be the actual bean ID registerd in the
	// Spring Container like here bean ID = 'sadFortuneService'

	// Define bean for our sad fortune service
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
	
	// Define bean for our swim coach and inject dependency swim coach. We are manually generating for the bean using @Bean annotation.
	@Bean
	public Coach swimCoach() {
		SwimCoach mySwimCoach = new SwimCoach(sadFortuneService());
		
		return mySwimCoach;
	}
	
}