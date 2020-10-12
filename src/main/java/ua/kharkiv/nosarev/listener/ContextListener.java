package ua.kharkiv.nosarev.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.kharkiv.nosarev.dao.*;
import ua.kharkiv.nosarev.dao.api.*;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.services.*;
import ua.kharkiv.nosarev.services.api.FeedbackService;
import ua.kharkiv.nosarev.services.api.OrderService;
import ua.kharkiv.nosarev.services.api.UserService;
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
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);
    private DataSource ds;
    private UserDao userDao;
    private OrderDao orderDao;
    private ServiceDao serviceDao;
    private FeedbackDao feedbackDao;
    private PaymentDao paymentDao;
    private FeedbackService feedbackService;
    private OrderService orderService;
    private UserService userService;
    private PaymentServiceImpl paymentService;
    private ServletContext ctx;
    private PaginationService paginationService;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ContextListener contextListener = new ContextListener();
        contextListener.initializeLogger(event);
        contextListener.initializeContextObjects(event);
    }

    private void initializeContextObjects(ServletContextEvent event) {
        initializeResources(event);
        ctx.setAttribute("userService", userService);
        ctx.setAttribute("orderService", orderService);
        ctx.setAttribute("serviceDao", serviceDao);
        ctx.setAttribute("paginationService", paginationService);
        ctx.setAttribute("feedbackService", feedbackService);
        ctx.setAttribute("paymentService", paymentService);
        ctx.setAttribute("uriMap", initializeSecurity(event));
        LOGGER.info("Context was initialized");
    }

    private void initializeResources(ServletContextEvent event) {
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/repair_agency");
            userDao = new UserDaoImpl(ds);
            orderDao = new OrderDaoImpl(ds);
            serviceDao = new ServiceDaoImpl(ds);
            feedbackDao = new FeedbackDaoImpl(ds);
            paymentDao = new PaymentDaoImpl(ds);
            userService = new UserServiceImpl(userDao);
            orderService = new OrderServiceImpl(orderDao);
            paginationService = new PaginationService(userService);
            feedbackService = new FeedbackServiceImpl(feedbackDao);
            paymentService = new PaymentServiceImpl(orderService, paymentDao);
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
        try {
            FileInputStream fis = new FileInputStream(fullPath);
            properties.load(fis);
            Map<UserRole, Set<String>> uriMap = SecurityUtil.initializeUriMap(properties);
            return uriMap;
        } catch (IOException e) {
            LOGGER.error("Security wasn't initialized, Properties not found");
            throw new RuntimeException();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
