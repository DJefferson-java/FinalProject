package skate.tracker.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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
