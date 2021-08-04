package com.sportBook.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportBook.model.Score;
import com.sportBook.model.SportEvent;
import com.sportBook.service.SportEventService;

@RestController
public class SportEventController {
	private SportEventService sportEventService;

	public SportEventController(SportEventService sportEventService) {
		this.sportEventService = sportEventService;
	}

	@GetMapping("/SportsEvents")
	public List<SportEvent> getSportsEvents() {
		return sportEventService.getSportsEvents();
	}

	@GetMapping("/SportsEvents/{id}")
	public SportEvent getSportEventById(@PathVariable("id") Integer id) {
		return sportEventService.getSportEventById(id);
	}

	// task: create an end point that returns the score.
	// This end point returns a sport event's score when you write this url in the
	// browser for this GetMapping. The object Score is built with the sport
	// event's id and a string with the score    
	@GetMapping("/SportsEvents/{id}/Score")
	public Score getScoreOfSportEvent(@PathVariable("id") Integer id) {
		return new Score(this.sportEventService.getSportEventById(id).getId(),
				this.sportEventService.getSportEventById(id).getScore());
	}

	@PostMapping("/SportsEvents")
	public void addSportEvent(@RequestBody SportEvent sportEvent) {
		sportEventService.addSportEvent(sportEvent);
	}

	// This end point can use a standard jpaRepository service or a custom service
	// when it is intended to send a score and an id to update a sports event.
	@PutMapping("/SportsEvents/{id}")
	public void updateSportEvent(@PathVariable("id") Integer id, @RequestBody SportEvent sportEvent) {

		try {
			this.sportEventService.updateSportEvent(sportEvent, id);
		} catch (Exception e) {
			this.sportEventService.updateScoreSportEvent(sportEvent, id);
		}
	}

	@DeleteMapping("/SportsEvents/{id}")
	public void deleteSportEvent(@PathVariable("id") Integer id) {
		sportEventService.deleteSportEvent(id);
	}
}
