package com.sportBook.testing.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sportBook.model.Score;
import com.sportBook.model.SportEvent;
import com.sportBook.repository.SportEventRepository;
import com.sportBook.service.SportEventService;

@SpringBootTest
public class UpdateServiceSportEventTest {

	private int ID_SPORTEVENT_TO_ANALYSE;

	// Can change values for testing other possibilities
	// Only positive
	final int INCREASE_UNITS = 1;
	final int INCREASE_SIMULTANEOUSLY_UNITS_A = 1;
	final int INCREASE_SIMULTANEOUSLY_UNITS_B = 1;

	// Only negative
	final int DECREASE_UNITS = -2;
	final int DECREASE_SIMULTANEOUSLY_UNITS_A = -2;
	final int DECREASE_SIMULTANEOUSLY_UNITS_B = -2;

	@Autowired
	private SportEventService sportEventService;

	@Autowired
	private SportEventRepository sportEventRepository;

	// increaseScoreTeam_A_Testing tries to add possible positive score for Team A
	@Test
	public void increaseScoreTeam_A_Testing() throws Exception {

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		// Get a sports event that exists in the database
		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		int numericScore_A = sportEvent.getScorea();
		int numericScore_B = sportEvent.getScoreb();

		Score score = new Score(sportEvent.getId(), numericScore_A, numericScore_B);

		Score newUpdateScore = addUnitsToTeam_A(sportEvent.getId(), numericScore_A, numericScore_B, INCREASE_UNITS);

		responseAnalysisIncreaseScore(sportEvent, newUpdateScore, score);

	}

	// increaseScoreTeam_B_Testing try to add possible positive score for Team B
	@Test
	public void increaseScoreTeam_B_Testing() throws Exception {

		initDB();

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		// Get a sports event that exists in the database
		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		// Extract numeric values for team A & B
		int numericScore_A = sportEvent.getScorea();
		int numericScore_B = sportEvent.getScoreb();

		Score score = new Score(sportEvent.getId(), numericScore_A, numericScore_B);

		// Build new score with the constant INCREASE_UNITS for testing
		Score newUpdateScore = addUnitsToTeam_B(sportEvent.getId(), numericScore_A, numericScore_B, INCREASE_UNITS);

		// Evaluate if the update can be done
		responseAnalysisIncreaseScore(sportEvent, newUpdateScore, score);

	}

	// increaseScoreTeam_AB_Testing try to add possible positive score for Team A
	// and B simultaneously
	@Test
	public void increaseScoreTeam_AB_Testing() throws Exception {

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		int numericScore_A = sportEvent.getScorea();

		int numericScore_B = sportEvent.getScoreb();

		Score score = new Score(sportEvent.getId(), numericScore_A, numericScore_B);

		Score newUpdateScore = addUnitsToTeam_AB(sportEvent.getId(), numericScore_A, numericScore_B,
				INCREASE_SIMULTANEOUSLY_UNITS_A, INCREASE_SIMULTANEOUSLY_UNITS_B);

		responseAnalysisIncreaseScore(sportEvent, newUpdateScore, score);

	}

	// decreaseScoreTeam_A_Testing shows that no units have been subtracted from
	// Team A's score
	@Test
	public void decreaseScoreTeam_A_Testing() throws Exception {

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		// Get a sports event that exists in the database
		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		// Extract numeric values for team A & B
		int numericScore_A = sportEvent.getScorea();
		int numericScore_B = sportEvent.getScoreb();

		Score score = new Score(sportEvent.getId(), numericScore_A, numericScore_B);

		Score newUpdateScore = addUnitsToTeam_A(sportEvent.getId(), numericScore_A, numericScore_B, DECREASE_UNITS);

		responseAnalysisDecreaseScore_A(sportEvent, newUpdateScore, score);

	}

	// decreaseScoreTeam_B_Testing shows that no units have been subtracted from
	// Team B's score
	@Test
	public void decreaseScoreTeam_B_Testing() throws Exception {

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		// Get a sports event that exists in the database
		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		// Extract numeric values for team A & B
		int numericScore_A = sportEvent.getScorea();
		int numericScore_B = sportEvent.getScoreb();

		Score score = new Score(sportEvent.getId(), numericScore_A, numericScore_B);

		// Build new score with the constant INCREASE_UNITS for testing
		Score newUpdateScore = addUnitsToTeam_B(sportEvent.getId(), numericScore_A, numericScore_B, DECREASE_UNITS);

		// Evaluate if the update can be done
		responseAnalysisDecreaseScore_B(sportEvent, newUpdateScore, score);

	}

	// decreaseScoreTeam_AB_Testing shows that no units have been subtracted from
	// Team A and Team B's score simultaneously
	@Test
	public void decreaseScoreTeam_AB_Testing() throws Exception {

		setID_SPORTEVENT_TO_ANALYSE(sportEventService.getSportsEvents().get(0).getId());

		SportEvent sportEvent = this.sportEventService.getSportEventById(this.ID_SPORTEVENT_TO_ANALYSE);

		int numericScore_A = sportEvent.getScorea();

		int numericScore_B = sportEvent.getScoreb();

		Score score = new Score(sportEvent.getId(), numericScore_A, numericScore_B);

		Score newUpdateScore = addUnitsToTeam_AB(sportEvent.getId(), numericScore_A, numericScore_B,
				DECREASE_SIMULTANEOUSLY_UNITS_A, DECREASE_SIMULTANEOUSLY_UNITS_B);

		responseAnalysisDecreaseScore_AB(sportEvent, newUpdateScore, score);

	}

	@Test
	public void updateSportEvent() throws Exception {
		SportEvent sportevent = sportEventService.getSportsEvents().get(0);
		sportEventService.updateSportEvent(sportevent, sportevent.getId());
	}

	// Build new score with the constant INCREASE_UNITS or DECREASE_UNITSfor testing
	private Score addUnitsToTeam_A(int id, int numericScore_A, int numericScore_B, int units) {
		return new Score(id, (numericScore_A + units), numericScore_B);
	}

	// Build new score with the constant INCREASE_UNITS or DECREASE_UNITS for testing
	private Score addUnitsToTeam_B(int id, int numericScore_A, int numericScore_B, int units) {
		return new Score(id, numericScore_A, (numericScore_B + units));
	}

	// Build new score with the constant INCREASE_SIMULTANEOUSLY_UNITS_A &
	// INCREASE_SIMULTANEOUSLY_UNITS_B or DECREASE_SIMULTANEOUSLY_UNITS_A &
	// DECREASE_SIMULTANEOUSLY_UNITS_B for testing
	private Score addUnitsToTeam_AB(int id, int numericScore_A, int numericScore_B, int simultaneouslyUnits_A,
			int simultaneouslyUnits_B) {
		return new Score(id, (numericScore_A + simultaneouslyUnits_A), (numericScore_B + simultaneouslyUnits_B));
	}

	// Evaluate if the update can be done
	private void responseAnalysisIncreaseScore(SportEvent sportEvent, Score newUpdateScore, Score previusScore) {

		SportEvent sportEventUpdated = new SportEvent(sportEvent.getId(), sportEvent.getName(),
				newUpdateScore.getScore_A(), newUpdateScore.getScore_B(), sportEvent.getCollaborators(),
				sportEvent.getParticipants(), sportEvent.getLocation(), sportEvent.getDate(), sportEvent.getTime());

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
		assertEquals(sportEventUpdated.getScorea(), QuerySportEvent.getScorea(),
				"Compare score: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getScoreb(), QuerySportEvent.getScoreb(),
				"Compare score: QuerySportEvent vs sportEventUpdated");

		assertEquals(sportEventUpdated.getTime(), QuerySportEvent.getTime(),
				"Compare time: QuerySportEvent vs sportEventUpdated");

		// restores the changes made upon the execution of the test class in the
		// database
		SportEvent restoreSportEvent = new SportEvent(sportEvent.getId(), sportEvent.getName(),
				previusScore.getScore_A(), previusScore.getScore_B(), sportEvent.getCollaborators(),
				sportEvent.getParticipants(), sportEvent.getLocation(), sportEvent.getDate(), sportEvent.getTime());

		this.sportEventRepository.save(restoreSportEvent);

	}

	// Evaluate if the update can be done
	private void responseAnalysisDecreaseScore_A(SportEvent sportEvent, Score newUpdateScore, Score score) {

		SportEvent sportEventUpdated = new SportEvent(sportEvent.getId(), sportEvent.getName(),
				newUpdateScore.getScore_A(), newUpdateScore.getScore_B(), sportEvent.getCollaborators(),
				sportEvent.getParticipants(), sportEvent.getLocation(), sportEvent.getDate(), sportEvent.getTime());

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
		assertNotEquals(sportEventUpdated.getScorea(), QuerySportEvent.getScorea(),
				"Compare score: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getScoreb(), QuerySportEvent.getScoreb(),
				"Compare score: QuerySportEvent vs sportEventUpdated");

		assertEquals(sportEventUpdated.getTime(), QuerySportEvent.getTime(),
				"Compare time: QuerySportEvent vs sportEventUpdated");

		// restores the changes made upon the execution of the test class in the
		// database
		SportEvent restoreSportEvent = new SportEvent(sportEvent.getId(), sportEvent.getName(), sportEvent.getScorea(),
				sportEvent.getScoreb(), sportEvent.getCollaborators(), sportEvent.getParticipants(),
				sportEvent.getLocation(), sportEvent.getDate(), sportEvent.getTime());

		this.sportEventRepository.save(restoreSportEvent);
	}

	// Evaluate if the update can be done
	private void responseAnalysisDecreaseScore_B(SportEvent sportEvent, Score newUpdateScore, Score score) {
		SportEvent sportEventUpdated = new SportEvent(sportEvent.getId(), sportEvent.getName(),
				newUpdateScore.getScore_A(), newUpdateScore.getScore_B(), sportEvent.getCollaborators(),
				sportEvent.getParticipants(), sportEvent.getLocation(), sportEvent.getDate(), sportEvent.getTime());

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
		assertEquals(sportEventUpdated.getScorea(), QuerySportEvent.getScorea(),
				"Compare score: QuerySportEvent vs sportEventUpdated");
		// This assert checks the invalid update
		assertNotEquals(sportEventUpdated.getScoreb(), QuerySportEvent.getScoreb(),
				"Compare score: QuerySportEvent vs sportEventUpdated");

		assertEquals(sportEventUpdated.getTime(), QuerySportEvent.getTime(),
				"Compare time: QuerySportEvent vs sportEventUpdated");

		// restores the changes made upon the execution of the test class in the
		// database
		SportEvent restoreSportEvent = new SportEvent(sportEvent.getId(), sportEvent.getName(), sportEvent.getScorea(),
				sportEvent.getScoreb(), sportEvent.getCollaborators(), sportEvent.getParticipants(),
				sportEvent.getLocation(), sportEvent.getDate(), sportEvent.getTime());

		this.sportEventRepository.save(restoreSportEvent);
	}

	// Evaluate if the update can be done
	private void responseAnalysisDecreaseScore_AB(SportEvent sportEvent, Score newUpdateScore, Score previusScore) {

		SportEvent sportEventUpdated = new SportEvent(sportEvent.getId(), sportEvent.getName(),
				newUpdateScore.getScore_A(), newUpdateScore.getScore_B(), sportEvent.getCollaborators(),
				sportEvent.getParticipants(), sportEvent.getLocation(), sportEvent.getDate(), sportEvent.getTime());

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
		assertNotEquals(sportEventUpdated.getScorea(), QuerySportEvent.getScorea(),
				"Compare score: QuerySportEvent vs sportEventUpdated");
		// This assert checks the invalid update
		assertNotEquals(sportEventUpdated.getScoreb(), QuerySportEvent.getScoreb(),
				"Compare score: QuerySportEvent vs sportEventUpdated");
		assertEquals(sportEventUpdated.getTime(), QuerySportEvent.getTime(),
				"Compare time: QuerySportEvent vs sportEventUpdated");

		// restores the changes made upon the execution of the test class in the
		// database
		SportEvent restoreSportEvent = new SportEvent(sportEvent.getId(), sportEvent.getName(),
				previusScore.getScore_A(), previusScore.getScore_B(), sportEvent.getCollaborators(),
				sportEvent.getParticipants(), sportEvent.getLocation(), sportEvent.getDate(), sportEvent.getTime());

		this.sportEventRepository.save(restoreSportEvent);
	}

	// This method sets a valid value found for this attribute
	private void setID_SPORTEVENT_TO_ANALYSE(int iD_SPORTEVENT_TO_ANALYSE) {
		this.ID_SPORTEVENT_TO_ANALYSE = iD_SPORTEVENT_TO_ANALYSE;
	}

	// This initDB (method) evaluates if the table 'sportevent' is empty. When it's
	// empty, it pushes a new sportevent. When it isn't empty it doesn't do anything
	public void initDB() throws Exception {
		if (sportEventService.getSportsEvents().size() < 1) {
			SportEvent sportEvent = new SportEvent(1, "Team A vs Team B", 1, 0, "Team A", "participants", "Madrid",
					new Date(), "17:00pm to 18:30pm ");
			sportEventService.addSportEvent(sportEvent);
		}
	}
}