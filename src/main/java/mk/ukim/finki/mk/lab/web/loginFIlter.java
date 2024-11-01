package mk.ukim.finki.mk.lab.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.io.IOException;
import java.net.http.HttpRequest;

@WebFilter(filterName = "LogFilter",urlPatterns = "/",dispatcherTypes = DispatcherType.REQUEST)
public class loginFIlter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;


        String path=req.getServletPath();

        System.out.println(path);
        System.out.println(req.getRequestURI());
        System.out.println(req.getMethod());
        if(path.isEmpty() && !req.getMethod().equalsIgnoreCase("GET") && !req.getRequestURI().equalsIgnoreCase("/")){
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
