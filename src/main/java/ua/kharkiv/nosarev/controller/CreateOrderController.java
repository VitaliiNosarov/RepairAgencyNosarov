package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.dao.api.ServiceDao;
import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.services.api.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/create_order")
public class CreateOrderController extends HttpServlet {

    private ServiceDao serviceDao;
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
        serviceDao = (ServiceDao) config.getServletContext().getAttribute("serviceDao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Service> list = serviceDao.getAllServices();
        req.setAttribute("list", list);
        req.getRequestDispatcher("create_order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        order.setCustomerId(user.getId());
        order.setDevice(req.getParameter("device"));
        order.setComment(req.getParameter("comment"));
        String[] services = req.getParameterValues("service");
        if (services != null) {
            for (String service : services) {
                order.addService(new Service(Integer.valueOf(service), null));
            }
        }
        session.setAttribute("infoMessage", orderService.insertOrder(order));
        resp.sendRedirect("userOrders");
    }
}
