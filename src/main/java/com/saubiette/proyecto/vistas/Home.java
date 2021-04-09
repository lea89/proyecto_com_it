package com.saubiette.proyecto.vistas;

import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route("")
@PWA(name = "Vaadin Application", shortName = "Vaadin App", description = "This is an example Vaadin application.", enableInstallPrompt = false)

@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@CssImport("./styles/styles.css")
@PreserveOnRefresh
public class Home extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Home() {

		Menu m = new Menu();
		Div div = new Div();
		div.add(m);
		Span text = new Span("No tienes permisos para acceder a esta seccion");
		text.getElement().setAttribute("font-size", "15");
		text.getElement().setAttribute("color", "red");
		add(m, div, text);

	}

}
