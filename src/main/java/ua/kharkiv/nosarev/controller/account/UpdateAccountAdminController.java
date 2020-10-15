package ua.kharkiv.nosarev.controller.account;

import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.services.api.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/updateUser")
public class UpdateAccountAdminController extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long accountId = Long.parseLong(req.getParameter("accountId"));
        User account = userService.getUserById(accountId);
        req.setAttribute("account", account);
        req.getRequestDispatcher("admin_update_account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long accountId = Long.parseLong(req.getParameter("accountId"));
        User account = userService.getUserById(accountId);
        if (!req.getParameter("balance").equals("")) {
            account.setBalance(new BigDecimal(req.getParameter("balance")));
        }
        account.setRole(UserRole.valueOf(req.getParameter("role")));
        userService.updateUser(account);
        HttpSession session = req.getSession();
        session.setAttribute("infoMessage", InfoMessage.UPDATING_ACCOUNT.toString());
        resp.sendRedirect("updateUser?accountId=" + accountId);
    }

}
