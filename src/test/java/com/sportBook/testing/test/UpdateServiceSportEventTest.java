package com.sportBook.testing.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.sportBook.model.SportEvent;
import com.sportBook.repository.SportEventRepository;
import com.sportBook.service.SportEventService;

@SpringBootTest
public class UpdateServiceSportEventTest {

	final int INCREASE_UNITS = 1;
	final int INCREASE_SIMULTANEOUSLY_UNITS_A = 1;
	final int INCREASE_SIMULTANEOUSLY_UNITS_B = 1;
	final int DECREASE_UNITS = -2;
	final int DECREASE_SIMULTANEOUSLY_UNITS_A = -2;
	final int DECREASE_SIMULTANEOUSLY_UNITS_B = -2;

	@Autowired
	private SportEventService sportEventService;

	@Autowired
	private SportEventRepository sportEventRepository;

	// IMPORTANT
	// The param's method 'insertId()' returns a number.
	// This sport event's id must exist in the database. The testing can not be
	// completed with a sports event that doesn't exist in
	// the database
	private int ID_SPORTEVENT_TO_ANALYSE;

	// You can add or decrease score possibilities. range (0-9)
	// The score marker only has place for one digit, then application's Front End
	// must restrict entry with 'input type number max = get score from api less 9'
	// Example: Score: 5-5, range added only 4 || 9-5 = 4
	// If an incorrect number is added, it must be corrected directly from the
	// database

	// increaseScoreTeam_A_Testing tries to add possible positive score for Team A
	@Test
	public void increaseScoreTeam_A_Testing() throws Exception {

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		// Get a sports event that exists in the database
		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		// Get score and save in a String variable
		String score = sportEvent.getScore();

		// Extract numeric values for team A & B
		int numericScore_A = extractScore_A(score);
		int numericScore_B = extractScore_B(score);

		// Build new score with the constant INCREASE_UNITS for testing
		String newUpdateScore = addUnitsToTeam_A(numericScore_A, numericScore_B, this.INCREASE_UNITS);

		// Evaluate if the update can be done
		responseAnalysisIncreaseScore(sportEvent, newUpdateScore, score);

	}

	// increaseScoreTeam_B_Testing try to add possible positive score for Team B
	@Test
	public void increaseScoreTeam_B_Testing() throws Exception {

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		String score = sportEvent.getScore();

		int numericScore_A = extractScore_A(score);

		int numericScore_B = extractScore_B(score);

		String newUpdateScore = addUnitsToTeam_B(numericScore_A, numericScore_B, this.INCREASE_UNITS);

		responseAnalysisIncreaseScore(sportEvent, newUpdateScore, score);

	}

	// increaseScoreTeam_AB_Testing try to add possible positive score for Team A
	// and
	// B simultaneously
	@Test
	public void increaseScoreTeam_AB_Testing() throws Exception {

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		String score = sportEvent.getScore();

		int numericScore_A = extractScore_A(score);

		int numericScore_B = extractScore_B(score);

		String newUpdateScore = addUnitsToTeam_AB(numericScore_A, numericScore_B, this.INCREASE_SIMULTANEOUSLY_UNITS_A,
				this.INCREASE_SIMULTANEOUSLY_UNITS_B);

		responseAnalysisIncreaseScore(sportEvent, newUpdateScore, score);

	}

	// decreaseScoreTeam_A_Testing shows that no units have been subtracted from
	// Team A's score
	@Test
	public void decreaseScoreTeam_A_Testing() throws Exception {

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		String score = sportEvent.getScore();

		int numericScore_A = extractScore_A(score);

		int numericScore_B = extractScore_B(score);

		String newUpdateScore = addUnitsToTeam_A(numericScore_A, numericScore_B, this.DECREASE_UNITS);

		// Evaluate if the update can be done
		responseAnalysisDecreaseScore(sportEvent, newUpdateScore, score);

	}

	// decreaseScoreTeam_B_Testing shows that no units have been subtracted from
	// Team B's score
	@Test
	public void decreaseScoreTeam_B_Testing() throws Exception {

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		String score = sportEvent.getScore();

		int numericScore_A = extractScore_A(score);

		int numericScore_B = extractScore_B(score);

		String newUpdateScore = addUnitsToTeam_B(numericScore_A, numericScore_B, this.DECREASE_UNITS);

		responseAnalysisDecreaseScore(sportEvent, newUpdateScore, score);

	}

	// decreaseScoreTeam_AB_Testing shows that no units have been subtracted from
	// Team A and Team B's score simultaneously
	@Test
	public void decreaseScoreTeam_AB_Testing() throws Exception {

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		String score = sportEvent.getScore();

		int numericScore_A = extractScore_A(score);

		int numericScore_B = extractScore_B(score);

		String newUpdateScore = addUnitsToTeam_AB(numericScore_A, numericScore_B, this.DECREASE_SIMULTANEOUSLY_UNITS_A,
				this.DECREASE_SIMULTANEOUSLY_UNITS_B);

		responseAnalysisDecreaseScore(sportEvent, newUpdateScore, score);

	}

	private int extractScore_A(String score) {
		char score_A = score.charAt(0);
		int numericScore_A = Character.getNumericValue(score_A);
		return numericScore_A;
	}

	private int extractScore_B(String score) {
		char score_B = score.charAt(score.length() - 1);
		int numericScore_B = Character.getNumericValue(score_B);
		return numericScore_B;
	}

	private String addUnitsToTeam_A(int numericScore_A, int numericScore_B, int units) {

		String newUpdateScore = (numericScore_A + units) + "-" + numericScore_B;
		return newUpdateScore;

	}

	private String addUnitsToTeam_B(int numericScore_A, int numericScore_B, int units) {

		String newUpdateScore = numericScore_A + "-" + (numericScore_B + units);
		return newUpdateScore;

	}

	private String addUnitsToTeam_AB(int numericScore_A, int numericScore_B, int simultaneouslyUnits_A,
			int simultaneouslyUnits_B) {

		String newUpdateScore = (numericScore_A + simultaneouslyUnits_A) + "-"
				+ (numericScore_B + simultaneouslyUnits_B);
		return newUpdateScore;

	}

	private void responseAnalysisIncreaseScore(SportEvent sportEvent, String newUpdateScore, String previusScore) {

		SportEvent sportEventUpdated = new SportEvent(sportEvent.getId(), sportEvent.getName(), newUpdateScore,
				sportEvent.getCollaborators(), sportEvent.getParticipants(), sportEvent.getLocation(),
				sportEvent.getDate(), sportEvent.getTime());

		this.sportEventService.updateScoreSportEvent(sportEventUpdated, sportEventUpdated.getId());

		SportEvent QuerySportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		assertEquals(sportEventUpdated.getId(), QuerySportEvent.getId(),
				"Compare ID: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getCollaborators(), QuerySportEvent.getCollaborators(),
				"Compare collaborators: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getDate(), QuerySportEvent.getDate(),
				"Compare date: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getLocation(), QuerySportEvent.getLocation(),
				"Compare location: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getName(), QuerySportEvent.getName(),
				"Compare name: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getParticipants(), QuerySportEvent.getParticipants(),
				"Compare participants: QuerySportEvent vs sportEventUpdated");

		// This assert checks the valid update
		assertEquals(sportEventUpdated.getScore(), QuerySportEvent.getScore(),
				"Compare score: QuerySportEvent vs sportEventUpdated");

		assertEquals(sportEventUpdated.getTime(), QuerySportEvent.getTime(),
				"Compare time: QuerySportEvent vs sportEventUpdated");

		// restores the changes made upon the execution of the test class in the
		// database
		SportEvent restoreSportEvent = new SportEvent(sportEvent.getId(), sportEvent.getName(), previusScore,
				sportEvent.getCollaborators(), sportEvent.getParticipants(), sportEvent.getLocation(),
				sportEvent.getDate(), sportEvent.getTime());

		this.sportEventRepository.save(restoreSportEvent);

	}

	private void responseAnalysisDecreaseScore(SportEvent sportEvent, String newUpdateScore, String previusScore) {

		SportEvent sportEventUpdated = new SportEvent(sportEvent.getId(), sportEvent.getName(), newUpdateScore,
				sportEvent.getCollaborators(), sportEvent.getParticipants(), sportEvent.getLocation(),
				sportEvent.getDate(), sportEvent.getTime());

		this.sportEventService.updateScoreSportEvent(sportEventUpdated, sportEventUpdated.getId());

		SportEvent QuerySportEvent = this.sportEventService.getSportEventById(ID_SPORTEVENT_TO_ANALYSE);

		assertEquals(sportEventUpdated.getId(), QuerySportEvent.getId(),
				"Compare ID: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getCollaborators(), QuerySportEvent.getCollaborators(),
				"Compare collaborators: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getDate(), QuerySportEvent.getDate(),
				"Compare date: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getLocation(), QuerySportEvent.getLocation(),
				"Compare location: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getName(), QuerySportEvent.getName(),
				"Compare name: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getParticipants(), QuerySportEvent.getParticipants(),
				"Compare participants: QuerySportEvent vs sportEventUpdated");

		// This assert checks the invalid update
		assertNotEquals(sportEventUpdated.getScore(), QuerySportEvent.getScore(),
				"Compare score: QuerySportEvent vs sportEventUpdated");

		assertEquals(sportEventUpdated.getTime(), QuerySportEvent.getTime(),
				"Compare time: QuerySportEvent vs sportEventUpdated");

		// restores the changes made upon the execution of the test class in the
		// database
		SportEvent restoreSportEvent = new SportEvent(sportEvent.getId(), sportEvent.getName(), previusScore,
				sportEvent.getCollaborators(), sportEvent.getParticipants(), sportEvent.getLocation(),
				sportEvent.getDate(), sportEvent.getTime());

		this.sportEventRepository.save(restoreSportEvent);
	}

	private void setID_SPORTEVENT_TO_ANALYSE(int iD_SPORTEVENT_TO_ANALYSE) {
		this.ID_SPORTEVENT_TO_ANALYSE = iD_SPORTEVENT_TO_ANALYSE;
	}

}