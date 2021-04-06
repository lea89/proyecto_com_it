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
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Component
@Route("personal/editar")
@CssImport("styles/styles.css")
@PreserveOnRefresh
public class EditarPersonalView extends VerticalLayout implements HasUrlParameter<String> {

	private static final long serialVersionUID = 1L;

	@Autowired
	PersonalController personalController;

	@Autowired
	AreaController areaController;

	@Autowired
	EstablecimientoController establecimientoController;

	int idPersonal = 0;

	public EditarPersonalView() {

	}

	@Override
	public void setParameter(BeforeEvent event, String id) {
		Menu menu = new Menu();

		// removeAll();

		FormPersonal formPersonal = new FormPersonal(personalController, areaController, establecimientoController,
				Integer.parseInt(id));

		add(menu, formPersonal);
	}
}
