package skate.tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skate.tracker.entity.Skater;

//Repository that searches the DB for data needed for the api's. In this case the Skater Table.
@Repository
public interface SkateTrackerDaoSkater extends JpaRepository<Skater, Long> {

}
