package skate.tracker.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import skate.tracker.controller.model.EventData;
import skate.tracker.controller.model.LocationData;
import skate.tracker.dao.SkateTrackerDaoLocation;
import skate.tracker.entity.Event;
import skate.tracker.entity.Location;

public class LocationServiceTestSupport {

	private LocationData insertAddress1;
	private EventData insertEvent;
//	private LocationData insertAddress2 = new LocationData(2L, "RiverGate Skate", "5533 Another Road", "Nashville",
//			"TN", "37011");

	private static final String LOCAION_TABLE = "location";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SkateTrackerController skateTrackerContoller;
	
	@Autowired
	SkateTrackerDaoLocation skateTrackerDaoLocation;


	protected LocationData buildInsertLocation1() {
		insertAddress1 = new LocationData(1L,"Skate Center USA", "5555 New Center Road", "Nashville",
				"TN", "37011");
		return insertAddress1;
	}
	
	protected EventData buildInsertEvent1() {
		insertEvent = new EventData(1L, "Skate Races", "10-31-2022", "30", insertAddress1.toLocation());
		return insertEvent;
	}

	protected int rowsInLocationTable() {
		
		return JdbcTestUtils.countRowsInTable(jdbcTemplate, LOCAION_TABLE);
	}

	protected LocationData createLocation(LocationData request) {
		Location location = request.toLocation();
		LocationData clone = new LocationData(location);
		
		clone.setLocationId(null);
		return skateTrackerContoller.createLocation(clone);
	}

}
