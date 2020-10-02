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
import ua.kharkiv.nosarev.services.UserServiceImpl;
import ua.kharkiv.nosarev.services.api.UserService;

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
import java.util.*;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ContextListener contextListener = new ContextListener();
        contextListener.initializeLogger(event);
        contextListener.initializeServiceObjects(event);
    }

    private void initializeServiceObjects(ServletContextEvent event) {
        try {
            Context context = new InitialContext();
            ServletContext ctx = event.getServletContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/repair_agency");
            UserDao userDao = new UserDaoImpl(ds);
            OrderDao orderDao = new OrderDaoImpl(ds);
            ServiceDao serviceDao = new ServiceDaoImpl(ds);
            UserService userService = new UserServiceImpl(userDao);
            ctx.setAttribute("userService", userService);
            ctx.setAttribute("orderDao", orderDao);
            ctx.setAttribute("serviceDao", serviceDao);
            ctx.setAttribute("uriMap", initializeSecurity(event));
        } catch (NamingException e) {
            LOGGER.error("Can't initialize Datasource", e);
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
            Map<UserRole, Set<String>> uriMap = initializeUriMap(properties);
            return uriMap;
        } catch (IOException e) {
            LOGGER.error("Properties not found");
        }
        LOGGER.error("Can't initialize security");
        throw new RuntimeException();
    }

    private Set<String> parseSecurityPropertiesToSet(Properties properties, UserRole role) {
        String[] values = properties.getProperty(String.valueOf(role))
                .replace(System.lineSeparator(),"\\s").split("\\s");
        Set<String> set = new HashSet<>(Arrays.asList(values));
        return set;
    }

    private Map<UserRole, Set<String>> initializeUriMap(Properties properties) {
        Map<UserRole, Set<String>> map = new HashMap();
        map.put(UserRole.ADMIN, parseSecurityPropertiesToSet(properties, UserRole.ADMIN));
        map.put(UserRole.MASTER, parseSecurityPropertiesToSet(properties, UserRole.MASTER));
        map.put(UserRole.CUSTOMER, parseSecurityPropertiesToSet(properties, UserRole.CUSTOMER));
        return map;
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
