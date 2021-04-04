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

		MenuItem altaItemEstablecimientos = establecimientoSubmenu.addItem("Alta", e -> {
			UI.getCurrent().navigate("establecimientos/alta");
		});

		MenuItem listarItemEstablecimientos = establecimientoSubmenu.addItem("Lista", e -> {
			UI.getCurrent().navigate("establecimientos");
		});

		MenuItem altaItemUsuarios = usuariosSubmenu.addItem("Alta", e -> {
			UI.getCurrent().navigate("usuarios/alta");
		});

		MenuItem listarItemUsuarios = usuariosSubmenu.addItem("Listar", e -> {
			UI.getCurrent().navigate("usuarios");
		});

		MenuItem altaItemRoles = rolesSubmenu.addItem("Alta", e -> {
			UI.getCurrent().navigate("roles/alta");
		});

		MenuItem listarItemRoles = rolesSubmenu.addItem("Lista", e -> {
			UI.getCurrent().navigate("roles");
		});

		add(menuBar);
	}
}
