package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.services.api.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


@WebServlet("/login")
public class LoginController extends HttpServlet {

    UserService useruserService;
    OrderDao orderDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        useruserService = (UserService) config.getServletContext().getAttribute("userService");
        orderDao = (OrderDao) config.getServletContext().getAttribute("orderDao");
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
        Order order = new Order();
        order.setPrice(new BigDecimal(255));
        order.setComment("comment comment");
        order.setMasterId(1);
        order.setStatus(OrderStatus.READY_TO_ISSUE);
        order.setCustomerId(1);
        order.addService("Replacement of thermal paste");
        order.addService("Video card repair");
        orderDao.insertOrder(order);
        req.setAttribute("user", user);
        req.getRequestDispatcher("user_account.jsp").forward(req, resp);
    }
}
