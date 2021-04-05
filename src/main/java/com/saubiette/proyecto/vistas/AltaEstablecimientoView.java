package com.saubiette.proyecto.vistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.EstablecimientoController;
import com.saubiette.proyecto.controladores.TipoEstController;
import com.saubiette.proyecto.entidades.Establecimiento;
import com.saubiette.vistas.componentes.FormEstablecimiento;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Component
@Route("establecimientos/alta")
@CssImport("styles/styles.css")
@PreserveOnRefresh
public class AltaEstablecimientoView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	Binder<Establecimiento> binder;

	@Autowired
	public AltaEstablecimientoView(EstablecimientoController establecimientoController,
			TipoEstController tipoEstController) {

		FormEstablecimiento formEstablecimiento = new FormEstablecimiento(establecimientoController, tipoEstController,
				false, 0);

		Menu menu = new Menu();
		add(menu, formEstablecimiento);

	}

}