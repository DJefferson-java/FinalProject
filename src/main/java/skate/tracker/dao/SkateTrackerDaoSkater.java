package skate.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import skate.tracker.entity.Skater;

public interface SkateTrackerDaoSkater extends JpaRepository<Skater, Long> {

}
