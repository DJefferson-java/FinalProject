package skate.tracker.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import skate.tracker.controller.model.EventData;
import skate.tracker.controller.model.EventDataReponseDto;
import skate.tracker.controller.model.LocationData;
import skate.tracker.controller.model.LocationDataResponseDto;
import skate.tracker.controller.model.SkaterData;
import skate.tracker.controller.model.SkaterEventDataResponse;
import skate.tracker.entity.Event;
import skate.tracker.service.SkateTrackerService;

@RestController
@RequestMapping("/skate_tracker")
@Slf4j
public class SkateTrackerController {

	@Autowired
	private SkateTrackerService skateTrackerService;

	//Creates a location
	@PostMapping("/addLocation")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LocationData createLocation(@RequestBody LocationData locationData) {
		log.info("Inserting data into Location Table {}" + locationData);
		return skateTrackerService.saveLocationData(locationData);

	}

	//Creates an Event
	@PostMapping("/addEvent")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EventData createEvent(@RequestBody EventData eventData) {
		log.info("Inserting data into Event Table {}" + eventData);
		return skateTrackerService.saveEventData(eventData);

	}

	//Creates a skater
	@PostMapping("/addSkater")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SkaterData createSkater(@RequestBody SkaterData skaterData) {
		log.info("Inserting data into Skater Table {}" + skaterData);
		return skateTrackerService.saveSkaterData(skaterData);

	}
	
	//Adds skater to an event
	@PostMapping("/addSkaterEvent")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SkaterEventDataResponse createSkaterEvent(@RequestParam Long skaterId, @RequestParam Long eventId) {
		log.info("Inserting data into Skater event Table {}" + skaterId, eventId);
		return skateTrackerService.addSkaterToEvent(skaterId, eventId);

	}

	//Retrieves all locations
	@GetMapping("/location")
	@ResponseStatus(code = HttpStatus.OK)
	public List<LocationDataResponseDto> getAllLocations() {
		log.info("Returning a list of locations");
		return skateTrackerService.getAllLocaitons();
	}
	
	//Retrieves all skaters
	@GetMapping("/skaters")
	@ResponseStatus(code = HttpStatus.OK)
	public List<SkaterEventDataResponse> getAllSkaters() {
		log.info("Returning a list of skaters");
		return skateTrackerService.getAllSkaters();
	}
		
	//Updates a skater
	@PutMapping("/updateSkater/{skaterId}")
	@ResponseStatus(code = HttpStatus.OK)
	public SkaterData updateSkater(@PathVariable Long skaterId, @RequestBody SkaterData skaterData) {
		skaterData.setSkaterId(skaterId);
		log.info("Updating a skater with ID =  {}", skaterId);
		
		return skateTrackerService.saveSkaterData(skaterData);
		
	}

	//Updates an event
	@PutMapping("/updateEvent/{eventId}")
	@ResponseStatus(code = HttpStatus.OK)
	public EventDataReponseDto updateEvent(@PathVariable Long eventId, @RequestBody EventData eventData) {
		eventData.setEventId(eventId);
		log.info("Updating an event with ID = {}", eventId);
		EventData event =skateTrackerService.saveEventData(eventData);
		return new EventDataReponseDto(event.getEventName(), event.getEventDate(), event.getDuration(), event.getLocation().getLocationName());
		
	}
	
	//Updates a location
	@PutMapping("/updateLocation/{locationId}")
	@ResponseStatus(code = HttpStatus.OK)
	public LocationDataResponseDto updateLocation(@PathVariable Long locationId, @RequestBody LocationData locationData) {
		locationData.setLocationId(locationId);
		log.info("Updating location with ID =  {}", locationId);
		LocationData location =skateTrackerService.saveLocationData(locationData);
		
		return new LocationDataResponseDto(location.getLocationId(), location.getLocationName(), location.getAddress(), location.getState(), location.getZip());
		
	}
	
	//Deletes a location and all child rows associated with it (event)
	@DeleteMapping ("/deleteLocation/{locationId}")
	public Map<String, String> deleteLocation(@PathVariable Long locationId) {
		log.info("Deleting location with ID =  {}", locationId);
		skateTrackerService.deleteLocation(locationId);
		
		return Map.of("message", "Location with ID=" + locationId + " was deleted sucessfully." );
	}
}
