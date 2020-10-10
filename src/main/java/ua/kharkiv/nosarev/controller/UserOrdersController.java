package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.PaginationObject;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.services.PaginationService;
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

@WebServlet("/userOrders")
public class UserOrdersController extends HttpServlet {

    private OrderService orderService;
    private PaginationService paginationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Order> orderList = orderService.getAllCustomerOrders(user.getId());
        req.setAttribute("orders", orderList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user_orders.jsp");
        dispatcher.forward(req, resp);
    }
}
