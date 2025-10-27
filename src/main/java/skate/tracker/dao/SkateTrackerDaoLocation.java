package skate.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import skate.tracker.entity.Event;
import skate.tracker.entity.Location;


public interface SkateTrackerDaoLocation  extends JpaRepository<Location, Long> {

}
