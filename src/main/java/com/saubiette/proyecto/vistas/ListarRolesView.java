package com.saubiette.proyecto.vistas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.RolController;
import com.saubiette.proyecto.entidades.Rol;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.Text;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Component
@Route("roles")
@CssImport("styles/styles.css")
public class ListarRolesView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RolController rolController;
	Iterable<Rol> rolList;

	List<Rol> result;

	Grid<Rol> grid;

	Div contenedor;

	Menu menu;

	Dialog dialog;

	public ListarRolesView(RolController rolController) {

		this.rolController = rolController;

		setElements();

		add(menu, contenedor);

	}

	private void setElements() {

		dialog = new Dialog();
		contenedor = new Div();
		contenedor.addClassName("contenedor");

		result = new ArrayList<Rol>();

		menu = new Menu();

		grid = new Grid<>();

		grid.addColumn(Rol::getRol).setHeader("Rol").setFlexGrow(1);

		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS,
				GridVariant.LUMO_ROW_STRIPES);

		contenedor.add(grid);

		loadData();

		/*
		 * this.rolController = getRolController(); this.userController =
		 * getUsuarioController(); loadData();
		 */
	}

	private void setDialogEdita(Rol rol) {

		dialog = new Dialog();
		Div div = new Div();
		Div divButton = new Div();

		div.addClassName("dialog-edita");
		Text titulo = new Text("Editar rol");

		TextField txtRol = new TextField("Ingrese el rol");

		txtRol.focus();

		dialog.add(txtRol);

		Button confirmar = new Button("Confirmar", e -> {
			rol.setRol(txtRol.getValue());
			rolController.actualizarRol(rol);
			dialog.close();
			grid.getDataProvider().refreshItem(rol);
		});
		confirmar.addClassName("confirmar");

		Button cancelar = new Button("Cancelar", e -> {
			dialog.close();
		});

		cancelar.addClassName("cancelar");

		divButton.add(confirmar, cancelar);
		div.add(titulo, txtRol, divButton);
		div.addClassName("dialog-edita");
		dialog.add(div);

		dialog.open();

	}

	private void setDialogElimina(Rol rol) {

		Div divButton = new Div();
		Div div = new Div();

		dialog = new Dialog();
		dialog.setCloseOnEsc(false);
		dialog.setCloseOnOutsideClick(false);

		Span titulo = new Span("Eliminar rol");

		Button confirmar = new Button("Confirmar", event -> {
			rolController.eliminarRol(rol);

			result.remove(rol);

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
		div.add(titulo, new Span("Desea eliminar el rol: " + rol.getRol() + "?"));
		dialog.add(div, divButton);
		dialog.open();
	}

	private void loadData() {

		rolList = rolController.traerRoles();
		rolList.forEach(result::add);

		grid.setItems(result);

		grid.addComponentColumn(rol -> {
			Div div = new Div();

			Icon eliminar = VaadinIcon.CLOSE.create();

			eliminar.setColor("red");

			eliminar.addClickListener(e -> setDialogElimina(rol));

			Icon editar = new Icon(VaadinIcon.EDIT);

			editar.addClickListener(e -> setDialogEdita(rol));

			div.add(editar, eliminar);

			return div;

		});
		grid.getDataProvider().refreshAll();
	}
}
