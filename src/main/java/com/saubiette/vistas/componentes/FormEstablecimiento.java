package com.saubiette.vistas.componentes;

import java.util.List;

import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.EstablecimientoController;
import com.saubiette.proyecto.controladores.TipoEstController;
import com.saubiette.proyecto.entidades.Establecimiento;
import com.saubiette.proyecto.entidades.TipoEstablecimiento;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;

@Component
public class FormEstablecimiento extends VerticalLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TextField direccion = new TextField("Direccion: ");
	TextField nombre = new TextField("Nombre: ");

	Span titulo = new Span();
	Span error = new Span();

	Div div = new Div();

	EstablecimientoController establecimientoController;
	TipoEstController tipoEstController;

	ComboBox<TipoEstablecimiento> tipo = new ComboBox<TipoEstablecimiento>("Tipo:");

	List<Establecimiento> listEstablecimientos;
	List<TipoEstablecimiento> listTipoEst;

	Button btnCrear;

	Binder<Establecimiento> binder;

	private Establecimiento establecimiento;

	boolean editar = false;

	int idEstablecimiento;

	public FormEstablecimiento(EstablecimientoController establecimientoController, TipoEstController tipoEstController,
			boolean editar, int idEstablecimiento) {

		this.establecimientoController = establecimientoController;
		this.tipoEstController = tipoEstController;
		this.editar = editar;
		this.idEstablecimiento = idEstablecimiento;

		init();
		configBinder();
	}

	public void init() {

		tipo = crearComboTipos();

		error.addClassName("error");
		titulo.addClassName("titulo");
		div.addClassName("contenedorForm");

		if (!editar) {
			titulo.setText("Alta establecimiento");
			btnCrear = new Button("Crear");
		} else {
			titulo.setText("Editar establecimiento");
			btnCrear = new Button("Editar");

			try {
				establecimiento = establecimientoController.traerEstablecimiento(idEstablecimiento);
				nombre.setValue(establecimiento.getNombre());
				direccion.setValue(establecimiento.getDireccion());
				tipo.setValue(establecimiento.getTipo());

			} catch (NullPointerException e) {
				e.getMessage();
			}
		}
		btnCrear.addClickListener(listener -> guardar());

		div.add(titulo);
		div.add(nombre);
		div.add(direccion);
		div.add(tipo);
		div.add(btnCrear);

		add(div);

	}

	private void configBinder() {

		binder = new Binder<>(Establecimiento.class);
		establecimiento = new Establecimiento();

		binder.bindInstanceFields(this);

		binder.forField(nombre).asRequired("El campo Nombre es requerido").bind("nombre");
		binder.forField(direccion).asRequired("El campo Direccion es requerido").bind("direccion");
		binder.forField(tipo).asRequired("El campo Tipo es requerido").bind("tipo");

		binder.setBean(establecimiento);

	}

	public void guardar() {

		error.setText("");
		try {
			String msg = "";

			if (editar) {
				msg = "El registro fue editado satisfactoriamente";
			} else {
				msg = "El registro fue dado de alta satisfactoriamente";
			}
			binder.writeBean(establecimiento);
			establecimientoController.crearEstablecimiento(establecimiento);
			Notification.show(msg, 3000, Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
			Thread.sleep(1500);
			UI.getCurrent().navigate("establecimientos");

		} catch (ValidationException ex) {
			Notification.show("El registro no pudo guardarse", 3000, Position.TOP_CENTER)
					.addThemeVariants(NotificationVariant.LUMO_ERROR);
			ex.printStackTrace();
			error.setText(ex.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private ComboBox<TipoEstablecimiento> crearComboTipos() {
		List<TipoEstablecimiento> listTipoEst = (List<TipoEstablecimiento>) tipoEstController.traerTipos();
		ComboBox<TipoEstablecimiento> comboTipo = new ComboBox<TipoEstablecimiento>();
		comboTipo.setLabel("Tipo de establecimiento");
		comboTipo.setItemLabelGenerator(TipoEstablecimiento::getNombre);
		comboTipo.setItems(listTipoEst);
		return comboTipo;
	}

}
