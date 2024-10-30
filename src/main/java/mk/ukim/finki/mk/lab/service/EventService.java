package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Event;

import java.util.List;


public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);

    List<Event> searchEventsAndByRating(String text,Double rating);
}
