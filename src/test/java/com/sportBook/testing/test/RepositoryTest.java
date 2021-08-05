package com.sportBook.testing.test;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sportBook.SportsBookApplication;
import com.sportBook.controller.SportEventController;
import com.sportBook.model.Score;
import com.sportBook.model.SportEvent;
import com.sportBook.service.SportEventService;

@SpringBootTest
public class RepositoryTest {

	@Autowired
	SportEventService sportEventService;

	@Autowired
	SportEventController sportEventController;

	@Test
	public void getSportsEventsTest() throws Exception {
		initDB();
		sportEventController.getSportsEvents();
	}

	//Testing all the methods of the project
	@Test
	public void main() {
		String[] arguments = { "1", "2", "3" };
		SportsBookApplication.main(arguments);
	}

	@Test
	public void getScoreOfSportEvent() {
		sportEventController.getScoreOfSportEvent(sportEventService.getSportsEvents().get(0).getId());
	}

	@Test
	public void newScore() {
		new Score();
	}

	@Test
	public void getId() {
		new Score().getId();
	}

	@Test
	public Integer getSportEventByIdTest() throws Exception {
		SportEvent sportEvent = sportEventController
				.getSportEventById(sportEventService.getSportsEvents().get(0).getId());
		return sportEvent.getId();

	}

	@Test
	public void addSportEventTest() throws Exception {

		sportEventController.addSportEvent(
				sportEventController.getSportEventById(sportEventService.getSportsEvents().get(0).getId()));

	}

	@Test
	public void updateSportEventTest() throws Exception {
		SportEvent sportEvent = sportEventController
				.getSportEventById(sportEventService.getSportsEvents().get(0).getId());
		sportEventController.updateSportEvent(sportEvent.getId(), sportEvent);
		sportEventController.deleteSportEvent(sportEvent.getId());

	}

	@Test
	public void deleteSportEventTest() throws Exception {
		SportEvent sportEvent = new SportEvent(1, "Team A vs Team B", 1, 0, "Team A", "participants", "Madrid",
				new Date(), "17:00pm to 18:30pm ");
		sportEventController.addSportEvent(sportEvent);
		int id = getSportEventByIdTest();
		sportEventController.deleteSportEvent(id);
		initDB();

	}

	// This initDB (method) evaluates if the table 'sportevent' is empty. When its
	// empty, it pushes a new sports event. When it is not empty it doesn't do anything
	public void initDB() throws Exception {
		if (sportEventService.getSportsEvents().size() < 1) {
			SportEvent sportEvent = new SportEvent(1, "Team A vs Team B", 1, 0, "Team A", "participants", "Madrid",
					new Date(), "17:00pm to 18:30pm ");
			sportEventService.addSportEvent(sportEvent);
		}
	}
}
