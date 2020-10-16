package ua.kharkiv.nosarev.controller.order;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.service.api.FeedbackService;
import ua.kharkiv.nosarev.service.api.OrderService;

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

    private OrderService orderService;
    private FeedbackService feedbackService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
        feedbackService = (FeedbackService) config.getServletContext().getAttribute("feedbackService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.parseLong(req.getParameter("orderId"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Order order = orderService.getOrderById(orderId);
        if (order.getStatus().equals(OrderStatus.COMPLETED)) {
            req.setAttribute("feedback", feedbackService.getFeedback(orderId));
        }
        if (user.getRole().equals(UserRole.ADMIN) || order.getCustomerId() == user.getId()) {
            req.setAttribute("order", order);
            req.getRequestDispatcher("order.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("not_rights.jsp");
        }

    }
}