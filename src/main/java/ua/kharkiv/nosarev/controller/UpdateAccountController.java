package ua.kharkiv.nosarev.controller;

import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.services.api.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/updateAccount")
public class UpdateAccountController extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("accountId") != null) {
            int accountId = Integer.parseInt(req.getParameter("accountId"));
            User account = userService.getUserById(accountId);
            req.setAttribute("account", account);
        }
        req.getRequestDispatcher("account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int accountId = Integer.parseInt(req.getParameter("accountId"));
        User account = userService.getUserById(accountId);
        account.setEmail(req.getParameter("email"));
        account.setPassword(req.getParameter("password"));
        account.setName(req.getParameter("name"));
        account.setSurName(req.getParameter("surname"));
        account.setPhone(req.getParameter("phone"));
        if (!req.getParameter("balance").equals("")) {
            account.setBalance(new BigDecimal(req.getParameter("balance")));
        }
        account.setRole(UserRole.valueOf(req.getParameter("role")));
        account.setLocale(UserLocale.valueOf(req.getParameter("locale")));
        userService.updateUser(account);
        resp.sendRedirect("info_page/registration_success.jsp");
    }

}
