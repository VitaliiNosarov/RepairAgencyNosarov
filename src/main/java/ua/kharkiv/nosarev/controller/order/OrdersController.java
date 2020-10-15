package ua.kharkiv.nosarev.controller.order;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.PaginationObject;
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

@WebServlet("/orders")
public class OrdersController extends HttpServlet {

    private OrderService orderService;
    private PaginationService paginationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
        paginationService = (PaginationService) config.getServletContext().getAttribute("paginationService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PaginationObject paginationObject = paginationService.getPgObjectFromRequest(req);
        int orderAmount = orderService.getRowsAmount(paginationObject.getFilter(), paginationObject.getFilterParam());
        List<Order> orderList = orderService.findOrders(paginationService
                .buildPaginationSQL(paginationObject), paginationObject);
        paginationService.setPaginationAttributes(req, paginationObject, orderAmount);
        req.setAttribute("orders", orderList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("all_orders.jsp");
        dispatcher.forward(req, resp);
    }
}
