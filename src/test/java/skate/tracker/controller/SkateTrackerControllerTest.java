package skate.tracker.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import skate.tracker.SkateTrackerApplication;
import skate.tracker.controller.model.EventData;
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
		LocationData request = buildInsertLocation1();
				
		LocationData expected = buildInsertLocation1();
		
		//When: the location is added to the location table
		LocationData actual = createLocation(request);
		
		//Then: the location returned is what is expected
		assertThat(actual).isEqualTo(expected);	
		
		//And: There is one row in the location table
		assertThat(rowsInLocationTable()).isOne();	
	}	

}
