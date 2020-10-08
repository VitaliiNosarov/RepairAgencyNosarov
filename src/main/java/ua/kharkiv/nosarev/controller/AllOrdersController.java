package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.services.api.OrderService;

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
public class AllOrdersController extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> list = orderService.getAllOrders();
        req.setAttribute("list", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("all_orders.jsp");
        dispatcher.forward(req, resp);
    }
}
