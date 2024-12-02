package mk.ukim.finki.mk.lab.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.mk.lab.model.AuthUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "LogFilter",urlPatterns = {"/","","/log","/eventBooking"},dispatcherTypes = DispatcherType.REQUEST)
public class loginFIlter implements Filter {

    private List<String> ignore_paths =null;
    private AuthUser authUser = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ignore_paths=new ArrayList<>();
        ignore_paths.add("");
        ignore_paths.add("/");
        ignore_paths.add("/log");
        ignore_paths.add("/events");
        ignore_paths.add("/event_details");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;



        String path=req.getServletPath();


        String reqURL=req.getRequestURI();
        System.out.println(reqURL);
        System.out.println(req.getMethod());
        String title=null;
        String numTickets=null;

        if(req.getMethod().equalsIgnoreCase("POST") && reqURL.equalsIgnoreCase("/eventBooking")){
            title =req.getParameter("radio_b");
            numTickets=req.getParameter("numTickets");
            if( title==null || title.isEmpty() || numTickets==null || numTickets.isEmpty()){
                if(req.getSession().getAttribute("title")==null || req.getSession().getAttribute("ticket_num")==null){
                    resp.sendRedirect("/");
                    return;
                }
            }

            String user_name=req.getParameter("user_the_name");
            if(title!=null)req.getSession().setAttribute("title",title);
            if(numTickets!=null)req.getSession().setAttribute("ticket_num",numTickets);

            if(user_name!=null){
                this.authUser=new AuthUser(user_name);
            }
        }

        if(this.authUser==null && !ignore_paths.contains(reqURL) ){

            resp.sendRedirect("/log");
        }
        else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
