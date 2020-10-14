package ua.kharkiv.nosarev.filter;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebFilter("/*")
public class LanguageFilter implements Filter {

    static final Logger LOGGER = Logger.getLogger(LanguageFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        setEncoding(request, response);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (req.getParameter("language") != null) {
            req.getSession().setAttribute("language", req.getParameter("language"));
            resp.sendRedirect(req.getHeader("referer"));
            return;
        }
        if (session.getAttribute("language") == null) {
            session.setAttribute("language", UserLocale.EN.toString());
        }
        chain.doFilter(request, response);
    }

    private void setEncoding(ServletRequest request, ServletResponse response) throws UnsupportedEncodingException {
        if (null == request.getCharacterEncoding())
            request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
    }


    public void destroy() {
    }

    public void init(FilterConfig arg0) throws ServletException {
    }
}
