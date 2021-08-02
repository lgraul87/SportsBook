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
//@CrossOrigin(origins = "http://localhost:8050/SportsEvents")
public class SportEventController {

	private SportEventService sportEventService;

	public SportEventController(SportEventService sportEventService) {
		this.sportEventService = sportEventService;
	}

	@GetMapping("/SportsEvents")
	private List<SportEvent> getSportEvent() {
		return sportEventService.getSportsEvents();
	}

	@GetMapping("/SportsEvents/{id}")
	private SportEvent getSportEventById(@PathVariable("id") Integer id) {
		return sportEventService.getSportEventById(id);
	}

	@GetMapping("/SportsEvents/{id}/Score")
	private Score getScoreOfSportEvent(@PathVariable("id") Integer id) {

		return new Score(this.sportEventService.getSportEventById(id).getId(),
				this.sportEventService.getSportEventById(id).getScore());
	}

	@PostMapping("/SportsEvents")
	private Integer addSportEvent(@RequestBody SportEvent sportEvent) {
		sportEventService.addSportEvent(sportEvent);
		return sportEvent.getId();
	}

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
