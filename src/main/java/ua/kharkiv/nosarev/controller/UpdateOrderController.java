package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.services.api.FeedbackService;
import ua.kharkiv.nosarev.services.api.OrderService;
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

@WebServlet("/updateOrder")
public class UpdateOrderController extends HttpServlet {

    private OrderService orderService;
    private UserService userService;
    private FeedbackService feedbackService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
        feedbackService = (FeedbackService) config.getServletContext().getAttribute("feedbackService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("order") == null) {
            long orderId = Long.parseLong(req.getParameter("orderId"));
            Order order = orderService.getOrderById(orderId);
            if (order.getStatus().equals(OrderStatus.COMPLETED)) {
                req.setAttribute("feedback", feedbackService.getFeedback(orderId));
            }
            List<User> listOfMasters = userService.getAllUsersByRole(UserRole.MASTER);
            req.setAttribute("order", order);
            req.setAttribute("masters", listOfMasters);
            req.getRequestDispatcher("update_order.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.parseLong(req.getParameter("orderId"));
        if (orderId != 0) {
            Order order = orderService.getOrderById(orderId);
            order.setMasterId(Long.parseLong(req.getParameter("masterId")));
            String price = req.getParameter("price");
            if (price != "") {
                BigDecimal cost = new BigDecimal(price);
                order.setPrice(cost);
            }
            order.setStatus(OrderStatus.valueOf(req.getParameter("status")));
            HttpSession session = req.getSession();
            session.setAttribute("infoMessage", orderService.updateOrder(order));
            orderService.updateNewOrderCount(req);
            resp.sendRedirect("updateOrder?orderId=" + orderId);
        }
    }
}
