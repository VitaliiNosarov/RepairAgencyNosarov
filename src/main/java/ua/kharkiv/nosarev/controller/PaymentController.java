package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.entitie.Payment;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.services.PaymentServiceImpl;
import ua.kharkiv.nosarev.services.api.PaymentService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/payment")
public class PaymentController extends HttpServlet {


    private PaymentService paymentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        paymentService = (PaymentService) config.getServletContext().getAttribute("paymentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.parseLong(req.getParameter("orderId"));
        User user = (User) req.getSession().getAttribute("user");
        Payment payment = paymentService.getPayment(orderId);
        if (user.getRole().equals(UserRole.ADMIN) || payment.getUserId() == user.getId()) {
            req.setAttribute("payment", payment);
            req.getRequestDispatcher("payment.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("not_rights.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.parseLong(req.getParameter("orderId"));
        String message = paymentService.payOrder(orderId);
        HttpSession session = req.getSession();
        session.setAttribute("infoMessage", message);
        resp.sendRedirect("order?orderId=" + orderId);
    }
}
