package skate.tracker.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import skate.tracker.controller.model.EventData;
import skate.tracker.controller.model.LocationData;
import skate.tracker.controller.model.LocationDataResponseDto;
import skate.tracker.controller.model.SkaterData;
import skate.tracker.controller.model.SkaterEventDataResponse;
import skate.tracker.dao.SkateTrackerDaoEvent;
import skate.tracker.dao.SkateTrackerDaoLocation;
import skate.tracker.dao.SkateTrackerDaoSkater;
import skate.tracker.entity.Event;
import skate.tracker.entity.Location;
import skate.tracker.entity.Skater;

@ExtendWith(MockitoExtension.class)
public class SkateTrackerServiceTest extends TestDataBuild{

	@Mock
	private SkateTrackerDaoSkater skaterRepository;
	@Mock
	private SkateTrackerDaoEvent eventRepository;
	@Mock
	private SkateTrackerDaoLocation locationRepository;

	@InjectMocks
	private SkateTrackerService skaterService;

	@Test
	void testGetAllSkaters() {
		// Given Skater
		Skater skater = getSkater();

		List<Skater> skaterEntites = new ArrayList<>();
		skaterEntites.add(skater);

		List<SkaterEventDataResponse> expected = expectedSkaterReponseList(skaterEntites);

		// When Repository is called
		when(skaterRepository.findAll()).thenReturn(skaterEntites);
		List<SkaterEventDataResponse> result = skaterService.getAllSkaters();

		// Then making sure that the entity matches the result returned
		assertThat(expected).isEqualTo(result);
	}

	@Test
	void testGetAllLocations() {
		// Given: Location
		Location location = getLocation();

		// Given: Create an Event to add to Location
		Event event = getEvent(location);

		// Add Event to the location entity and return expected list
		List<Location> locationEntites = addEventsToLocation(event, location);
		List<LocationDataResponseDto> responseList = expectedReponseList(locationEntites);

		// When Repository, then service is called
		when(locationRepository.findAll()).thenReturn(locationEntites);
		List<LocationDataResponseDto> result = skaterService.getAllLocaitons();

		// Then making sure that the entity matches the result returned
		assertThat(responseList).isEqualTo(result);
	}

	@Test
	void testSaveLocationData() {
		// Given Location data to convert to a location
		LocationData location = getLocationData();
		Location loc = location.toLocation();

		// When location is saved
		when(locationRepository.save(loc)).thenReturn(loc);
		// When service is run
		LocationData dbData = skaterService.saveLocationData(location);

		// Than
		assertThat(dbData).isNotNull();
		assertThat(dbData.getLocationId()).isEqualTo(1L);
		assertThat(dbData.getLocationName()).isEqualTo("Test Location");

		verify(locationRepository, times(1)).save(loc);
	}

	@Test
	void testSaveEventData() {
		// Given a location to add to event
		Location loc = getLocation();
		// Given event data
		EventData eventData = getEventData(loc);
		Event event = eventData.toEvent();

		// When event is save
		when(eventRepository.save(event)).thenReturn(event);
		// When service runs
		EventData dbData = skaterService.saveEventData(eventData);

		// Then
		assertThat(dbData).isNotNull();
		assertThat(dbData.getEventId()).isEqualTo(1L);
		assertThat(dbData.getEventName()).isEqualTo("Test Event");

		verify(eventRepository, times(1)).save(event);
	}

	@Test
	void testAddSkaterToEvent() {
		// Given skater
		SkaterData skaterData = getSkaterData();
		Skater skater = skaterData.toSkater();
		// Given: Event with a location added
		Location location = getLocation();
		Event event = getEvent(location);
		
		List<Event> expectedEvents = new ArrayList<>();
		expectedEvents.add(event);
		

		// Find event by ID
		when(eventRepository.findById(event.getEventId())).thenReturn(Optional.of(event));
		// Find skater by ID
		when(skaterRepository.findById(skater.getSkaterId())).thenReturn(Optional.of(skater));

		//When the service is called to add a skater event
		SkaterEventDataResponse SkaterEventDataResponse = skaterService.addSkaterToEvent(skater.getSkaterId(),
				event.getEventId());

		// Then
		assertThat(SkaterEventDataResponse).isNotNull();
		assertThat(SkaterEventDataResponse.getSkaterName()).isEqualTo("Test Skater");
		assertThat(SkaterEventDataResponse.getEvents()).isEqualTo(addEventNames(expectedEvents));
		
		verify(skaterRepository, times(1)).save(skater);

	}
	
	@Test
	void testDeleteLocation() {
		//Given location to delete
		Location location = getLocation();
		
		// Find location by ID
		when(locationRepository.findById(location.getLocationId())).thenReturn(Optional.of(location));
		
		skaterService.deleteLocation(location.getLocationId());
		verify(locationRepository, times(1)).delete(location);
	
		
	}
	
}
