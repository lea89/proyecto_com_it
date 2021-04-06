package com.saubiette.proyecto.vistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.AreaController;
import com.saubiette.proyecto.controladores.EstablecimientoController;
import com.saubiette.proyecto.controladores.PersonalController;
import com.saubiette.vistas.componentes.FormPersonal;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Component
@Route("personal/alta")
@CssImport("styles/styles.css")
@PreserveOnRefresh
public class AltaPersonalView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public AltaPersonalView(PersonalController personalController, AreaController areaController,
			EstablecimientoController establecimientoController) {

		Menu menu = new Menu();
		FormPersonal formPersonal = new FormPersonal(personalController, areaController, establecimientoController, 0);

		add(menu, formPersonal);
	}
}
