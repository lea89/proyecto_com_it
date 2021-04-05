package com.saubiette.proyecto.vistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.EstablecimientoController;
import com.saubiette.proyecto.controladores.TipoEstController;
import com.saubiette.vistas.componentes.FormEstablecimiento;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Component
@Route("establecimientos/editar")
@CssImport("styles/styles.css")
@PreserveOnRefresh
public class EditarEstablecimientoView extends VerticalLayout
		implements HasUrlParameter<String>, AfterNavigationObserver {
	private static final long serialVersionUID = 1L;

	FormEstablecimiento formEstablecimiento;
	EstablecimientoController establecimientoController;

	TipoEstController tipoEstController;

	@Autowired
	public EditarEstablecimientoView(EstablecimientoController establecimientoController,
			TipoEstController tipoEstController) {
		this.establecimientoController = establecimientoController;
		this.tipoEstController = tipoEstController;
	}

	@Override
	public void setParameter(BeforeEvent event, String id) {

		removeAll();
		Div div = new Div();

		formEstablecimiento = new FormEstablecimiento(establecimientoController, tipoEstController, true,
				Integer.parseInt(id));

		div.add(formEstablecimiento);

		Menu menu = new Menu();
		div.addClassName("contenedor");

		add(menu, div);
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
	}
}
