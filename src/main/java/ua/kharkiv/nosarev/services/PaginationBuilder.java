package ua.kharkiv.nosarev.services;

import org.apache.log4j.Logger;
import ua.kharkiv.nosarev.dao.SQLConstant;
import ua.kharkiv.nosarev.entitie.PaginationObject;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.OrderStatus;
import ua.kharkiv.nosarev.entitie.enumeration.PaginationField;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.exception.ServiceException;
import ua.kharkiv.nosarev.services.api.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class PaginationBuilder {
    private static final Logger LOGGER = Logger.getLogger(PaginationService.class);
    private UserService userService;

    public PaginationBuilder(UserService userService) {
        this.userService = userService;
    }

    public PaginationObject getPgObjectFromRequest(HttpServletRequest req) {
        PaginationObject pagObject;
        try {
            int currentPage = Integer.parseInt(req.getParameter("currentPage"));
            int recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));
            boolean reverse = Boolean.parseBoolean(req.getParameter("reverse"));
            PaginationField orderBy = PaginationField.valueOf(req.getParameter("orderBy"));
            String filter = req.getParameter("filter");
            String filterParam = req.getParameter("filterParam");
            pagObject = new PaginationObject(currentPage, recordsPerPage, orderBy);
            pagObject.setReverse(reverse);
            if (filter != null) {
                pagObject.setFilter(PaginationField.valueOf(filter));
            }
            if (filterParam != null) {
                pagObject.setFilterParam(filterParam);
            }
        } catch (IllegalArgumentException | NullPointerException exception) {
            LOGGER.error("Exception in getOrderPaginationParams. Wrong Arguments", exception);
            throw new ServiceException();
        }
        return pagObject;
    }

    public HttpServletRequest setPaginationAttributes(HttpServletRequest req, PaginationObject pagObject, int numberOfRows) {

        if (pagObject.getFilter() != null) {
            req.setAttribute("filter", pagObject.getFilter());
            if (pagObject.getFilterParam() != null) {
                req.setAttribute("filterParam", pagObject.getFilterParam());
            }
        }
        if (PaginationField.STATUS.equals(pagObject.getFilter())) {
            Set orderStatuses = EnumSet.allOf(OrderStatus.class);
            req.setAttribute("statuses", orderStatuses);
        }
        if (PaginationField.MASTER.equals(pagObject.getFilter())) {
            List<User> listOfMasters = userService.getAllUsersByRole(UserRole.MASTER);
            req.setAttribute("masters", listOfMasters);
        }
        int nOfPages = numberOfRows / pagObject.getRecordsPerPage();
        if (numberOfRows % pagObject.getRecordsPerPage() > 0) {
            nOfPages++;
        }
        req.setAttribute("reverse", pagObject.isReverse());
        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", pagObject.getCurrentPage());
        req.setAttribute("recordsPerPage", pagObject.getRecordsPerPage());
        req.setAttribute("orderBy", pagObject.getOrderBy());
        return req;
    }

    public String buildPaginationSQL(PaginationObject pagObject) {
        String reverse = "";
        String filterString = "";
        if (pagObject.isReverse()) {
            reverse = SQLConstant.REVERSE;
        }
        if (pagObject.getFilterParam() != null && pagObject.getFilterParam().length() > 0) {
            filterString = SQLConstant.WHERE + pagObject.getFilter().getName() + " = '" + pagObject.getFilterParam() + "'";
        }
        String paginationSQL = SQLConstant.FIND_ORDERS + filterString + SQLConstant.GROUP_BY_ID
                + SQLConstant.ORDER_BY + pagObject.getOrderBy().getName() + reverse + SQLConstant.LIMIT;
        return paginationSQL;
    }
}
