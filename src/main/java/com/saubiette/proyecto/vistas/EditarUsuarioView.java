package com.saubiette.proyecto.vistas;

import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.RolController;
import com.saubiette.proyecto.controladores.UsuarioController;
import com.saubiette.proyecto.util.SpringContextHelper;
import com.saubiette.vistas.componentes.FormUsuario;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Component
@Route("usuarios/editar")
@CssImport("styles/styles.css")

public class EditarUsuarioView extends VerticalLayout implements HasUrlParameter<String>, AfterNavigationObserver {

	private SpringContextHelper beans;

	private SpringContextHelper getBeans() {
		if (beans == null)
			beans = new SpringContextHelper(UI.getCurrent());
		return beans;
	}

	int idUsuario = 0;
	Div div;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UsuarioController userController;
	RolController rolController;
	FormUsuario form;

	public EditarUsuarioView(UsuarioController userController, RolController rolController) {
		this.userController = userController;
		this.rolController = rolController;
	}

	@Override
	public void setParameter(BeforeEvent event, String id) {

		idUsuario = Integer.parseInt(id);

		Menu menu = new Menu();
		FormUsuario form = new FormUsuario(userController, rolController, idUsuario);

		div = new Div();
		div.addClassName("contenedor");
		div.add(form);

		add(menu, div);
	}

	private UsuarioController getUsuarioController() {
		return (UsuarioController) getBeans().getBean("usuarioController");
	}

	private RolController getRolController() {
		return (RolController) getBeans().getBean("rolController");
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		this.rolController = getRolController();
		this.userController = getUsuarioController();

	}
}
