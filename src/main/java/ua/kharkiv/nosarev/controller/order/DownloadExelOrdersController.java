package ua.kharkiv.nosarev.controller.order;

import ua.kharkiv.nosarev.service.api.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/orders_download")
public class DownloadExelOrdersController extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService) config.getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = getServletContext().getRealPath("/") + ("orders_report.xls");
        orderService.uploadOrdersToExel(path);
        uploadFile(req, resp);
    }

    private void uploadFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        String fileName = new SimpleDateFormat("yyyy_MM_dd_HH-mm'_orders.xls'").format(new Date());
        resp.setHeader("Content-disposition", "attachment; filename=" + fileName);
        try (InputStream in = req.getServletContext().getResourceAsStream("orders_report.xls");
             OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[1048];
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}
