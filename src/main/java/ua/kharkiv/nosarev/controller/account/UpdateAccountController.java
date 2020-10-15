package ua.kharkiv.nosarev.controller.account;

import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.exception.RegistrationException;
import ua.kharkiv.nosarev.services.api.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            User user = (User) req.getSession().getAttribute("user");
            long accountId = Long.parseLong(req.getParameter("accountId"));
            User account = userService.getUserById(accountId);
            if (account.getId() == user.getId()) {
                req.setAttribute("account", account);
            } else {
                resp.sendRedirect("not_rights.jsp");
                return;
            }
        }
        req.getRequestDispatcher("account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long accountId = Long.parseLong(req.getParameter("accountId"));
        HttpSession session = req.getSession();
        User account = userService.getUserById(accountId);
        account.setEmail(req.getParameter("email"));
        account.setPassword(req.getParameter("password"));
        account.setName(req.getParameter("name"));
        account.setSurName(req.getParameter("surname"));
        account.setPhone(req.getParameter("phone"));
        account.setLocale(UserLocale.valueOf(req.getParameter("locale")));
        try {
            session.setAttribute("user", userService.updateUser(account));
            session.setAttribute("infoMessage", InfoMessage.UPDATING_ACCOUNT.toString());

        } catch (RegistrationException exception) {
            session.setAttribute("infoMessage", InfoMessage.WRONG_FIELDS.toString());
        }
        resp.sendRedirect("updateAccount?accountId=" + accountId);
    }

}
