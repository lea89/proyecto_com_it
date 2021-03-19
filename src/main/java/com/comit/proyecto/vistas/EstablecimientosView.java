package com.comit.proyecto.vistas;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.comit.proyecto.GreetService;
import com.comit.proyecto.entidades.Clinica;
import com.comit.proyecto.entidades.Establecimiento;
import com.comit.proyecto.entidades.Hospital;
import com.comit.proyecto.vistas.componentes.Menu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;

@Route("establecimientos")
@CssImport("styles/styles.css")
@CssImport("styles/establecimiento.css")
public class EstablecimientosView extends VerticalLayout{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EstablecimientosView() {
		
		Menu m = new Menu();
		Grid<Establecimiento> grid = new Grid<>();
		
		grid.addClassName("tabla-establecimientos");
		
		Establecimiento e1 = new Clinica("Colon 2550","Clinica del niño", 1);
		Establecimiento e2 = new Hospital("Guemes 123","HPC Guemes", 1);
		Establecimiento e3 = new Clinica("Jujuy 3000","Pueyrredon", 1);
		Establecimiento e4 = new Clinica("Colon 2550","Clinica del niño", 1);

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
		
		grid.setHeightByRows(true);
		
		lista.add(e1);
		lista.add(e2);
		lista.add(e3);
		lista.add(e4);
		
		
		grid.addColumn(Establecimiento::getNombre).setHeader("Nombre");
		grid.addColumn(Establecimiento::getDireccion).setHeader("Dirección");
		grid.addColumn(Establecimiento::getTipo).setHeader("Tipo");
		
		grid.addThemeVariants(
				GridVariant.LUMO_NO_BORDER,
		        GridVariant.LUMO_NO_ROW_BORDERS, 
		        GridVariant.LUMO_ROW_STRIPES);
		
		grid.setItems(lista);
//		
		add(m, grid);
	}
	
	public String getTipo(int tipo) {
		return "";
	}
}
