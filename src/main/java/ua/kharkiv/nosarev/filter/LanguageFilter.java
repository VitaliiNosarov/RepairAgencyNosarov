package ua.kharkiv.nosarev.filter;

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

    private static final String LANGUAGE = "language";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        setEncoding(request, response);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (req.getParameter(LANGUAGE) != null) {
            req.getSession().setAttribute(LANGUAGE, req.getParameter(LANGUAGE));
            resp.sendRedirect(req.getHeader("referer"));
            return;
        }
        if (session.getAttribute(LANGUAGE) == null) {
            session.setAttribute(LANGUAGE, UserLocale.EN.toString());
        }
        chain.doFilter(request, response);
    }

    private void setEncoding(ServletRequest request, ServletResponse response) throws UnsupportedEncodingException {
        if (null == request.getCharacterEncoding())
            request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

}
