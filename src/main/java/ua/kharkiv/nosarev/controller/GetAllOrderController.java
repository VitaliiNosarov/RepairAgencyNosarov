package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.entitie.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class GetAllOrderController extends HttpServlet {

    private OrderDao orderDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> list = orderDao.getAllOrders();
        req.setAttribute("list", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("allOrders.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderDao = (OrderDao) config.getServletContext().getAttribute("orderDao");
    }
}
