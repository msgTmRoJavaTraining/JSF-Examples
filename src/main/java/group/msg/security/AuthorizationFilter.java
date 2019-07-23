package group.msg.security;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            Object loggedInOrNot = req.getSession().getAttribute("loggedIn");

            String reqURI = req.getRequestURI();
            if (reqURI.contains("/day10/my_login.xhtml")
                    || reqURI.contains("/public/")
                    || reqURI.contains("javax.faces.resource")
                    || (loggedInOrNot!=null && loggedInOrNot.equals(true))) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(req.getContextPath() + "/day10/my_login.xhtml");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
