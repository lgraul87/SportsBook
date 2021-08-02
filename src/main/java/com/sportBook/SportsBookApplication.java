package com.sportBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan("com.sportBook")
public class SportsBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsBookApplication.class, args);
	}
}
/*
 * This Back-End application provides a solution to manage sports events scores
 * 
 * Points can be added to a team's score, but never subtracted.
 * 
 * The range of valid points is between 0-9
 * 
 * When trying to add negative points, the application restricts the action and
 * will not update the score. For example:
 * 
 * ####### Team A vs Team B ####### current score : 0-1
 * 
 * Update new score = 0-0 ---- the score will not be updated, since it is not
 * possible to subtract points
 * 
 * Update new score = 1-1 ---- the score will be updated
 * 
 * The score 9-9 is the maximum allowed range
 * 
 */