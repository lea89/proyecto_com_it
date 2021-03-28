package com.saubiette.proyecto.vistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.RolController;
import com.saubiette.proyecto.controladores.UsuarioController;
import com.saubiette.vistas.componentes.FormUsuario;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

@Component
@Route("usuarios/alta")
@CssImport("styles/styles.css")
public class AltaUsuarioView extends VerticalLayout implements RouterLayout {
	private static final long serialVersionUID = 1L;

	@Autowired
	public AltaUsuarioView(RolController rolController, UsuarioController userController) {

		Menu menu = new Menu();

		FormUsuario formUsuario = new FormUsuario(userController, rolController);

		add(menu, formUsuario);
	}

}
