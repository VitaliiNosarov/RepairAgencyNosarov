package ua.kharkiv.nosarev.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
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
        if (req.getParameter("locale") != null) {
            req.getSession().setAttribute("locale", req.getParameter("locale"));
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
