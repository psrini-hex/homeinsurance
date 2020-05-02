package com.perscholas.first_servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("from ServletContextListener: " + " context initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}
}