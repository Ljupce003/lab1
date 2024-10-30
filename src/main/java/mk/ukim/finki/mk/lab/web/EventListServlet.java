package mk.ukim.finki.mk.lab.web;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@WebServlet(name = "EventListServlet",urlPatterns = {""})
public class EventListServlet extends HttpServlet {
    private final EventService eventService;
    private final SpringTemplateEngine springTemplateEngine;


    public EventListServlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange= JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);
        WebContext context=new WebContext(webExchange);

        context.setVariable("list",eventService.listAll());

        Object results=req.getSession().getAttribute("results");
        if(results!=null){
            context.setVariable("results",results);
            context.setVariable("list_available",true);
        }

        long session_cr_t=req.getSession().getCreationTime();
        String session_id=req.getSession().getId();

        context.setVariable("sess_id",session_id);
        context.setVariable("sess_t",session_cr_t);

        springTemplateEngine.process("listEvents.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text=req.getParameter("text");
        Double rating= Double.parseDouble(req.getParameter("rating").strip());

        List< Event> results=this.eventService.searchEventsAndByRating(text,rating);

        req.getSession().setAttribute("results",results);

        resp.sendRedirect("");
    }
}
