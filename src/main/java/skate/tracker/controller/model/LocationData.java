package skate.tracker.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import skate.tracker.entity.Event;
import skate.tracker.entity.Location;

@Data
@NoArgsConstructor
public class LocationData {

	private Long locationId;
	private String locationName;
	private String address;
	private String city;
	private String state;
	private String zip;

	private Set<EventData> events = new HashSet<>();

	public LocationData(Location location) {
		locationId = location.getLocationId();
		locationName = location.getLocationName();
		address = location.getAddress();
		city = location.getCity();
		state = location.getState();
		zip = location.getZip();

		for (Event event : location.getEvents()) {
			events.add(new EventData(event));
		}
	}
	
	public LocationData(String locationName, String address, String city, String state, String zip) {
		this.locationName = locationName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public Location toLocation() {
		Location location = new Location();
		location.setAddress(address);
		location.setCity(city);
		location.setLocationId(locationId);
		location.setState(state);
		location.setZip(zip);
		location.setLocationName(locationName);
		
		for (EventData event : events) {
			location.getEvents().add(event.toEvent());
		}
		
		return location;
	}
	
	

}
