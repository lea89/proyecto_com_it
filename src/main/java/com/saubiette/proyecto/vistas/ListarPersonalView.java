package com.saubiette.proyecto.vistas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.EstablecimientoController;
import com.saubiette.proyecto.controladores.PersonalController;
import com.saubiette.proyecto.entidades.Personal;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Component
@Route("personal")
@CssImport("styles/styles.css")
@PreserveOnRefresh
public class ListarPersonalView extends VerticalLayout implements BeforeEnterObserver {

	private static final long serialVersionUID = 1L;

	Grid<Personal> grid;

	Personal personal;

	Div contenedor;

	Dialog dialog;

	List<Personal> result;
	Iterable<Personal> personalList;

	@Autowired
	PersonalController personalController;

	@Autowired
	EstablecimientoController establecimientoController;

	public ListarPersonalView(PersonalController personalController) {
		this.personalController = personalController;

		init();
	}

	private void init() {
		contenedor = new Div();
		contenedor.addClassName("contenedor");

		result = new ArrayList<Personal>();

		Menu menu = new Menu();

		grid = new Grid<Personal>();

		grid.addColumn(Personal::getLegajo).setHeader("Legajo");
		grid.addColumn(Personal::toString).setHeader("Nombre");
		grid.addColumn(Personal::getDni).setHeader("DNI");
		grid.addColumn(Personal::getDireccion).setHeader("Direccion");

		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS,
				GridVariant.LUMO_ROW_STRIPES);

		grid.addColumn((area) -> {
			return area.getArea().getArea();
		}).setHeader("Area");

		grid.addColumn((establecimiento) -> {
			return establecimiento.getEstablecimiento().getNombre();
		}).setHeader("Establecimiento");

		grid.addComponentColumn(personal -> {
			Div div = new Div();

			Icon eliminar = VaadinIcon.CLOSE.create();

			eliminar.setColor("red");

			eliminar.addClickListener(e -> setDialogElimina(personal));

			Icon editar = new Icon(VaadinIcon.EDIT);

			editar.addClickListener(e -> UI.getCurrent().navigate("personal/editar/" + personal.getId()));

			div.add(editar, eliminar);

			return div;

		});

		contenedor.add(grid);

		loadData();

		add(menu, contenedor);
	}

	private void setDialogElimina(Personal personal) {

		Div divButton = new Div();
		Div div = new Div();

		dialog = new Dialog();
		dialog.setCloseOnEsc(false);
		dialog.setCloseOnOutsideClick(false);

		Span titulo = new Span("Eliminar area");

		Button confirmar = new Button("Confirmar", event -> {
			personalController.eliminarPersonal(personal);

			result = new ArrayList<Personal>();

			grid.setItems(result);

			loadData();

			dialog.close();
		});

		confirmar.addClassName("confirmar");

		Button cancelar = new Button("Cancelar", event -> {

			dialog.close();
		});
		// Cancel action on ESC press
		Shortcuts.addShortcutListener(dialog, () -> {
			dialog.close();
		}, Key.ESCAPE);

		cancelar.addClassName("cancelar");

		divButton.add(confirmar, cancelar);

		div.addClassName("dialog-elimina");
		div.add(titulo, new Span("Desea eliminar a: " + personal.toString() + "?"));
		dialog.add(div, divButton);
		dialog.open();
	}

	private void loadData() {

		personalList = personalController.traerPersonal();
		personalList.forEach(result::add);

		grid.setItems(result);

		grid.getDataProvider().refreshAll();
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {

		result = new ArrayList<Personal>();

		grid.setItems(result);

		loadData();
	}
}
