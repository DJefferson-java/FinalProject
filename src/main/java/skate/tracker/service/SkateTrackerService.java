package skate.tracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service
public class SkateTrackerService {

	@Autowired
	private SkateTrackerDaoLocation skateTrackerDaoLocation;

	@Autowired
	private SkateTrackerDaoEvent skateTrackerDaoEvent;

	@Autowired
	private SkateTrackerDaoSkater skateTrackerDaoSkater;

	@Transactional(readOnly = false)
	public LocationData saveLocationData(LocationData locationData) {
		Location location = locationData.toLocation();
		Location dbLocation = skateTrackerDaoLocation.save(location);

		return new LocationData(dbLocation);
	}

	@Transactional(readOnly = false)
	public EventData saveEventData(EventData eventData) {
		Event event = eventData.toEvent();
		Event dbEvent = skateTrackerDaoEvent.save(event);

		return new EventData(dbEvent);
	}

	@Transactional(readOnly = false)
	public SkaterData saveSkaterData(SkaterData skaterData) {
		Skater skater = skaterData.toSkater();
		Skater dbSkater = skateTrackerDaoSkater.save(skater);

		return new SkaterData(dbSkater);
	}

	private Skater findSkaterById(Long skaterId) {
		return skateTrackerDaoSkater.findById(skaterId)
				.orElseThrow(() -> new NoSuchElementException("Skater with ID " + skaterId + " was not found"));
	}

	private Event findEventById(Long eventId) {
		return skateTrackerDaoEvent.findById(eventId)
				.orElseThrow(() -> new NoSuchElementException("Event with ID " + eventId + " was not found"));
	}

	private Location findLocationById(Long locationId) {
		return skateTrackerDaoLocation.findById(locationId)
				.orElseThrow(() -> new NoSuchElementException("Location with ID " + locationId + " was not found"));
	}
	
	public LocationDataResponseDto getLocationById(Long locationId) {
		Location location = findLocationById(locationId);
		LocationDataResponseDto locationDto = new LocationDataResponseDto();
		locationDto.setAddress(location.getAddress());
		locationDto.setLocationName(location.getLocationName());
		locationDto.setState(location.getState());
		locationDto.setCity(location.getCity());
		locationDto.setEventNames(addEventNames(extractLocationEvents(location)));
		return locationDto;
	}
	

	@Transactional(readOnly = true)
	public List<LocationDataResponseDto> getAllLocaitons() {
		List<Location> locationEntities = skateTrackerDaoLocation.findAll();

		return locationEntities.stream()
				.map(location -> new LocationDataResponseDto(location.getLocationName(), location.getCity(),
						location.getState(), location.getAddress(), addEventNames(extractLocationEvents(location))))
				.toList();
	}

	@Transactional(readOnly = true)
	public List<SkaterEventDataResponse> getAllSkaters() {
		List<Skater> skaters = skateTrackerDaoSkater.findAll();

		return skaters.stream().map(skater -> new SkaterEventDataResponse(skater.getSkaterName(), skater.getSkaterAge(),
				addEventNames(extractSkaterEvents(skater)))).toList();
	}

	private List<Event> extractSkaterEvents(Skater skater) {
		return skater.getEvents().stream().toList();
	}

	private List<Event> extractLocationEvents(Location location) {
		return location.getEvents().stream().toList();
	}

	private List<String> addEventNames(List<Event> allEvents) {
		List<String> eventNames = new ArrayList<>();
		for (Event event : allEvents) {
			eventNames.add(event.getEventName());
		}
		return eventNames;
	}

	public SkaterEventDataResponse addSkaterToEvent(Long skaterId, Long eventId) {
		Skater skater = findSkaterById(skaterId);
		Event event = findEventById(eventId);

		if (!skater.getEvents().contains(event)) {
			skater.getEvents().add(event);
		}
		skateTrackerDaoSkater.save(skater);

		return new SkaterEventDataResponse(skater.getSkaterName(), skater.getSkaterAge(),
				addEventNames(extractSkaterEvents(skater)));
	}

	public List<SkaterEventDataResponse> getSkaterById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional (readOnly = false)
	public void deleteLocation(Long locationId) {
		Location location = findLocationById(locationId);
		skateTrackerDaoLocation.delete(location);	
	}

}
