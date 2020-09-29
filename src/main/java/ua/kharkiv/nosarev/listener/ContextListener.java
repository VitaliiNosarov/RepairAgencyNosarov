package ua.kharkiv.nosarev.listener;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.kharkiv.nosarev.dao.OrderDaoImpl;
import ua.kharkiv.nosarev.dao.UserDaoImpl;
import ua.kharkiv.nosarev.dao.api.OrderDao;
import ua.kharkiv.nosarev.dao.api.UserDao;
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

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ContextListener contextListener = new ContextListener();
        contextListener.initializeLogger(event);
        contextListener.initializeServiceObjects(event);

    }

    private void initializeServiceObjects(ServletContextEvent event){
        try {
            Context context = new InitialContext();
            ServletContext ctx = event.getServletContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/repair_agency");
            UserDao userDao = new UserDaoImpl(ds);
            OrderDao orderDao = new OrderDaoImpl(ds);
            UserService userService = new UserServiceImpl(userDao);
            ctx.setAttribute("userService", userService);
            ctx.setAttribute("orderDao", orderDao);
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

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }
}
