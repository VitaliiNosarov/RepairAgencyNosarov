package ua.kharkiv.nosarev.controller.order;

import ua.kharkiv.nosarev.entitie.Service;
import ua.kharkiv.nosarev.entitie.enumeration.UserLocale;
import ua.kharkiv.nosarev.service.api.OfficeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/service_manager")
public class ServicesController extends HttpServlet {

    private OfficeService officeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        officeService = (OfficeService) config.getServletContext().getAttribute("officeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Service> services = officeService.getAllServices(UserLocale.EN);
        req.setAttribute("services", services);
        req.getRequestDispatcher("services.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long serviceId = Long.parseLong(req.getParameter("serviceId"));
        Service newService = new Service();
        newService.setId(serviceId);
        newService.setName(req.getParameter("name_en"));
        newService.setNameRu(req.getParameter("name_ru"));

        HttpSession session = req.getSession();
        session.setAttribute("infoMessage", officeService.saveService(newService));
        resp.sendRedirect("service_manager");
    }
}
