package skate.tracker.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import skate.tracker.entity.Event;
import skate.tracker.entity.Skater;


@Data
@NoArgsConstructor
public class SkaterData {

	private Long skaterId;
	private String skaterName;
	private int skaterAge;
	
	private Set<EventData> events = new HashSet<>();	
	
	public SkaterData(Skater skater){
		skaterId = skater.getSkaterId();
		skaterName = skater.getSkaterName();
		skaterAge = skater.getSkaterAge();
		
		for(Event event : skater.getEvents()) {
			events.add(new EventData(event));
		}
		
	}

	public Skater toSkater() {
		Skater skater = new Skater();
		skater.setSkaterAge(skaterAge);
		skater.setSkaterId(skaterId);
		skater.setSkaterName(skaterName);
		
		for(EventData event : events) {
		skater.getEvents().add(event.toEvent());
		}
		
		return skater;
	}
}
