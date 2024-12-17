package mk.ukim.finki.mk.lab.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.service.EventService;
import mk.ukim.finki.mk.lab.service.LocationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
    public String getEventsPage(Model model,
                                HttpServletRequest servletRequest,
                                @RequestParam(required = false) String Name,
                                @RequestParam(required = false) String description,
                                @RequestParam(required = false) String locationName,
                                @RequestParam(required = false) Double popularityScore,
                                @RequestParam(defaultValue = "0")Boolean showFiltered,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize
                                ){

        model.addAttribute("eventi",this.eventService.listAll());
        model.addAttribute("locations",this.locationService.findAll());


        if(showFiltered){
            Page<Event> page=this.eventService.findPage(Name,description,popularityScore,locationName,pageNum,pageSize);
            model.addAttribute("page",page);
        }

        List<String> attr_list=new ArrayList<>();  //TODO
        attr_list.add("name");
        attr_list.add("description");
        attr_list.add("popularityScore");
        attr_list.add("id");
        model.addAttribute("attr_list",attr_list);
        model.addAttribute("locations",this.locationService.findAll());




//        Boolean has_atr_list= (Boolean) servletRequest.getSession().getAttribute("ev_list_available");
//        List<Event> selected_events= (List<Event>) servletRequest.getSession().getAttribute("ev_list");
//        model.addAttribute("ev_list_available",has_atr_list);
//        model.addAttribute("ev_list",selected_events);

        return "ListingEvents";
    }



    @PostMapping("/events")
    public String PostToFilter(@RequestParam(required = false) String radio_attr,
                               @RequestParam(required = false) String text_to_search,
                               HttpServletRequest servletRequest) {

        List<Event> selected_events=this.eventService.filterBy(radio_attr,text_to_search); //TODO

        if(!selected_events.isEmpty()){
            servletRequest.getSession().setAttribute("ev_list_available",true);
        }
        else servletRequest.getSession().setAttribute("ev_list_available",false);

        servletRequest.getSession().setAttribute("ev_list",selected_events);


        return "redirect:/events";
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
        Optional<Event> event=this.eventService.updateEvent(eventId,event_name,event_desc,popularity,id);

        if(event.isEmpty()){
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
