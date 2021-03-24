package com.saubiette.proyecto.vistas;

import org.springframework.stereotype.Component;

import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("admin")
@Component
@CssImport("styles/styles.css")
public class AdminView extends HorizontalLayout{

	public AdminView() {
		
		Menu menu = new Menu();
		
		
		add(menu);
	}
}
