package com.saubiette.proyecto.vistas;

import java.util.ArrayList;
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

	@Autowired
	EstablecimientoController establecimientoController;

	@Autowired
	TipoEstController tipoEstController;

	TextField txtNombre;
	TextField txtDireccion;
	Span titulo;
	Span error;
	Div div;

	ComboBox<TipoEstablecimiento> comboTipo;

	List<Establecimiento> listEstablecimientos;
	List<TipoEstablecimiento> listTipoEst;

	Button btnCrear;

	Binder<Establecimiento> binder;

	public AltaEstablecimientoView(EstablecimientoController establecimientoController,
			TipoEstController tipoEstController) {

		this.establecimientoController = establecimientoController;
		this.tipoEstController = tipoEstController;

		Menu menu = new Menu();

		setElements();
		setValueElements();
		setListeners();

		div = new Div();

		div.addClassName("contenedorForm");
		div.add(titulo);
		div.add(txtNombre);
		div.add(txtDireccion);
		div.add(comboTipo);
		div.add(btnCrear);
		div.add(error);

		add(menu, div);
	}

	public void setElements() {
		titulo = new Span("Alta de establecimiento");
		titulo.addClassName("titulo");

		error = new Span();
		error.addClassName("error");
		txtNombre = new TextField("Nombre:");
		txtDireccion = new TextField("Direccion:");

		listEstablecimientos = new ArrayList<Establecimiento>();

		listTipoEst = new ArrayList<TipoEstablecimiento>();

		comboTipo = new ComboBox<TipoEstablecimiento>();
		btnCrear = new Button("Crear establecimiento");

		binder = new Binder<>(Establecimiento.class);

		binder.forField(txtNombre).asRequired().bind(Establecimiento::getNombre, Establecimiento::setNombre);
		binder.forField(txtDireccion).asRequired().bind(Establecimiento::getDireccion, Establecimiento::setDireccion);
		binder.forField(comboTipo).asRequired().bind(Establecimiento::getTipo, Establecimiento::setTipo);

	}

	public void setValueElements() {

		// establecimientoController.traerEstablecimientos().forEach(listEstablecimientos::add);

		if (tipoEstController.traerTipos() != null) {
			tipoEstController.traerTipos().forEach(x -> {
				listTipoEst.add(x);
			});
		}

		comboTipo.setLabel("Tipo de establecimiento");
		comboTipo.setItemLabelGenerator(TipoEstablecimiento::getNombre);
		comboTipo.setItems(listTipoEst);
	}

	public void setListeners() {
		btnCrear.addClickListener(e -> {

			error.setText("");

			Establecimiento est = new Establecimiento();

			binder.validate();
			if (binder.isValid()) {
				try {
					binder.writeBean(est);
					establecimientoController.crearEstablecimiento(est);

				} catch (ValidationException ex) {
					error.setText(ex.getMessage());
				}

			} else {
				System.out.println(binder.validate().getBeanValidationErrors());
				error.setText("Por favor verifique los campos e intente nuevamente.");
			}

		});
	}
}
