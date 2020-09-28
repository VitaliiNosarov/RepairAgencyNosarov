package ua.kharkiv.nosarev.controller;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.User;
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

    static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final long serialVersionUID = 1L;
    UserService useruserService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        useruserService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = useruserService.getUserByEmailPass(email, password);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        req.setAttribute("user", user);
        req.getRequestDispatcher("user_account.jsp").forward(req, resp);
    }
}
