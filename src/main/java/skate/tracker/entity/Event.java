package skate.tracker.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Event {
	// Child
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventId;
	private String eventName;
	private String eventDate;
	private String duration;

	@ManyToOne
	@JoinColumn(name = "location_id", nullable = false) 
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Location location;
	
	@ManyToMany(mappedBy = "events")
	private Set<Skater> skaters = new HashSet<>();

}
