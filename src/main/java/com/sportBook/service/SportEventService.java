package com.sportBook.service;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
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

	@Lock(LockModeType.PESSIMISTIC_READ)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "9000")})
	public void updateSportEvent(SportEvent sportEvent, Integer id) {
		sportEventRepository.save(sportEvent);
	}

	@Lock(LockModeType.PESSIMISTIC_READ)
	@QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "9000")})
	public void deleteSportEvent(Integer id) {
		sportEventRepository.deleteById(id);
	}

	// Service customized: the score will be updated only if the received values are positive values.
	// This method receives through parameters an edited sports event and an id. The id
	// is the same for the edited sports event as it is for the unedited sports
	// event
	@Lock(LockModeType.PESSIMISTIC_READ)
	public void updateScoreSportEvent(SportEvent sportEvent, Integer id) {

		// Get at unedited sports event previous to edit by id
		SportEvent previousSportEvent = sportEventService.getSportEventById(id);

		// Get previous sports event's score to analyze
		String previousSportEventScore = previousSportEvent.getScore();

		// The score values is then converted into numeric values
		char score_A_previousSportEvent = previousSportEventScore.charAt(0);
		int numericScore_A_previousSportEvent = Character.getNumericValue(score_A_previousSportEvent);

		char score_B_previousSportEvent = previousSportEventScore.charAt(previousSportEventScore.length() - 1);
		int numericScore_B_previousSportEvent = Character.getNumericValue(score_B_previousSportEvent);

		// Get edited sports event's score to analyze
		String CurrentScoreSportEvent = sportEvent.getScore();

		// The score values is then converted into numeric values
		char CurrentScore_A = CurrentScoreSportEvent.charAt(0);
		int numericCurrentScore_A = Character.getNumericValue(CurrentScore_A);

		char CurrentScore_B = CurrentScoreSportEvent.charAt(CurrentScoreSportEvent.length() - 1);
		int numericCurrentScore_B = Character.getNumericValue(CurrentScore_B);

		// Evaluate: It will update the score only if the score took positive value or
		// values
		if (numericScore_A_previousSportEvent <= numericCurrentScore_A
				&& numericScore_B_previousSportEvent <= numericCurrentScore_B) {

			String newUpdateScore = numericCurrentScore_A + "-" + numericCurrentScore_B;

			SportEvent SportEventUpdate = new SportEvent(id, previousSportEvent.getName(), newUpdateScore,
					previousSportEvent.getCollaborators(), previousSportEvent.getParticipants(),
					previousSportEvent.getLocation(), previousSportEvent.getDate(), previousSportEvent.getTime());

			sportEventService.addSportEvent(SportEventUpdate);
		}

	}

}
