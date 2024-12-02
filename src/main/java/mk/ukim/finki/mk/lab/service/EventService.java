package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;

import java.util.List;
import java.util.Optional;


public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);

    void AddEvent(String event_name, String event_desc, String popularity, String loc_id);

    List<Event> searchEventsAndByRating(String text,Double rating);

    void DeleteEvent(Long id);

    Optional<Event> searchEventByID(Long id);

    Event updateEvent(Long eventId,String event_name, String event_desc,String popularity,Long id);

    public Event addBooking(String event_name, EventBooking booking);
}
