package ua.kharkiv.nosarev.controller.account;

import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.exception.RegistrationException;
import ua.kharkiv.nosarev.service.api.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(req.getParameter("name"));
        user.setSurName(req.getParameter("surname"));
        user.setPhone(req.getParameter("phone"));
        user.setLocale(UserLocale.valueOf(req.getParameter("locale")));
        HttpSession session = req.getSession();
        try {
            userService.saveUser(user);
            session.setAttribute("infoMessageSuccess", InfoMessage.REGISTRATION_SUCCESS);
            resp.sendRedirect("login");
        } catch (RegistrationException exception) {
            session.setAttribute("infoMessage", InfoMessage.WRONG_REGISTRATION);
            resp.sendRedirect("registration");
        }
    }
}
