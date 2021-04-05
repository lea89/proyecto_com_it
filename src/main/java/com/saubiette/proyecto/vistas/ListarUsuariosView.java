package com.saubiette.proyecto.vistas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.RolController;
import com.saubiette.proyecto.controladores.UsuarioController;
import com.saubiette.proyecto.entidades.Usuario;
import com.saubiette.proyecto.util.SpringContextHelper;
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
@Route("usuarios")
@CssImport("styles/styles.css")
@PreserveOnRefresh
public class ListarUsuariosView extends VerticalLayout implements BeforeEnterObserver {

	UsuarioController userController;

	Iterable<Usuario> usuariosList;
	List<Usuario> result;
	Div contenedor;
	Menu menu;
	Grid<Usuario> grid;

	RolController rolController;

	private static final long serialVersionUID = 1L;

	SpringContextHelper beans;

	Dialog dialog;

	public ListarUsuariosView(RolController rolController, UsuarioController userController) {

		this.userController = userController;
		this.rolController = rolController;

		setElements();

		grid.getDataProvider().refreshAll();
		add(menu, contenedor);
	}

	private SpringContextHelper getBeans() {
		if (beans == null)
			beans = new SpringContextHelper(UI.getCurrent());
		return beans;
	}

	private UsuarioController getControllerUsuario() {

		return (UsuarioController) getBeans().getBean("usuarioController");

	}

	private void setDialog(String text, Usuario usuario) {

		dialog = new Dialog();

		dialog.add(new Text(text));

		dialog.setCloseOnEsc(false);
		dialog.setCloseOnOutsideClick(false);

		Button confirmButton = new Button("Confirmar", event -> {
			userController.eliminarUsuario(usuario.getId());
			result.remove(usuario);

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

		result = new ArrayList<Usuario>();

		menu = new Menu();

		grid = new Grid<>();

		grid.addColumn(Usuario::getEmail).setHeader("E-Mail").setFlexGrow(1);
		grid.addColumn(Usuario::getNombre).setHeader("Nombre");
		grid.addColumn(Usuario::getNombre).setHeader("Apellido");

		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS,
				GridVariant.LUMO_ROW_STRIPES);

		grid.addColumn((usuario) -> {
			return usuario.getRol().getRol();
		}).setHeader("Rol");

		grid.addComponentColumn(usuario -> {
			Div div = new Div();

			Icon eliminar = VaadinIcon.CLOSE.create();

			eliminar.setColor("red");

			eliminar.addClickListener(
					e -> setDialog("Esta seguro que desea eliminar el usuario: " + usuario.getEmail() + "?", usuario));

			Icon editar = new Icon(VaadinIcon.EDIT);

			editar.addClickListener(e -> UI.getCurrent().navigate("usuarios/editar/" + usuario.getId()));

			div.add(editar, eliminar);

			return div;

		});

		contenedor.add(grid);

		loadData();
	}

	private void loadData() {

		usuariosList = userController.traerUsuarios("");
		usuariosList.forEach(result::add);

		grid.setItems(result);

		grid.getDataProvider().refreshAll();
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		// TODO Auto-generated method stub
		result = new ArrayList<Usuario>();

		grid.setItems(result);

		loadData();
	}

}
