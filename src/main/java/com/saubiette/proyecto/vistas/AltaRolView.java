package com.saubiette.proyecto.vistas;

import org.springframework.beans.factory.annotation.Autowired;

import com.saubiette.proyecto.controladores.RolController;
import com.saubiette.proyecto.entidades.Rol;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("roles/alta")
public class AltaRolView extends VerticalLayout {

	@Autowired
	RolController rolController;

	TextField txtRol;
	Button btnCrear;

	public AltaRolView() {
		Menu menu = new Menu();

		setElements();

		setListener();

		Div contenedor = new Div();

		contenedor.addClassName("contenedorForm");
		contenedor.add(txtRol, btnCrear);

		add(menu, contenedor);
	}

	public void setElements() {

		txtRol = new TextField("Rol");
		btnCrear = new Button("Crear rol");
	}

	public void setListener() {

		btnCrear.addClickListener(ev -> {
			Rol rol = new Rol();

			rol.setRol(txtRol.getValue());

			Notification notification;

			try {
				Rol rolCreado = rolController.crearRol(rol);
				;
				if (rolCreado != null) {

					notification = new Notification("El rol se creo correctamente", 3000, Position.BOTTOM_CENTER);
					notification.open();

				} else {
					notification = new Notification("Error al crear el rol.", 3000, Position.TOP_END);
					notification.open();
				}
			} catch (Exception e) {
				notification = new Notification(e.getMessage(), 3000, Position.BOTTOM_CENTER);
				notification.open();
			}

		});
	}
}
