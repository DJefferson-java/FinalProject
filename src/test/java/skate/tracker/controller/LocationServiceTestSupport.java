package skate.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import skate.tracker.controller.model.LocationData;

public class LocationServiceTestSupport {
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	 @Autowired
	 private SkateTrackerController skateTrackerContoller;
	 
	 
	private LocationData buildInserLocaiton() {
		// TODO Auto-generated method stub
		return null;
	}

}
