package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.services.api.OrderService;
import ua.kharkiv.nosarev.services.api.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/updateOrder")
public class UpdateOrderController extends HttpServlet {

    OrderService orderService;
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("order") != null) {
            req.getRequestDispatcher("OLDorder.jsp").forward(req, resp);
        } else {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            if (orderId != 0) {
                Order order = orderService.getOrderById(orderId);
                List<User> listOfMasters = userService.getAllUsersByRole(UserRole.MASTER);
                req.setAttribute("order", order);
                req.setAttribute("masters", listOfMasters);
                req.getRequestDispatcher("update_order.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        if (orderId != 0) {
            Order order = orderService.getOrderById(orderId);
            int masterId = Integer.parseInt(req.getParameter("masterId"));
            String priceParam = req.getParameter("price");
            OrderStatus status = OrderStatus.valueOf(req.getParameter("status"));
            orderService.updateOrder(order, masterId, priceParam, status);
            resp.sendRedirect("info_page/updating_order_success.jsp");
        }
    }
}
