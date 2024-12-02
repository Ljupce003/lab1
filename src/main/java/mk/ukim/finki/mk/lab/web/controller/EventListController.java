package mk.ukim.finki.mk.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EventListController {
    private final EventService eventService;

    public EventListController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String getEventSelectionPage(HttpSession session, Model model){

        model.addAttribute("list",eventService.listAll());

        Object results=session.getAttribute("results");
        if(results!=null){
            model.addAttribute("results",results);
            model.addAttribute("list_available",true);

        }
        model.addAttribute("sess_id",session.getId());
        model.addAttribute("sess_t",session.getCreationTime());

        return "listEvents";
    }

    @PostMapping("/")
    public String PostToEventSelectionPage(@RequestParam String text,
                                           @RequestParam Double rating,
                                           HttpSession session){

        List<Event> results=this.eventService.searchEventsAndByRating(text,rating);
        session.setAttribute("results",results);

        return "redirect:/";
    }




}

//TODO If instead of setting the attribute "results" to session we
// can add do : @SessionAttributes("results") , and this means as soon as we add
// this to the model as in line:30 then it will automatically be also added to
// the session and we won't have to manually add it like in line:46
