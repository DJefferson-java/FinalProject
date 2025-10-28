package skate.tracker.controller.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import skate.tracker.entity.Event;
import skate.tracker.entity.Location;

/**
 * Taking in a Location Entity and transforming the output 
 * to Only include name, address, city, state and a list of event names.
 * Or alternatively only returning the location without the events
 * @author Demitria Jefferson
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class LocationDataResponseDto {
	private String locationName;
	private String address;
	private String city;
	private String state;
	private List<String> eventNames;
	
	    public LocationDataResponseDto(String name, String city, String state, String address, List<String> eventNames) {
	        this.locationName = name;
	        this.city = city;
	        this.state = state;
	        this.address = address;
	        this.eventNames = eventNames;
	    }
	    
	    public LocationDataResponseDto(String name, String city, String state, String address) {
	        this.locationName = name;
	        this.city = city;
	        this.state = state;
	        this.address = address;
	    }

}
