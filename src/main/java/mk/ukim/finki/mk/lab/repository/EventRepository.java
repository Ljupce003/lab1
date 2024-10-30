package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    private List<Event> events;

    public EventRepository() {
        this.events =new ArrayList<>(10);

        events.add(new Event("Svadba","Heppy",1500));
        events.add(new Event("Krsevka","Heppy",1300));
        events.add(new Event("Rodenden","Heppi",900));
    }

    public List<Event> findAll(){
        return this.events;
    }

    public List<Event> searchEvents(String text){
        return this.events.stream()
                .filter(e -> e.getName().contains(text) || e.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public List<Event> searchEventsAndByRating(String text,Double rating){
        return this.events.stream()
                .filter(e -> (e.getName().contains(text) || e.getDescription().contains(text)) && e.getPopularityScore()>=rating)
                .collect(Collectors.toList());
    }



}
