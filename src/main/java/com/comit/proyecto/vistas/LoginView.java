package com.comit.proyecto.vistas;

import org.springframework.beans.factory.annotation.Autowired;

import com.comit.proyecto.controladores.UsuarioController;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.Route;

@Route
@CssImport("styles/login.css")
public class LoginView extends VerticalLayout{

	Button btnIngresar;
	@Autowired UsuarioController userController;
	
	public LoginView () {
		
		Div form = new Div();
		
		form.addClassName("form");
		
		TextField txtUsuario = new TextField();
		
		txtUsuario.setLabel("Usuario: ");
		txtUsuario.setPlaceholder("Ingrese su nombre de usuario");
		txtUsuario.setRequired(true);
		txtUsuario.addThemeVariants(
				TextFieldVariant.LUMO_ALIGN_CENTER
				);
		
		PasswordField txtClave = new PasswordField();
		
		txtClave.setLabel("Clave: ");
		txtClave.setPlaceholder("Ingrese su clave");
		txtClave.setRequired(true);
		txtClave.addThemeVariants(
				TextFieldVariant.LUMO_ALIGN_CENTER
				);

		btnIngresar = new Button("Ingresar");
		
		btnIngresar.addThemeVariants(
				ButtonVariant.LUMO_PRIMARY,
				ButtonVariant.LUMO_SUCCESS
				);
		
		
		Div label = new Div();
		
		label.addClassName("responseLogin");
		
		form.add(txtUsuario,txtClave, btnIngresar, label);
		
		
		btnIngresar.addClickListener(e -> {
			
			

			boolean res = login(txtUsuario.getValue(), txtClave.getValue());
			
			
			if(res) {
				//label.setText("Usuario y clave correctos");
				UI.getCurrent().navigate("");
			} else {
				label.setText("Usuario y clave INCORRECTOS");
			}
		});

		add(form);
	}
	
	public boolean login(String usuario, String clave) {
		

		
		return this.userController.login(usuario, clave);
	}
}
