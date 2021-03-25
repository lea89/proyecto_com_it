package com.saubiette.proyecto.vistas;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.RolController;
import com.saubiette.proyecto.controladores.UsuarioController;
import com.saubiette.proyecto.entidades.Rol;
import com.saubiette.proyecto.entidades.Usuario;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Component
@Route("altaUsuario")
@CssImport("styles/styles.css")
public class AltaUsuarioView extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	Button btnCrear;

	TextField txtEmail;
	PasswordField txtClave;
	TextField txtNombre;
	TextField txtApellido;
	TextField txtDni;
	TextField txtObra_social;
	TextField txtNumero_socio;

	TextField mensaje;

	Div formUsuario;

	Menu menu;

	ComboBox<Rol> roles;

	UsuarioController userController;

	RolController rolController;

	@Autowired
	public AltaUsuarioView(RolController rolController, UsuarioController userController) {
		this.rolController = rolController;
		this.userController = userController;
		setElements();

		setListener();

		Div contenedor = new Div();

		contenedor.addClassName("contenedorForm");
		contenedor.add(txtEmail, txtClave, txtNombre, txtApellido, txtDni, txtObra_social, txtNumero_socio, roles,
				btnCrear);

		add(menu, contenedor);
	}

	public void setElements() {
		menu = new Menu();

		formUsuario = new Div();

		txtEmail = new TextField("Email: ");
		txtClave = new PasswordField("Clave: ");

		txtNombre = new TextField("Nombre: ");

		txtApellido = new TextField("Apellido: ");

		txtDni = new TextField("Dni: ");

		txtObra_social = new TextField("Obra social: ");

		txtNumero_socio = new TextField("Numero de afiliado: ");

		btnCrear = new Button("Crear usuario");

		roles = new ComboBox<Rol>();
		roles.setLabel("Rol");
		ArrayList<Rol> listaRoles = new ArrayList<Rol>();

		if (rolController.traerRoles() != null) {
			rolController.traerRoles().forEach(x -> {
				listaRoles.add(x);
			});
		}

		btnCrear.setText(listaRoles.size() + "");
		roles.setItemLabelGenerator(Rol::getRol);
		roles.setItems(listaRoles);
	}

	public void setListener() {
		btnCrear.addClickListener(event -> {

			Usuario usuario = new Usuario();

			usuario.setEmail(txtEmail.getValue());
			usuario.setClave(txtClave.getValue());
			usuario.setNombre(txtNombre.getValue());
			usuario.setApellido(txtApellido.getValue());
			usuario.setDni(txtDni.getValue());
			usuario.setObra_social(txtObra_social.getValue());
			usuario.setN_socio(txtNumero_socio.getValue());
			usuario.setRol(roles.getValue());

			Notification notification;

			try {
				Usuario user = userController.crearUsuario(usuario);
				if (user != null) {

					notification = new Notification("El usuario se creo correctamente", 3000, Position.TOP_END);
					notification.open();

				} else {
					notification = new Notification("Error al crear el usuario.", 3000, Position.TOP_END);
					notification.open();
				}
			} catch (Exception e) {
				notification = new Notification(e.getMessage(), 3000, Position.TOP_END);
				notification.open();
			}

		});
	}

}
