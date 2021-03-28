package com.saubiette.proyecto.util;

import java.io.Serializable;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServlet;

public class SpringContextHelper implements Serializable {
	private static final long serialVersionUID = 1L;
	private UI application;

	public SpringContextHelper(UI application) {
		this.application = application;
	}

	public Object getBean(final String beanRef) {
		return getContext().getBean(beanRef);
	}

	private ServletContext getServletContext() {
		return VaadinServlet.getCurrent().getServletContext();
	}

	private ApplicationContext getContext() {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	}

}
