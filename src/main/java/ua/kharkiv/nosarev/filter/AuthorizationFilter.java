package ua.kharkiv.nosarev.filter;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);
    Map<UserRole, Set<String>> uriMap;

    @Override
    public void init(FilterConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        uriMap = (Map<UserRole, Set<String>>) context.getAttribute("uriMap");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String uri = req.getServletPath();
        if (uri.contains("login") || uri.contains("registration") || uri.contains("index.jsp") || uri.endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        if (uriMap.get(currentUser.getRole()).contains(uri)) {
            chain.doFilter(request, response);
            return;
        }
        LOGGER.info("Unauthorized access request");
        resp.sendRedirect("not_rights.jsp");
    }

    @Override
    public void destroy() {
    }
}
