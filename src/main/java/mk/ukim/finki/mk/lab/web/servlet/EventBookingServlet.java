package mk.ukim.finki.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

//@WebServlet
//@WebServlet(name = "EventBookingServlet",urlPatterns = {"/eventBooking"},value ="/servlet")
public class EventBookingServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public EventBookingServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Get /eventbooking ID: "+req.getSession().getId());

        IWebExchange webExchange= JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context=new WebContext(webExchange);

        String u_name= (String) req.getSession().getAttribute("user_name");
        String title= (String) req.getSession().getAttribute("title");
        String numTickets= (String) req.getSession().getAttribute("ticket_num");

        context.setVariable("user_name",u_name);
        context.setVariable("title",title);
        context.setVariable("ticket_num",numTickets);
        context.setVariable("ip_address",req.getRemoteAddr());



        springTemplateEngine.process("bookingConfirmation.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String user_name=req.getParameter("user_the_name");
        req.getSession().setAttribute("user_name",user_name);


        //TODO if we need to store new Bookings we do it here

        resp.sendRedirect("/eventBooking");
    }
}
