package com.saubiette.proyecto.vistas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.EstablecimientoController;
import com.saubiette.proyecto.controladores.TipoEstController;
import com.saubiette.proyecto.entidades.Establecimiento;
import com.saubiette.proyecto.entidades.TipoEstablecimiento;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Component
@Route("establecimientos/alta")
@CssImport("styles/styles.css")
@PreserveOnRefresh
public class AltaEstablecimientoView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	EstablecimientoController establecimientoController;
	TipoEstController tipoEstController;

	TextField direccion = new TextField("Nombre: ");
	TextField nombre = new TextField("Dirección: ");
	Span titulo = new Span("Alta de establecimiento");
	Span error = new Span();
	Div div = new Div();

	ComboBox<TipoEstablecimiento> tipo = new ComboBox<TipoEstablecimiento>("Tipo:");

	List<Establecimiento> listEstablecimientos;
	List<TipoEstablecimiento> listTipoEst;

	Button btnCrear;

	Binder<Establecimiento> binder;

	private Establecimiento establecimiento;

	@Autowired
	public AltaEstablecimientoView(EstablecimientoController establecimientoController,
			TipoEstController tipoEstController) {

		this.establecimientoController = establecimientoController;
		this.tipoEstController = tipoEstController;

		init();
		configBinder();

	}

	public void init() {
		establecimiento = new Establecimiento();

		tipo = crearComboTipos();

		error.addClassName("error");
		titulo.addClassName("titulo");
		div.addClassName("contenedorForm");

		btnCrear = new Button("Crear establecimiento");

		btnCrear.addClickListener(listener -> guardar());

		div.add(titulo);
		div.add(nombre);
		div.add(direccion);
		div.add(tipo);
		div.add(btnCrear);
		div.add(error);
		Menu menu = new Menu();
		add(menu, div);

	}

	private void configBinder() {
		binder = new Binder<>(Establecimiento.class);

		binder.bindInstanceFields(this);

		binder.forField(nombre).asRequired("El campo Nombre es requerido").bind("nombre");
		binder.forField(direccion).asRequired("El campo Direccion es requerido").bind("direccion");
		binder.forField(tipo).asRequired("El campo Tipo es requerido").bind("tipo");

		binder.setBean(establecimiento);

	}

	private ComboBox<TipoEstablecimiento> crearComboTipos() {
		List<TipoEstablecimiento> listTipoEst = (List<TipoEstablecimiento>) tipoEstController.traerTipos();
		ComboBox<TipoEstablecimiento> comboTipo = new ComboBox<TipoEstablecimiento>();
		comboTipo.setLabel("Tipo de establecimiento");
		comboTipo.setItemLabelGenerator(TipoEstablecimiento::getNombre);
		comboTipo.setItems(listTipoEst);
		return comboTipo;
	}

	public void guardar() {

		error.setText("");
		try {
			binder.writeBean(establecimiento);
			establecimientoController.crearEstablecimiento(establecimiento);
			Notification.show("El registro fue dado de alta satisfactoriamente", 3000, Position.TOP_CENTER)
					.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

		} catch (ValidationException ex) {
			Notification.show("El registro no pudo guardarse", 3000, Position.TOP_CENTER)
					.addThemeVariants(NotificationVariant.LUMO_ERROR);
			ex.printStackTrace();
			error.setText(ex.getMessage());
		}

	}

}