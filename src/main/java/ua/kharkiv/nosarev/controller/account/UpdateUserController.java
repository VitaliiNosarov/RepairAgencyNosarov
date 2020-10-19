package ua.kharkiv.nosarev.controller.account;

import ua.kharkiv.nosarev.entitie.User;
import ua.kharkiv.nosarev.entitie.enumeration.InfoMessage;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.exception.RegistrationException;
import ua.kharkiv.nosarev.service.api.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUserController extends HttpServlet {

    private static final String ACCOUNT_ID = "accountId";
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User account = null;
        User user = (User) req.getSession().getAttribute("user");
        if (req.getParameter(ACCOUNT_ID) != null) {
            long accountId = Long.parseLong(req.getParameter(ACCOUNT_ID));
            account = userService.getUserById(accountId);
        } else {
            account = userService.getUserById(user.getId());
        }
        if (account.getId() != user.getId()) {
            resp.sendRedirect("not_rights.jsp");
            return;
        }
        req.setAttribute("account", account);
        req.getRequestDispatcher("account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long accountId = Long.parseLong(req.getParameter(ACCOUNT_ID));
        HttpSession session = req.getSession();
        User account = userService.getUserById(accountId);
        account.setEmail(req.getParameter("email"));
        String password = req.getParameter("password");
        if (password != null && password.length() > 5) {
            account.setPassword(password);
        }
        account.setName(req.getParameter("name"));
        account.setSurName(req.getParameter("surname"));
        account.setPhone(req.getParameter("phone"));
        account.setLocale(UserLocale.valueOf(req.getParameter("locale")));
        try {
            session.setAttribute("user", userService.updateUser(account));
            session.setAttribute("infoMessage", InfoMessage.UPDATING_ACCOUNT_SUCCESS);
        } catch (RegistrationException exception) {
            session.setAttribute("infoMessage", InfoMessage.WRONG_FIELDS);
        }
        resp.sendRedirect("updateUser?accountId=" + accountId);
    }
}
