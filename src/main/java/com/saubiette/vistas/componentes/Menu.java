package com.saubiette.vistas.componentes;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@Component
@CssImport("./styles/menu.css")
@Tag("menu")
public class Menu extends HorizontalLayout {

	public Menu() {
		MenuBar menuBar = new MenuBar();

		menuBar.setOpenOnHover(true);

		MenuItem establecimientos = menuBar.addItem("Establecimientos");
		MenuItem usuarios = menuBar.addItem("Usuarios");
		MenuItem roles = menuBar.addItem("Roles");
		MenuItem areas = menuBar.addItem("Areas");
		MenuItem personal = menuBar.addItem("Personal");

		SubMenu establecimientoSubmenu = establecimientos.getSubMenu();
		SubMenu usuariosSubmenu = usuarios.getSubMenu();
		SubMenu rolesSubmenu = roles.getSubMenu();

		MenuItem altaItemEstablecimientos = establecimientoSubmenu.addItem("Alta");
		MenuItem bajaItemEstablecimientos = establecimientoSubmenu.addItem("Baja");
		MenuItem modificarItemEstablecimientos = establecimientoSubmenu.addItem("Modificacion");
		MenuItem listarItemEstablecimientos = establecimientoSubmenu.addItem("Lista");

		MenuItem altaItemUsuarios = usuariosSubmenu.addItem("Alta", e -> {
			UI.getCurrent().navigate("altaUsuario");
		});

		MenuItem bajaItemUsuarios = usuariosSubmenu.addItem("Baja");
		MenuItem modificarItemUsuarios = usuariosSubmenu.addItem("Modificacion");
		MenuItem listarItemUsuarios = usuariosSubmenu.addItem("Lista");

		MenuItem altaItemRoles = rolesSubmenu.addItem("Alta", e -> {
			UI.getCurrent().navigate("altaUsuario");
		});

		MenuItem bajaItemRoles = rolesSubmenu.addItem("Baja");
		MenuItem modificarItemRoles = rolesSubmenu.addItem("Modificacion");
		MenuItem listarItemRoles = rolesSubmenu.addItem("Lista");

		add(menuBar);
	}
}
