package skate.tracker.controller;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import skate.tracker.SkateTrackerApplication;
import skate.tracker.controller.model.LocationData;
import skate.tracker.entity.Location;

@SpringBootTest(webEnvironment = WebEnvironment.NONE, classes = SkateTrackerApplication.class)
@ActiveProfiles("test")
@Sql(scripts = ("classpath:schema.sql"))
@SqlConfig(encoding = "utf-8")
class SkateTrackerControllerTest  extends LocationServiceTestSupport{

	@Test
	void testCreateLocation() {	
		// Given: A location request
		LocationData request = buildInserLocaiton();
		
		Location excepted = buildInserLocaiton();
		//When: the location is added to the location table
		
		//And: There is one row in the location table
		fail("Not yet implemented");
	}

	

}
