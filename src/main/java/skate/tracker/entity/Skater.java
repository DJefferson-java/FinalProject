package skate.tracker.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Creating the Skater entity to add,update,retrieve or delete.
 * Uses JPA
 * @author Demitria Jefferson
 * @version 1.0
 */
@Data
@Entity
public class Skater {
	// Child
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long skaterId;
	private String skaterName;
	private int skaterAge;
	
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "skater_events", joinColumns = @JoinColumn(name = "skater_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
	private Set<Event> events = new HashSet<>();

}
