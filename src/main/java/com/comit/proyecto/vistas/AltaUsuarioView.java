package com.comit.proyecto.vistas;

import com.comit.proyecto.controladores.UsuarioController;
import com.comit.proyecto.entidades.Usuario;
import com.comit.proyecto.vistas.componentes.Menu;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("altaUsuario")
public class AltaUsuarioView extends VerticalLayout{

	Button btnCrear;
	
	TextField txtEmail;
	TextField txtClave;
	TextField txtNombre;
	TextField txtApellido;
	TextField txtDni;
	TextField txtObra_social;
	TextField txtNumero_socio;
	
	Div formUsuario;
	
	Menu menu;
	
	public AltaUsuarioView() {
		
		setElements();		
		
		setListener();
		
		
		add(menu,txtEmail,txtClave,txtNombre,txtApellido,txtDni,txtObra_social,txtNumero_socio, btnCrear);
		
		//add(menu, formUsuario);
	}
	
	
	public void setElements() {
		menu = new Menu();
		
		formUsuario = new Div();
		
		txtEmail = new TextField("Email: ");
		txtClave = new TextField("Clave: ");
		
		txtNombre = new TextField("Nombre: ");
		
		txtApellido = new TextField("Apellido: ");
		
		txtDni = new TextField("Dni: ");
		
		txtObra_social = new TextField("Obra social: ");
		
		txtNumero_socio = new TextField("Numero de afiliado: ");
		
		btnCrear = new Button("Crear usuario");
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
			usuario.setId_rol(1);
			
			UsuarioController userController = new UsuarioController();
			UsuarioController.userRepository.save(usuario);
		});
	}
}
