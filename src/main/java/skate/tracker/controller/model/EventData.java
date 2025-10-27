package skate.tracker.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import skate.tracker.entity.Event;
import skate.tracker.entity.Location;
import skate.tracker.entity.Skater;

@Data
@NoArgsConstructor
public class EventData {

	private Long eventId;
	private String eventName;
	private String eventDate;
	private String duration;
	private Location location;
	private Set<SkaterData> skaters = new HashSet<>();

	public EventData(Event event) {
		eventId = event.getEventId();
		eventName = event.getEventName();
		eventDate = event.getEventDate();
		duration = event.getDuration();
		location = event.getLocation();

		for (Skater skater : event.getSkaters()) {
			skaters.add(new SkaterData(skater));
		}

	}

	public Event toEvent() {
		Event event = new Event();
		event.setDuration(duration);
		event.setEventDate(eventDate);
		event.setEventId(eventId);
		event.setEventName(eventName);
		event.setLocation(location);
		
		for (SkaterData skater : skaters) {
			event.getSkaters().add(skater.toSkater());
		}
		return event;
	}
	
	
}
