package mk.ukim.finki.mk.lab.service.Implementations;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.repository.EventRepository;
import mk.ukim.finki.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Event> searchEventsAndByRating(String text, Double rating) {
        return eventRepository.searchEventsAndByRating(text,rating);
    }
}
