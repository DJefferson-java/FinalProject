package skate.tracker.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import skate.tracker.controller.model.EventData;
import skate.tracker.controller.model.LocationData;
import skate.tracker.controller.model.LocationDataResponseDto;
import skate.tracker.controller.model.SkaterData;
import skate.tracker.controller.model.SkaterEventDataResponse;
import skate.tracker.entity.Event;
import skate.tracker.entity.Location;
import skate.tracker.entity.Skater;

public class TestDataBuild {

	protected List<LocationDataResponseDto> expectedReponseList(List<Location> locations) {
		List<LocationDataResponseDto> responseList = new ArrayList<>();
		LocationDataResponseDto locationDataResponseDto = new LocationDataResponseDto();
		for (Location location : locations) {
			locationDataResponseDto.setLocaitonId(location.getLocationId());
			locationDataResponseDto.setLocationName(location.getLocationName());
			locationDataResponseDto.setAddress(location.getAddress());
			locationDataResponseDto.setCity(location.getCity());
			locationDataResponseDto.setState(location.getState());
			locationDataResponseDto.setEventNames(addEventNames(extractLocationEvents(location)));
		}
		responseList.add(locationDataResponseDto);
		return responseList;
	}

	protected List<SkaterEventDataResponse> expectedSkaterReponseList(List<Skater> skaters) {
		List<SkaterEventDataResponse> responseList = new ArrayList<>();
		SkaterEventDataResponse skaterDataResponseDto = new SkaterEventDataResponse();
		for (Skater skater : skaters) {
			skaterDataResponseDto.setSkaterName(skater.getSkaterName());
			skaterDataResponseDto.setSkaterAge(skater.getSkaterAge());
			skaterDataResponseDto.setEvents(addEventNames(extractSkaterEvents(skater)));
		}
		responseList.add(skaterDataResponseDto);
		return responseList;
	}

	protected List<Location> addEventsToLocation(Event eventEntity, Location location) {
		Set<Event> eventSet = new HashSet<>();
		eventSet.add(eventEntity);
		location.setEvents(eventSet);

		List<Location> locationEntites = new ArrayList<>();
		locationEntites.add(location);

		return locationEntites;
	}

	protected Event getEvent(Location location) {
		EventData event = new EventData(1L, "Test Event", "10/25/2025", "30", location);

		return event.toEvent();
	}

	protected EventData getEventData(Location location) {
		return new EventData(1L, "Test Event", "10/25/2025", "30", location);
	}

	protected Location getLocation() {
		LocationData location = new LocationData(1L, "Test Location", "testAddress", "testcity", "testState",
				"testZip");

		return location.toLocation();
	}

	protected LocationData getLocationData() {
		return new LocationData(1L, "Test Location", "testAddress", "testcity", "testState", "testZip");
	}

	protected Skater getSkater() {
		SkaterData skater = new SkaterData();
		skater.setSkaterId(1L);
		skater.setSkaterName("Test Skater");
		skater.setSkaterAge(14);

		return skater.toSkater();
	}

	protected SkaterData getSkaterData() {
		SkaterData skater = new SkaterData();
		skater.setSkaterId(1L);
		skater.setSkaterName("Test Skater");
		skater.setSkaterAge(14);

		return skater;
	}

	protected List<String> addEventNames(List<Event> locationEvents) {
		List<String> eventNames = new ArrayList<>();
		for (Event event : locationEvents) {
			eventNames.add(event.getEventName());
		}
		return eventNames;
	}

	protected List<Event> extractLocationEvents(Location location) {
		return location.getEvents().stream().toList();
	}

	protected List<Event> extractSkaterEvents(Skater skater) {
		return skater.getEvents().stream().toList();
	}
}
