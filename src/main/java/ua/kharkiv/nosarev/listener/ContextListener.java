package ua.kharkiv.nosarev.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.kharkiv.nosarev.dao.*;
import ua.kharkiv.nosarev.dao.api.*;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.service.*;
import ua.kharkiv.nosarev.service.api.*;
import ua.kharkiv.nosarev.util.SecurityUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);
    private FeedbackService feedbackService;
    private OrderService orderService;
    private UserService userService;
    private PaymentService paymentService;
    private OfficeService officeService;
    private long countOfNewOrders;
    private ServletContext ctx;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        Locale.setDefault(Locale.ENGLISH);
        ContextListener contextListener = new ContextListener();
        contextListener.initializeLogger(event);
        contextListener.initializeContextObjects(event);
    }

    private void initializeContextObjects(ServletContextEvent event) {
        initializeResources(event);
        ctx.setAttribute("userService", userService);
        ctx.setAttribute("orderService", orderService);
        ctx.setAttribute("officeService", officeService);
        ctx.setAttribute("feedbackService", feedbackService);
        ctx.setAttribute("paymentService", paymentService);
        ctx.setAttribute("countOfNewOrders", countOfNewOrders);
        ctx.setAttribute("uriMap", initializeSecurity(event));
        LOGGER.info("Context was initialized");
    }

    private void initializeResources(ServletContextEvent event) {
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/repair_agency");
            UserDao userDao = new UserDaoImpl(ds);
            OrderDao orderDao = new OrderDaoImpl(ds);
            OfficeDao officeDao = new OfficeDaoImpl(ds);
            FeedbackDao feedbackDao = new FeedbackDaoImpl(ds);
            PaymentDao paymentDao = new PaymentDaoImpl(ds);
            Validator validator = new Validator();
            userService = new UserServiceImpl(userDao, validator);
            feedbackService = new FeedbackServiceImpl(feedbackDao, validator);
            orderService = new OrderServiceImpl(orderDao, userService, validator);
            officeService = new OfficeServiceImpl(officeDao, validator);
            paymentService = new PaymentServiceImpl(orderService, paymentDao);
            countOfNewOrders = orderDao.getNewOrdersAmount();
            ctx = event.getServletContext();
        } catch (NamingException e) {
            LOGGER.error("Can't initialize Datasource", e);
            throw new RuntimeException();
        }
    }

    private void initializeLogger(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        PropertyConfigurator.configure(fullPath);
        LOGGER.info("Application was started");
    }

    private Map<UserRole, Set<String>> initializeSecurity(ServletContextEvent event) {
        Properties properties = new Properties();
        ServletContext context = event.getServletContext();
        String securityConfigFile = context.getInitParameter("security-config-location");
        String fullPath = context.getRealPath("") + File.separator + securityConfigFile;
        try (FileInputStream fis = new FileInputStream(fullPath);) {
            properties.load(fis);
            return SecurityUtil.initializeUriMap(properties);
        } catch (IOException e) {
            LOGGER.error("Security wasn't initialized, Properties not found" + e);
            throw new RuntimeException();
        }
    }

}
