package com.comit.proyecto.vistas;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.comit.proyecto.GreetService;
import com.comit.proyecto.entidades.Establecimiento;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;

@Route("establecimientos")
public class EstablecimientosView extends VerticalLayout{



	public EstablecimientosView() {
		
		Grid<Establecimiento> grid = new Grid<>();
		Establecimiento personService = new Establecimiento("Clinica del niño","Colon 2550", 1);

		/*
		 * This Data Provider doesn't load all items into the memory right away.
		 * Grid will request only the data that should be shown in its current
		 * view "window". The Data Provider will use callbacks to load only a
		 * portion of the data.
		 
		CallbackDataProvider<Establecimiento, Void> provider = DataProvider
		        .fromCallbacks(query -> personService
		                        .fetch(query.getOffset(), query.getLimit()).stream(),
		                query -> personService.count());
		grid.setDataProvider(provider);*/
		ArrayList<Establecimiento> lista = new ArrayList<Establecimiento>();
		lista.add(personService);
		grid.addColumn(Establecimiento::getNombre).setHeader("Nombre");
		grid.addColumn(Establecimiento::getDireccion).setHeader("Dirección");
		grid.addColumn(Establecimiento::getTipo).setHeader("Tipo");
		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
		        GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
		grid.setItems(lista);
//		grid.addColumn(Establecimiento::getAge).setHeader("Age");
		add(grid);
	}
	
	public String getTipo(Object tipo) {
		return "";
	}
}
