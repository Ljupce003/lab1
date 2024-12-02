package mk.ukim.finki.mk.lab.web.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.service.EventBookingService;
import mk.ukim.finki.mk.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventBookingController {
    private final EventBookingService eventBookingService;
    private final EventService eventService;
    public EventBookingController(EventBookingService eventBookingService, EventService eventService) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
    }

    @GetMapping("/eventBooking")
    public String getConfirmationPage(HttpServletRequest servletRequest, HttpSession session, Model model){

        String u_name= (String) session.getAttribute("user_name");
        String title= (String) session.getAttribute("title");
        String numTickets= (String) session.getAttribute("ticket_num");

        EventBooking newBooking=this.eventBookingService.placeBooking(title,u_name,"Orce Nikolov",Integer.parseInt(numTickets));
        if(newBooking!=null){
            this.eventService.addBooking(title,newBooking);
        }

        servletRequest.getHeaderNames().asIterator().forEachRemaining( h -> System.out.println(h+" : "+servletRequest.getHeader(h)) );

        model.addAttribute("user_name",u_name);
        model.addAttribute("title",title);
        model.addAttribute("ticket_num",numTickets);
        model.addAttribute("ip_address",servletRequest.getRemoteAddr());

        return "bookingConfirmation";
    }

    @PostMapping("/eventBooking")
    public String postToConfirmationPage(HttpServletRequest servletRequest, HttpSession session){

        String user_name=servletRequest.getParameter("user_the_name");
        if(user_name!=null)session.setAttribute("user_name",user_name);

        //RequestDispatcher dispatcher=servletRequest.getRequestDispatcher("")


        //servletRequest.getHeaderNames().asIterator().forEachRemaining( h -> System.out.println(h+" : "+servletRequest.getHeader(h)) );



        return "redirect:/eventBooking";
    }


    // TODO na POST method se pustat informaciite ne na GET pa zatoa napravi POSTmapping
}
