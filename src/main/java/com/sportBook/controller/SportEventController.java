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

	// Task: create an end point that returns the score.
	// This end point returns a sport event's score when you write this url in the
	// browser for this GetMapping. 
	@GetMapping("/SportsEvents/{id}/score")
	public Score getScoreOfSportEvent(@PathVariable("id") Integer id) {
		return new Score(id, this.sportEventService.getSportEventById(id).getScorea(),
				this.sportEventService.getSportEventById(id).getScoreb());
	}

	@PostMapping("/SportsEvents")
	public void addSportEvent(@RequestBody SportEvent sportEvent) {
		sportEventService.addSportEvent(sportEvent);
	}

	// This end point only updates a new positive score
	// when it is intended to send a score and an id to update a sports event.
	@PutMapping("/SportsEvents/{id}")
	public void updateSportEvent(@PathVariable("id") Integer id, @RequestBody SportEvent sportEvent) {
		this.sportEventService.updateScoreSportEvent(sportEvent, id); 
	}

	@DeleteMapping("/SportsEvents/{id}")
	public void deleteSportEvent(@PathVariable("id") Integer id) {
		sportEventService.deleteSportEvent(id);
	}
}
