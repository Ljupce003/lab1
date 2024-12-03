package mk.ukim.finki.mk.lab.service.Implementations;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.model.Location;
import mk.ukim.finki.mk.lab.repository.jpa.EventRepository;
import mk.ukim.finki.mk.lab.repository.jpa.LocationRepository;
import mk.ukim.finki.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImplemented implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImplemented(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchByNameContainingOrDescriptionContaining(text,text);
    }

    @Override
    public Optional<Event> AddEvent(String event_name, String event_desc, String popularity, String loc_id) {
        Optional<Location> optionalLocation=locationRepository.findById(Long.valueOf(loc_id));
        if(optionalLocation.isPresent()){
            Event event =new Event(event_name,event_desc,Double.parseDouble(popularity),optionalLocation.get());
            return Optional.of(this.eventRepository.save(event));
        }

       return Optional.empty();
    }

    @Override
    public List<Event> searchEventsAndByRating(String text, Double rating) {
        return eventRepository.searchByNameContainingOrDescriptionContainingAndPopularityScoreAfter(text,text,rating);
    }

    @Override
    public void DeleteEvent(Long id) {
        this.eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> searchEventByID(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public Optional<Event> updateEvent(Long eventId, String event_name, String event_desc, String popularity, Long id) {

        Optional<Event> optionalEvent=this.eventRepository.findById(eventId);
        Optional<Location> optionalLocation=this.locationRepository.findById(id);

        if(optionalEvent.isPresent() && optionalLocation.isPresent()){
            Event event=optionalEvent.get();
            Location location=optionalLocation.get();

            event.setName(event_name);
            event.setDescription(event_desc);
            event.setPopularityScore(Double.parseDouble(popularity));
            event.setLocation(location);

            return Optional.of(this.eventRepository.save(event));
        }

        return Optional.empty();
    }

    @Override
    public Optional<Event> addBooking(String event_name, EventBooking booking) {
        Optional<Event> optionalEvent = Optional.ofNullable(eventRepository.findAllByName(event_name));
        if(optionalEvent.isPresent()){
            Event event=optionalEvent.get();
            event.getBookings().add(booking);
            return Optional.of(this.eventRepository.save(event));
        }
        return Optional.empty();
    }
}
