package skate.tracker.controller.model;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Taking in a Skater Entity and transforming the output 
 * to Only include name, age, and list of event names.
 * @author Demitria Jefferson
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class SkaterEventDataResponse {
	private String skaterName;
	private int skaterAge;	
	private List<String> events = new LinkedList<>();
	
	public SkaterEventDataResponse(String name, int age, List<String> eventNames) {
		this.skaterName = name;
		this.skaterAge = age;
		this.events = eventNames;
		
	}
}
