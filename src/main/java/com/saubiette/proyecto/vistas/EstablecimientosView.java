package com.saubiette.proyecto.vistas;

import java.util.ArrayList;

import com.saubiette.proyecto.entidades.Establecimiento;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Route("establecimientos")
@CssImport("styles/styles.css")
@CssImport("styles/establecimiento.css")
@PreserveOnRefresh
public class EstablecimientosView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EstablecimientosView() {

		Menu m = new Menu();
		Grid<Establecimiento> grid = new Grid<>();

		grid.addClassName("tabla-establecimientos");

		/*
		 * This Data Provider doesn't load all items into the memory right away. Grid
		 * will request only the data that should be shown in its current view "window".
		 * The Data Provider will use callbacks to load only a portion of the data.
		 * 
		 * CallbackDataProvider<Establecimiento, Void> provider = DataProvider
		 * .fromCallbacks(query -> personService .fetch(query.getOffset(),
		 * query.getLimit()).stream(), query -> personService.count());
		 * grid.setDataProvider(provider);
		 */
		ArrayList<Establecimiento> lista = new ArrayList<Establecimiento>();

		grid.setHeightByRows(true);

		grid.addColumn(Establecimiento::getNombre).setHeader("Nombre");
		grid.addColumn(Establecimiento::getDireccion).setHeader("Dirección");
		grid.addColumn(Establecimiento::getTipo).setHeader("Tipo");

		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS,
				GridVariant.LUMO_ROW_STRIPES);

		grid.setItems(lista);
//		
		add(m, grid);
	}

	public String getTipo(int tipo) {
		return "";
	}
}
