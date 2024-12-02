package mk.ukim.finki.mk.lab.service.Implementations;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.repository.EventRepository;
import mk.ukim.finki.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImplemented implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImplemented(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEvents(text);
    }

    @Override
    public void AddEvent(String event_name, String event_desc, String popularity, String loc_id) {
        eventRepository.addEvent(event_name,event_desc,popularity,loc_id);
    }

    @Override
    public List<Event> searchEventsAndByRating(String text, Double rating) {
        return eventRepository.searchEventsAndByRating(text,rating);
    }

    @Override
    public void DeleteEvent(Long id) {
        this.eventRepository.deleteEvent(id);
    }

    @Override
    public Optional<Event> searchEventByID(Long id) {
        return this.eventRepository.searchEventByID(id);
    }

    @Override
    public Event updateEvent(Long eventId, String event_name, String event_desc, String popularity, Long id) {
        return this.eventRepository.updateEvent(eventId,event_name,event_desc,popularity,id);
    }

    @Override
    public Event addBooking(String event_name, EventBooking booking) {
        return this.eventRepository.addBooking(event_name,booking);
    }
}
