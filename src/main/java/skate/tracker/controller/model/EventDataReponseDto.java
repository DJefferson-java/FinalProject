package skate.tracker.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Taking in a Event Entity and transforming the output 
 * to Only include name, date, duration, location name only.
 * @author Demitria Jefferson
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class EventDataReponseDto {
	private String eventName;
	private String eventDate;
	private String duration;
	private String locationName;

	public EventDataReponseDto(String name, String date, String duration, String locationName) {
		this.duration = duration;
		this.eventDate = date;
		this.eventName = name;
		this.locationName = locationName;

	}
}
