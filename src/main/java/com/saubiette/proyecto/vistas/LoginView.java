package com.saubiette.proyecto.vistas;

import org.springframework.beans.factory.annotation.Autowired;

import com.saubiette.proyecto.controladores.UsuarioController;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ingresar")
@CssImport("styles/login.css")
public class LoginView extends VerticalLayout {

	Button btnIngresar;
	@Autowired
	UsuarioController userController;

	/*
	 * public LoginView() {
	 * 
	 * Div form = new Div();
	 * 
	 * form.addClassName("form");
	 * 
	 * TextField txtUsuario = new TextField();
	 * 
	 * txtUsuario.setLabel("Usuario: ");
	 * txtUsuario.setPlaceholder("Ingrese su nombre de usuario");
	 * txtUsuario.setRequired(true);
	 * txtUsuario.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
	 * 
	 * PasswordField txtClave = new PasswordField();
	 * 
	 * txtClave.setLabel("Clave: "); txtClave.setPlaceholder("Ingrese su clave");
	 * txtClave.setRequired(true);
	 * txtClave.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
	 * 
	 * btnIngresar = new Button("Ingresar");
	 * 
	 * btnIngresar.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
	 * ButtonVariant.LUMO_SUCCESS);
	 * 
	 * Div label = new Div();
	 * 
	 * label.addClassName("responseLogin");
	 * 
	 * form.add(txtUsuario, txtClave, btnIngresar, label);
	 * 
	 * btnIngresar.addClickListener(e -> {
	 * 
	 * boolean res = login(txtUsuario.getValue(), txtClave.getValue());
	 * 
	 * if (res) { // label.setText("Usuario y clave correctos");
	 * UI.getCurrent().navigate(""); } else {
	 * label.setText("Usuario y clave INCORRECTOS"); } });
	 * 
	 * LoginForm component = new LoginForm(); component.addLoginListener(e -> {
	 * AuthenticationResponse isAuthenticated; try { isAuthenticated =
	 * userController .createToken(new AuthenticationRequest(txtUsuario.getValue(),
	 * txtClave.getValue())); if (isAuthenticated.getToken() != null) {
	 * UI.getCurrent().navigate("/establecimientos"); } else {
	 * component.setError(true); } } catch (Exception e1) { // TODO Auto-generated
	 * catch block e1.printStackTrace(); }
	 * 
	 * });
	 * 
	 * add(component); }
	 */

}
