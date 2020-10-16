package ua.kharkiv.nosarev.controller.order;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.service.api.FeedbackService;
import ua.kharkiv.nosarev.service.api.OrderService;
import ua.kharkiv.nosarev.service.api.UserService;

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
            req.getRequestDispatcher("admin_update_order.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.parseLong(req.getParameter("orderId"));
        HttpSession session = req.getSession();
        if (orderId != 0) {
            Order order = orderService.getOrderById(orderId);
            order.setMasterId(Long.parseLong(req.getParameter("masterId")));
            String price = req.getParameter("price");
            try {
                if (price != null && price.length() > 0) {
                    order.setPrice(new BigDecimal(price));
                }
                order.setStatus(OrderStatus.valueOf(req.getParameter("status")));
            } catch (IllegalArgumentException | NullPointerException ex) {
                session.setAttribute("infoMessage", InfoMessage.WRONG_FIELDS);
                return;
            }
            session.setAttribute("infoMessage", orderService.updateOrder(order));
            orderService.updateNewOrderCount(req);
            resp.sendRedirect("updateOrder?orderId=" + orderId);
        }
    }
}
