package ua.kharkiv.nosarev.controller.order;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.service.api.OfficeService;
import ua.kharkiv.nosarev.service.api.OrderService;

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

    private OfficeService officeService;
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
        officeService = (OfficeService) config.getServletContext().getAttribute("officeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLocale language = UserLocale.valueOf(req.getSession().getAttribute("language").toString());
        List<Service> list = officeService.getAllServices(language);
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
        orderService.updateNewOrderCount(req);
        resp.sendRedirect("userOrders");
    }
}
