package mk.ukim.finki.mk.lab.web.controller;


import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.service.EventService;
import mk.ukim.finki.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class EventController {
    private final LocationService locationService;
    private final EventService eventService;

    public EventController(LocationService locationService, EventService eventService) {
        this.locationService = locationService;
        this.eventService = eventService;
    }


    @GetMapping("/events")
    public String getEventsPage(@RequestParam(required = false) String error, Model model){

        model.addAttribute("eventi",this.eventService.listAll());
        model.addAttribute("locations",this.locationService.findAll());
        return "ListingEvents";
    }


    @PostMapping("/events/add")
    public String saveEvent(@RequestParam String event_name,
                            @RequestParam String event_desc,
                            @RequestParam String popularity,
                            @RequestParam String id){
        this.eventService.AddEvent(event_name,event_desc,popularity,id);

        return "redirect:/events";
    }

    @PostMapping("/events/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId,
                            @RequestParam String event_name,
                            @RequestParam String event_desc,
                            @RequestParam String popularity,
                            @RequestParam Long id){

        //update existing event
        Event event=this.eventService.updateEvent(eventId,event_name,event_desc,popularity,id);

        if(event==null){
            System.out.println("Event not updated");
        }

        return "redirect:/events";
    }

    @GetMapping("/events/edit-form")
    public String getAddEventPage(Model model){
        model.addAttribute("locations",this.locationService.findAll());
        return "add_event";

    }

    @GetMapping("/events/edit-form/{id}")
    public String getEditEventForm(@PathVariable Long id,Model model){

        if(id==null){
            System.out.println("Error edit on event id");
            return "redirect:/events";

        }
        else {
            //Here is the edit event
            Optional<Event> optional=this.eventService.searchEventByID(id);
            if(optional.isPresent()){
                Event event =optional.get();
                model.addAttribute("event",event);
            }
            else {
                System.out.println("Error event with id:"+id+" not found");
                return "redirect:/events";
            }
        }
        model.addAttribute("locations",this.locationService.findAll());

        return "add_event";
    }



    @PostMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {

        this.eventService.DeleteEvent(id);

        return "redirect:/events";
    }

    @GetMapping("/events/details/{id}")
    public String getDetailsPage(@PathVariable Long id,Model model){

        Optional<Event> optional=this.eventService.searchEventByID(id);
        if(optional.isPresent()){
            Event event =optional.get();
            model.addAttribute("event",event);
            if(!event.getBookings().isEmpty()){
                model.addAttribute("hasBooking",true);
            }
        }

        return "event_details";
    }

}
