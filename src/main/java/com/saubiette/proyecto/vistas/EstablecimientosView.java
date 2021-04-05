package com.saubiette.proyecto.vistas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.EstablecimientoController;
import com.saubiette.proyecto.entidades.Establecimiento;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Component
@Route("establecimientos")
@CssImport("styles/styles.css")
@PreserveOnRefresh
public class EstablecimientosView extends VerticalLayout implements BeforeEnterObserver {

	Iterable<Establecimiento> establecimientoList;
	List<Establecimiento> result;
	Div contenedor;
	Menu menu;
	Grid<Establecimiento> grid;

	private static final long serialVersionUID = 1L;

	Dialog dialog;
	@Autowired
	EstablecimientoController establecimientoController;

	public EstablecimientosView(EstablecimientoController establecimientoController) {

		this.establecimientoController = establecimientoController;
		setElements();
	}

	private void setDialog(String text, Establecimiento establecimiento) {

		dialog = new Dialog();

		dialog.add(new Text(text));

		dialog.setCloseOnEsc(false);
		dialog.setCloseOnOutsideClick(false);

		Button confirmButton = new Button("Confirmar", event -> {
			establecimientoController.eliminarEstablecimiento(establecimiento);
			result.remove(establecimiento);

			loadData();
			dialog.close();
		});
		confirmButton.addClassName("confirmar");
		Button cancelButton = new Button("Cancelar", event -> {
			dialog.close();
		});

		cancelButton.addClassName("cancelar");
		// Cancel action on ESC press
		Shortcuts.addShortcutListener(dialog, () -> {

			dialog.close();
		}, Key.ESCAPE);

		dialog.add(new Div(confirmButton, cancelButton));

		dialog.open();
	}

	private void setElements() {

		contenedor = new Div();
		contenedor.addClassName("contenedor");

		result = new ArrayList<Establecimiento>();

		menu = new Menu();

		grid = new Grid<Establecimiento>();

		grid.addColumn(Establecimiento::getNombre).setHeader("Nombre");
		grid.addColumn(Establecimiento::getDireccion).setHeader("Direccion");

		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS,
				GridVariant.LUMO_ROW_STRIPES);

		grid.addColumn((establecimiento) -> {
			return establecimiento.getTipo().getNombre();
		}).setHeader("Tipo");

		grid.addComponentColumn(establecimiento -> {
			Div div = new Div();

			Icon eliminar = VaadinIcon.CLOSE.create();

			eliminar.setColor("red");

			eliminar.addClickListener(e -> setDialog(
					"Esta seguro que desea eliminar el establecimiento: " + establecimiento.getNombre() + "?",
					establecimiento));

			Icon editar = new Icon(VaadinIcon.EDIT);

			editar.addClickListener(
					e -> UI.getCurrent().navigate("establecimientos/editar/" + establecimiento.getId()));

			div.add(editar, eliminar);

			return div;

		});

		contenedor.add(grid);

		loadData();

		add(menu, contenedor);
	}

	private void loadData() {

		establecimientoList = establecimientoController.traerEstablecimientos();
		establecimientoList.forEach(result::add);

		grid.setItems(result);

		grid.getDataProvider().refreshAll();
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {

		result = new ArrayList<Establecimiento>();

		grid.setItems(result);

		loadData();
	}
}
