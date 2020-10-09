package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.entitie.Order;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.entitie.enumeration.PaginationField;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.services.api.OrderService;
import ua.kharkiv.nosarev.services.api.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@WebServlet("/orders")
public class OrdersController extends HttpServlet {

    private OrderService orderService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
        String orderBy = req.getParameter("orderBy");
        String reverse = req.getParameter("reverse");
        String filter = req.getParameter("filter");
        String filterParam = req.getParameter("filterParam");
        int numberOfRows = orderService.getRowsAmount();

        int nOfPages = numberOfRows / recordsPerPage;
        if (numberOfRows % recordsPerPage > 0) {
            nOfPages++;
        }

        if (PaginationField.STATUS.toString().equals(filter)) {
            Set statuses = EnumSet.allOf(OrderStatus.class);
            req.setAttribute("statuses", statuses);
        }
        if (PaginationField.MASTER.toString().equals(filter)) {
            List<User> listOfMasters = userService.getAllUsersByRole(UserRole.MASTER);
            req.setAttribute("masters", listOfMasters);
        }
        List<Order> orderList = orderService.findOrders(currentPage, recordsPerPage, orderBy, reverse, filter, filterParam);
        req.setAttribute("orders", orderList);
        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.setAttribute("orderBy", orderBy);
        req.setAttribute("reverse", reverse);
        req.setAttribute("filter", filter);
        req.setAttribute("filterParam", filter);

        RequestDispatcher dispatcher = req.getRequestDispatcher("all_orders.jsp");
        dispatcher.forward(req, resp);
    }
}
