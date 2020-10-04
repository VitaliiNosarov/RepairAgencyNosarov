package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.services.api.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OrderUpdateController extends HttpServlet {

    OrderDao orderDao;
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderDao = (OrderDao) config.getServletContext().getAttribute("orderDao");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("order") != null && req.getParameter("customer") != null) {
            req.getRequestDispatcher("order.jsp").forward(req, resp);
            return;
        }
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        if (orderId != 0) {
            Order order = orderDao.getOrderById(orderId);
            req.setAttribute("order", order);
            String[] customerName = userService.getUserFullName(order.getCustomerId());
            String[] masterName = userService.getUserFullName(order.getMasterId());
            req.setAttribute("customer", customerName[0] + " " + customerName[1]);
            req.setAttribute("master", masterName[0] + " " + masterName[1]);
            req.getRequestDispatcher("update_order.jsp").forward(req, resp); //TODO
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
