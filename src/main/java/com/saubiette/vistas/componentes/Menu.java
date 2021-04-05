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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		SubMenu areaSubmenu = areas.getSubMenu();
		SubMenu personalSubmenu = personal.getSubMenu();

		establecimientoSubmenu.addItem("Alta", e -> {
			UI.getCurrent().navigate("establecimientos/alta");
		});

		establecimientoSubmenu.addItem("Lista", e -> {
			UI.getCurrent().navigate("establecimientos");
		});

		usuariosSubmenu.addItem("Alta", e -> {
			UI.getCurrent().navigate("usuarios/alta");
		});

		usuariosSubmenu.addItem("Listar", e -> {
			UI.getCurrent().navigate("usuarios");
		});

		rolesSubmenu.addItem("Alta", e -> {
			UI.getCurrent().navigate("roles/alta");
		});

		rolesSubmenu.addItem("Lista", e -> {
			UI.getCurrent().navigate("roles");
		});

		areaSubmenu.addItem("Alta", e -> {
			UI.getCurrent().navigate("areas/alta");
		});

		areaSubmenu.addItem("Lista", e -> {
			UI.getCurrent().navigate("areas");
		});

		personalSubmenu.addItem("Alta", e -> {
			UI.getCurrent().navigate("personal/alta");
		});

		personalSubmenu.addItem("Lista", e -> {
			UI.getCurrent().navigate("personal");
		});

		add(menuBar);
	}
}
