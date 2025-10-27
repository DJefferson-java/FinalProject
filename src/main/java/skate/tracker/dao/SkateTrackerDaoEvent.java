package skate.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import skate.tracker.entity.Event;

public interface SkateTrackerDaoEvent extends JpaRepository<Event, Long> {

}
