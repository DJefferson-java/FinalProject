package skate.tracker.controller.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

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
