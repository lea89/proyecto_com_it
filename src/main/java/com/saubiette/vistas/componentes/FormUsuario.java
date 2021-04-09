package com.saubiette.vistas.componentes;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.RolController;
import com.saubiette.proyecto.controladores.UsuarioController;
import com.saubiette.proyecto.entidades.Rol;
import com.saubiette.proyecto.entidades.Usuario;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;

@Component
public class FormUsuario extends VerticalLayout implements BeforeEnterListener {
	/**
	 * 
	 */
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

	Span titulo;

	Div formUsuario;

	ComboBox<Rol> roles;

	UsuarioController userController;

	RolController rolController;

	int idUsuario = 0;

	Usuario usuario = null;

	String btn = "";
	String msg = "";

	public FormUsuario(UsuarioController userController, RolController rolController) {

		this.rolController = rolController;
		this.userController = userController;

		setElements(0);
		setListener();

		Div contenedor = new Div();

		contenedor.addClassName("contenedorForm");
		contenedor.add(titulo, txtEmail, txtClave, txtNombre, txtApellido, txtDni, txtObra_social, txtNumero_socio,
				roles, btnCrear);

		add(contenedor);
	}

	public FormUsuario(UsuarioController userController, RolController rolController, int idUsuario) {

		this.rolController = rolController;
		this.userController = userController;
		this.idUsuario = idUsuario;
		setElements(idUsuario);
		setListener();

		// String s = this.userController.traerUsuario(1).getApellido();
		usuario = this.userController.traerUsuario(idUsuario);

		setValueElements(usuario);

		Div contenedor = new Div();

		contenedor.addClassName("contenedorForm");
		contenedor.add(titulo, txtEmail, txtClave, txtNombre, txtApellido, txtDni, txtObra_social, txtNumero_socio,
				roles, btnCrear);

		add(contenedor);
	}

	public void setElements(int idUsuario) {

		formUsuario = new Div();

		txtEmail = new TextField("Email: ");

		txtClave = new PasswordField("Clave: ");

		txtNombre = new TextField("Nombre: ");

		txtApellido = new TextField("Apellido: ");

		txtDni = new TextField("Dni: ");

		txtObra_social = new TextField("Obra social: ");

		txtNumero_socio = new TextField("Numero de afiliado: ");

		btnCrear = new Button("Crear usuario");
		titulo = new Span();
		titulo.addClassName("titulo");

		roles = new ComboBox<Rol>();
		roles.setLabel("Rol");
		ArrayList<Rol> listaRoles = new ArrayList<Rol>();

		if (rolController.traerRoles() != null) {
			rolController.traerRoles().forEach(x -> {
				listaRoles.add(x);
			});
		}

		btn = "";
		msg = "";
		if (idUsuario != 0) {
			titulo.setText("Editar usuario");
			btn = "Editar cambios";
			msg = "Se guardaron los datos de usuario correctamente!";
			btnCrear.setText(btn);
		} else {
			titulo.setText("Alta usuario");
			btn = "Crear cambios";
			msg = "Se creo el usuario correctamente!";
			btnCrear.setText("Crear usuario");
		}

		roles.setItemLabelGenerator(Rol::getRol);
		roles.setItems(listaRoles);
	}

	public void setValueElements(Usuario usuario) {

		if (usuario != null) {
			txtEmail.setValue(usuario.getEmail());
			txtClave.setValue(usuario.getClave());
			txtNombre.setValue(usuario.getNombre());
			txtApellido.setValue(usuario.getApellido());
			txtDni.setValue(usuario.getDni());
			txtObra_social.setValue(usuario.getObra_social());
			txtNumero_socio.setValue(usuario.getN_socio());
			roles.setValue(usuario.getRol());
		}
	}

	public void setListener() {

		btnCrear.addClickListener(event -> {

			if (usuario == null) {
				usuario = new Usuario();
			}

			if (idUsuario != 0)
				usuario.setId(idUsuario);

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

					Thread.sleep(2000);
					notification = new Notification(msg, 3000, Position.BOTTOM_CENTER);
					notification.open();
					UI.getCurrent().navigate("usuarios");

				} else {
					notification = new Notification("Error al crear el usuario.", 3000, Position.BOTTOM_CENTER);
					notification.open();
				}
			} catch (Exception e) {
				notification = new Notification("Error al crear el usuario.", 3000, Position.TOP_END);
				notification.open();
			}

		});
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		// TODO Auto-generated method stub
		setValueElements(null);
		setValueElements(usuario);

	}

}
