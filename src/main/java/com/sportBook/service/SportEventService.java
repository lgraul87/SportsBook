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

	// Service customized: only update the score if get positive values.
	// This method receives by parameters an edited sporting event and an id. The id
	// is the same for the edited sporting event as it is for the unedited sporting
	// event
	public void updateScoreSportEvent(SportEvent sportEvent, Integer id) {

		// Get at unedited sporting event previous to edit by id
		SportEvent previousSportEvent = sportEventService.getSportEventById(id);

		// Get previous score's sporting event for analyze
		String previousSportEventScore = previousSportEvent.getScore();

		// Will be take the value from score and convert to numeric values
		char score_A_previousSportEvent = previousSportEventScore.charAt(0);
		int numericScore_A_previousSportEvent = Character.getNumericValue(score_A_previousSportEvent);

		char score_B_previousSportEvent = previousSportEventScore.charAt(previousSportEventScore.length() - 1);
		int numericScore_B_previousSportEvent = Character.getNumericValue(score_B_previousSportEvent);

		// Get edited score's sporting event for analyze
		String CurrentScoreSportEvent = sportEvent.getScore();

		// Will be take the value from score and convert to numeric values
		char CurrentScore_A = CurrentScoreSportEvent.charAt(0);
		int numericCurrentScore_A = Character.getNumericValue(CurrentScore_A);

		char CurrentScore_B = CurrentScoreSportEvent.charAt(CurrentScoreSportEvent.length() - 1);
		int numericCurrentScore_B = Character.getNumericValue(CurrentScore_B);

		// Evaluate: Only can update the score if the score took positive value or
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
