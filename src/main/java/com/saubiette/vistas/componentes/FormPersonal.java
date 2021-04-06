package com.saubiette.vistas.componentes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.saubiette.proyecto.controladores.AreaController;
import com.saubiette.proyecto.controladores.EstablecimientoController;
import com.saubiette.proyecto.controladores.PersonalController;
import com.saubiette.proyecto.entidades.Area;
import com.saubiette.proyecto.entidades.Establecimiento;
import com.saubiette.proyecto.entidades.Personal;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

public class FormPersonal extends VerticalLayout {

	ComboBox<Establecimiento> comboEstablecimientos;

	ComboBox<Area> comboAreas;

	TextField txtNombre;
	TextField txtApellido;
	TextField txtLegajo;
	TextField txtDireccion;
	TextField txtDni;

	Button crear;

	@Autowired
	EstablecimientoController establecimientoController;

	@Autowired
	AreaController areaController;

	@Autowired
	PersonalController personalController;

	int idPersonal = 0;

	Binder<Personal> binder;

	Personal personal;

	Div contenedor;

	private static final long serialVersionUID = 1L;

	public FormPersonal(PersonalController personalController, AreaController areaController,
			EstablecimientoController establecimientoController, int idPersonal) {

		this.establecimientoController = establecimientoController;
		this.areaController = areaController;
		this.personalController = personalController;

		this.comboAreas = crearComboAreas();
		this.comboEstablecimientos = crearComboEstablecimientos();

		this.idPersonal = idPersonal;

		init();

		configBinder();

	}

	private void init() {
		txtNombre = new TextField("Nombre");
		txtApellido = new TextField("Apellido");
		txtLegajo = new TextField("Legajo");
		txtDireccion = new TextField("Direccion");
		txtDni = new TextField("DNI");

		if (idPersonal == 0) {
			// titulo.setText("Editar personal");
			crear = new Button("Crear");
		} else {
			// titulo.setText("Editar establecimiento");
			crear = new Button("Editar");

			try {
				personal = personalController.traerPersonal(idPersonal);

				System.out.println(personal.toString());
				txtLegajo.setValue(personal.getLegajo());
				txtNombre.setValue(personal.getNombre());
				txtApellido.setValue(personal.getApellido());
				txtDni.setValue(personal.getDni());
				txtDireccion.setValue(personal.getDireccion());
				comboAreas.setValue(personal.getArea());
				comboEstablecimientos.setValue(personal.getEstablecimiento());

			} catch (NullPointerException e) {
				e.getMessage();
			}
		}

		crear.addClickListener(listener -> guardar());

		contenedor = new Div();

		contenedor.addClassName("contenedorForm");
		contenedor.add(txtLegajo, txtNombre, txtApellido, txtDni, txtDireccion, comboAreas, comboEstablecimientos,
				crear);

		add(contenedor);
	}

	private void configBinder() {

		binder = new Binder<>(Personal.class);

		binder.forField(txtNombre).asRequired("El campo Nombre es requerido").bind("nombre");
		binder.forField(txtApellido).asRequired("El campo Nombre es requerido").bind("apellido");
		binder.forField(txtDireccion).asRequired("El campo Direccion es requerido").bind("direccion");
		binder.forField(txtDni).asRequired("El campo Dni es requerido").bind("dni");
		binder.forField(txtLegajo).asRequired("El campo Dni es requerido").bind("legajo");
		binder.forField(comboAreas).asRequired("El campo Area es requerido").bind("area");
		binder.forField(comboEstablecimientos).asRequired("El campo Area es requerido").bind("establecimiento");

		binder.bindInstanceFields(this);

		binder.setBean(personal);

	}

	private ComboBox<Establecimiento> crearComboEstablecimientos() {
		List<Establecimiento> listEstablecimiento = (List<Establecimiento>) establecimientoController
				.traerEstablecimientos();
		ComboBox<Establecimiento> comboEstablecimiento = new ComboBox<Establecimiento>();
		comboEstablecimiento.setLabel("Establecimiento");
		comboEstablecimiento.setItemLabelGenerator(Establecimiento::getNombre);
		comboEstablecimiento.setItems(listEstablecimiento);
		return comboEstablecimiento;
	}

	private ComboBox<Area> crearComboAreas() {
		List<Area> listAreas = (List<Area>) areaController.traerAreas();
		ComboBox<Area> comboArea = new ComboBox<Area>();
		comboArea.setLabel("Areas");
		comboArea.setItemLabelGenerator(Area::getArea);
		comboArea.setItems(listAreas);
		return comboArea;
	}

	private void guardar() {
		try {
			String msg = "";

			if (idPersonal != 0) {
				msg = "El registro fue editado satisfactoriamente";
			} else {
				msg = "El registro fue dado de alta satisfactoriamente";
			}
			binder.writeBean(personal);
			personalController.crearPersonal(personal);
			Notification.show(msg, 3000, Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
			Thread.sleep(1500);
			UI.getCurrent().navigate("personal");

		} catch (ValidationException ex) {
			Notification.show("El registro no pudo guardarse", 3000, Position.TOP_CENTER)
					.addThemeVariants(NotificationVariant.LUMO_ERROR);
			ex.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
