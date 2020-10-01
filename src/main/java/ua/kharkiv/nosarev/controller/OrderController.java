package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/order")
public class OrderController extends HttpServlet {

    private OrderDao orderDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderDao = (OrderDao) config.getServletContext().getAttribute("orderDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        Order order = orderDao.getOrderById(orderId);
        req.setAttribute("order", order);
        req.getRequestDispatcher("order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        order.setCustomerId(user.getId());
        order.setComment(req.getParameter("comment"));
        String[] services = req.getParameterValues("service");
        for (String service : services) {
            order.addService(new Service(Integer.valueOf(service), null));
        }
        orderDao.insertOrder(order);
        order = orderDao.getOrderById(order.getId());
        session.setAttribute("order", order);
    }
}
