package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Value;

// No @Component annotation above the class name as we are manually looking (scanning) for the bean as there we commented out the @ComponentScan
// line (so will not scan for @Component (beans) automatically) (see SportConfig.java class)
public class SwimCoach implements Coach {

	private FortuneService fortuneService;

	@Value("${foo.email}")
	private String email;
	
	@Value("${foo.team}")
	private String team;
	
	// No @Autowired annotation for the same reason we discused above. We are using @Bean for manually creating bean and injecting (DI) the
	// FortuneService instance (see SportConfig.java class)
	public SwimCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Swim 1000 meters as a warm up.";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	public String getEmail() {
		return email;
	}

	public String getTeam() {
		return team;
	}	

}




