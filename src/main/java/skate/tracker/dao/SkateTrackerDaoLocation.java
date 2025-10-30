package skate.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skate.tracker.entity.Event;
import skate.tracker.entity.Location;

//Repository that searches the DB for data needed for the api's. In this case the Location Table.
@Repository
public interface SkateTrackerDaoLocation  extends JpaRepository<Location, Long> {

}
