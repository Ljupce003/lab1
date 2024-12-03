package mk.ukim.finki.mk.lab.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.mk.lab.Bootstrap.Dataholder;
import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "LogFilter",urlPatterns = {"/","","/log","/eventBooking"},dispatcherTypes = DispatcherType.REQUEST)
public class loginFIlter implements Filter {

    private List<String> ignore_paths =null;
    //private AuthUser authUser = null;
    private final UserService userService;

    public loginFIlter(UserService userService) {
        this.userService = userService;
    }


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

        User user = (User) req.getSession().getAttribute("authUser");


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
            if(numTickets!=null){
                Integer ticket_num=null;
                try {
                    ticket_num=Integer.parseInt(numTickets);
                }catch (NumberFormatException e){
                    e.printStackTrace();
                    resp.sendRedirect("/?intError="+e.getMessage());
                }

                req.getSession().setAttribute("ticket_num",ticket_num);
            }

            if(user_name!=null){
                user =new User(user_name);
                //Dataholder.users.add(user);
                this.userService.addUser(user_name);
                req.getSession().setAttribute("authUser", user);
                //this.authUser=new AuthUser(user_name);
            }
        }

        if(user ==null && !ignore_paths.contains(reqURL) ){

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
