package mk.ukim.finki.mk.lab.repository.inmemory;

import mk.ukim.finki.mk.lab.Bootstrap.Dataholder;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class inMemoryEventRepository {
    private final inMemoryLocationRepository inMemoryLocationRepository;

    public inMemoryEventRepository(inMemoryLocationRepository inMemoryLocationRepository) {
        this.inMemoryLocationRepository = inMemoryLocationRepository;
    }

    public List<Event> findAll(){
        return Dataholder.events;
    }

    public Optional<Event> addEvent(String event_name, String event_desc, String popularity, String loc_id){
        Long id =Long.parseLong(loc_id);
        Optional<Location> location =this.inMemoryLocationRepository.findID(id);
        if(location.isPresent()){
            double popularity_score=Double.parseDouble(popularity);
            Event event=new Event(event_name,event_desc,popularity_score,location.get());
            Dataholder.events.add(event);
            return Optional.of(event);
        }
        return Optional.empty();
    }

    public List<Event> searchEvents(String text){
        return Dataholder.events.stream()
                .filter(e -> e.getName().contains(text) || e.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public Optional<Event> searchEventByID(Long id){
        return Dataholder.events.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public List<Event> searchEventsAndByRating(String text,Double rating){
        return Dataholder.events.stream()
                .filter(e -> (e.getName().contains(text) || e.getDescription().contains(text)) && e.getPopularityScore()>=rating)
                .collect(Collectors.toList());
    }

    public boolean deleteEvent(Long id){
        if(Dataholder.events.removeIf(e-> e.getId().equals(id))){
            System.out.println("Event with id: "+id+" deleted successfully");
            return true;
        }
        else {
            System.out.println("Event with id: "+id+" NOT deleted successfully --->> UNSUCCESSFUL");
            return false;
        }
    }

    public Optional<Event> addBooking(String event_name, EventBooking booking){
        Optional<Event> optional=Dataholder.events.stream().filter( e -> e.getName().equals(event_name)).findFirst();
        if(optional.isPresent()){
            Event event =optional.get();
            event.getBookings().add(booking);
            return Optional.of(event);
        }
        return Optional.empty();
    }

    public Optional<Event> updateEvent(Long eventId,String event_name, String event_desc,String popularity,Long id){
        Optional<Event> optional = searchEventByID(eventId);
        Optional<Location> newLocOptional=this.inMemoryLocationRepository.findID(id);
        if(optional.isPresent() && newLocOptional.isPresent()){
            Event event = optional.get();
            event.setName(event_name);
            event.setDescription(event_desc);
            event.setPopularityScore(Double.parseDouble(popularity));
            event.setLocation(newLocOptional.get());
            return Optional.of(event);
        }

        return Optional.empty();
    }

}
