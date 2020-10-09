package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.services.api.OrderService;

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

    OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        if (orderId != 0) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            Order order = orderService.getOrderById(orderId);
            if (user.getRole().equals(UserRole.ADMIN) || order.getCustomerId() == user.getId()) {
                req.setAttribute("order", order);
                req.getRequestDispatcher("OLDorder.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("not_rights.jsp");
            }
        }
    }
}