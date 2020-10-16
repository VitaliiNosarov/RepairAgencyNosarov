package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.entitie.FeedBack;
import ua.kharkiv.nosarev.entitie.enumeration.FeedbackRate;
import ua.kharkiv.nosarev.service.api.FeedbackService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/saveFeedback")
public class FeedbackController extends HttpServlet {

    private FeedbackService feedbackService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        feedbackService = (FeedbackService) config.getServletContext().getAttribute("feedbackService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long orderId = Long.parseLong(req.getParameter("orderId"));
        FeedBack feedBack = new FeedBack();
        feedBack.setId(orderId);
        feedBack.setComment(req.getParameter("comment"));
        feedBack.setRate(FeedbackRate.valueOf(req.getParameter("rate")));
        HttpSession session = req.getSession();
        session.setAttribute("infoMessage", feedbackService.saveFeedback(feedBack));
        resp.sendRedirect("order?orderId=" + orderId);
    }
}
