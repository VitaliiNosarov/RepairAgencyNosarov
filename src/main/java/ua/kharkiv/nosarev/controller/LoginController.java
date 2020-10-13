package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.MessageType;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.exception.AuthenticationException;
import ua.kharkiv.nosarev.services.api.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login")
public class LoginController extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            resp.sendRedirect("create_order");
            return;
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        try {
            User user = userService.getUserByEmailPass(email, password);
            session.setAttribute("user", user);
            session.setAttribute("language", user.getLocale().toString());
        } catch (AuthenticationException exception) {
            session.setAttribute("infoMessage", MessageType.WRONG_AUTHENTICATION.getMessage());
        }
        resp.sendRedirect("login");
    }
}
