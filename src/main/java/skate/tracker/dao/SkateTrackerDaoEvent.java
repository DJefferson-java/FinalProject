package skate.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import skate.tracker.entity.Event;

//Repository that searches the DB for data needed for the api's. In this case the Event Table.
public interface SkateTrackerDaoEvent extends JpaRepository<Event, Long> {

}
