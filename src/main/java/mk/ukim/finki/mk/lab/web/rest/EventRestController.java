package mk.ukim.finki.mk.lab.web.rest;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class EventRestController {
    private final EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getEvents(){
        List<Event> events=this.eventService.listAll();
        return ResponseEntity.of(Optional.ofNullable(events));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id){
        return this.eventService.searchEventByID(id)
                .map(e -> ResponseEntity.ok().body(e))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Event> save(@RequestParam String event_name,
                                      @RequestParam String event_desc,
                                      @RequestParam String popularity,
                                      @RequestParam String loc_id){
        return this.eventService.AddEvent(event_name,event_desc,popularity,loc_id)
                .map(event -> ResponseEntity.ok().body(event))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public void removeEvent(@PathVariable Long id){
        this.eventService.DeleteEvent(id);
    }
}
