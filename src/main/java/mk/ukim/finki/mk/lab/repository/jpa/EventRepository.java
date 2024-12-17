package mk.ukim.finki.mk.lab.repository.jpa;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository  extends JpaSpecificationRepository<Event,Long> {

    void deleteById(Long id);

    void findAllById(Long id);

    List<Event> findAllByLocation_Id(Long locationId);

    Event findAllByName(String name);

    List<Event> searchByNameContainingIgnoreCase(String name);

    List<Event> searchByDescriptionContainingIgnoreCase(String desc);

    List<Event> searchByPopularityScoreAfter(Double val);

    List<Event> searchByNameContainingOrDescriptionContaining(String text,String descriptionText);

    List<Event> searchByNameContainingOrDescriptionContainingAndPopularityScoreAfter(String text,String descriptionText,Double rating);
}
