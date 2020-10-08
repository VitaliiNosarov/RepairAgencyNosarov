package ua.kharkiv.nosarev.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.kharkiv.nosarev.dao.OrderDaoImpl;
import ua.kharkiv.nosarev.dao.ServiceDaoImpl;
import ua.kharkiv.nosarev.dao.UserDaoImpl;
import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.dao.api.ServiceDao;
import ua.kharkiv.nosarev.dao.api.UserDao;
import ua.kharkiv.nosarev.entitie.enumeration.UserRole;
import ua.kharkiv.nosarev.services.OrderServiceImpl;
import ua.kharkiv.nosarev.services.UserServiceImpl;
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
    private OrderService orderService;
    private ServletContext ctx;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ContextListener contextListener = new ContextListener();
        contextListener.initializeLogger(event);
        contextListener.initializeContextObjects(event);
    }

    private void initializeContextObjects(ServletContextEvent event) {
        initializeResources(event);
        OrderService orderService = new OrderServiceImpl(orderDao);
        UserService userService = new UserServiceImpl(userDao);
        ctx.setAttribute("userService", userService);
        ctx.setAttribute("orderService", orderService);
        ctx.setAttribute("serviceDao", serviceDao);
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
            orderService = new OrderServiceImpl(orderDao);
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
