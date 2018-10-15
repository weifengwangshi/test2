package com.basefrm.listener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("IndexListener2 contextDestroyed method");
    }
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("============+++++++++++++++++++");
    }

}
