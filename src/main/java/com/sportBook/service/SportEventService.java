package com.sportBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.sportBook.model.SportEvent;
import com.sportBook.repository.SportEventRepository;

@Service
public class SportEventService {

	@Autowired
	private SportEventService sportEventService;

	@Autowired
	private SportEventRepository sportEventRepository;

	public List<SportEvent> getSportsEvents() {
		return sportEventRepository.findAll();
	}

	public SportEvent getSportEventById(Integer id) {
		return sportEventRepository.findById(id).get();
	}

	public void addSportEvent(SportEvent sportEvent) {
		sportEventRepository.save(sportEvent);
	}

	public void updateSportEvent(SportEvent sportEvent, Integer id) {
		sportEventRepository.save(sportEvent);
	}

	public void deleteSportEvent(Integer id) {
		sportEventRepository.deleteById(id);
	}

	// Service customized: the score will be updated only if the received values are
	// positive values.This method receives through parameters an edited sports
	// event and an id. The
	// id is the same for the edited sports event as it is for the unedited sports
	// event
	public void updateScoreSportEvent(SportEvent sportEvent, Integer id) {

		SportEvent previousSportEvent = sportEventService.getSportEventById(id);

		int previousScore_A = previousSportEvent.getScorea();

		int previousScore_B = previousSportEvent.getScoreb();

		int currentScore_A = sportEvent.getScorea();

		int currentScore_B = sportEvent.getScoreb();

		if (previousScore_A <= currentScore_A && previousScore_B <= currentScore_B) {

			SportEvent SportEventUpdate = new SportEvent(previousSportEvent.getId(), previousSportEvent.getName(),
					currentScore_A, currentScore_B, previousSportEvent.getCollaborators(),
					previousSportEvent.getParticipants(), previousSportEvent.getLocation(),
					previousSportEvent.getDate(), previousSportEvent.getTime());

			sportEventService.addSportEvent(SportEventUpdate);
		}

	}

}
