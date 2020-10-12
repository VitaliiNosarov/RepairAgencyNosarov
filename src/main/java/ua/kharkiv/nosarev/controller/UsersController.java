package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.services.api.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersController extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long currentPage = Long.parseLong(req.getParameter("currentPage"));
        long recordsPerPage = Long.parseLong(req.getParameter("recordsPerPage"));
        long numberOfRows = userService.getAmountOfUsers();
        long nOfPages = numberOfRows / recordsPerPage;
        if (numberOfRows % recordsPerPage > 0) {
            nOfPages++;
        }
        List<User> list = userService.findUsers(currentPage, recordsPerPage);
        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.setAttribute("list", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("all_users.jsp");
        dispatcher.forward(req, resp);
    }
}
