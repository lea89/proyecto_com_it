package com.saubiette.proyecto.vistas;

import org.springframework.beans.factory.annotation.Autowired;

import com.saubiette.proyecto.GreetService;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@PWA(name = "Vaadin Application", shortName = "Vaadin App", description = "This is an example Vaadin application.", enableInstallPrompt = false)

@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@CssImport("./styles/styles.css")
public class Home extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Home(@Autowired GreetService service) {

		Menu m = new Menu();
		Div div = new Div();
		div.setSizeFull();
		div.add(m);

		add(div);

	}

}
