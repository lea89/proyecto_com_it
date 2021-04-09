package com.saubiette.proyecto.vistas;

import org.springframework.beans.factory.annotation.Autowired;

import com.saubiette.proyecto.controladores.RolController;
import com.saubiette.proyecto.entidades.Rol;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("roles/alta")
public class AltaRolView extends VerticalLayout {

	@Autowired
	RolController rolController;

	TextField txtRol;
	Button btnCrear;

	Span titulo = new Span();

	public AltaRolView() {
		Menu menu = new Menu();

		setElements();

		setListener();

		Div contenedor = new Div();

		titulo.setText("Alta rol");
		titulo.addClassName("titulo");

		contenedor.addClassName("contenedorForm");
		contenedor.add(titulo, txtRol, btnCrear);

		titulo.setText("Alta rol");
		titulo.addClassName("titulo");

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

				if (rolCreado != null) {

					Notification.show("El rol fue dado de alta satisfactoriamente", 3000, Position.TOP_CENTER)
							.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

					Thread.sleep(2000);

					UI.getCurrent().navigate("roles");

				} else {
					Notification.show("El rol no pudo guardarse", 3000, Position.TOP_CENTER)
							.addThemeVariants(NotificationVariant.LUMO_ERROR);
				}
			} catch (Exception e) {
				Notification.show("El rol no pudo guardarse", 3000, Position.TOP_CENTER)
						.addThemeVariants(NotificationVariant.LUMO_ERROR);
			}

		});
	}
}
