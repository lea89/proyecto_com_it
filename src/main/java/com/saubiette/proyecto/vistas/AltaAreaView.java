package com.saubiette.proyecto.vistas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.AreaController;
import com.saubiette.proyecto.controladores.EstablecimientoController;
import com.saubiette.proyecto.entidades.Area;
import com.saubiette.proyecto.entidades.Establecimiento;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.UI;
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
@Route("areas/alta")
@CssImport("styles/styles.css")
@PreserveOnRefresh
public class AltaAreaView extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	AreaController areaController;
	EstablecimientoController establecimientoController;

	TextField txtArea = new TextField("Area");

	ComboBox<Establecimiento> establecimientos;

	Button btnCrear = new Button("Crear area");

	Binder<Area> binder;

	Area area;

	Span titulo = new Span();

	@Autowired
	public AltaAreaView(AreaController areaController, EstablecimientoController establecimientoController) {
		this.areaController = areaController;
		this.establecimientoController = establecimientoController;
		Menu menu = new Menu();

		btnCrear.addClickListener(e -> guardar());

		titulo.addClassName("titulo");
		titulo.setText("Alta area");
		establecimientos = crearComboEstablecimientos();

		configureBinder();

		Div contenedor = new Div();

		contenedor.addClassName("contenedorForm");
		contenedor.add(titulo, txtArea, establecimientos, btnCrear);

		add(menu, contenedor);
	}

	public void guardar() {

		try {

			binder.writeBean(area);
			areaController.crearArea(area);
			Notification.show("El registro fue editado satisfactoriamente", 3000, Position.TOP_CENTER)
					.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
			Thread.sleep(1500);
			UI.getCurrent().navigate("areas");

		} catch (ValidationException ex) {
			Notification.show("El registro no pudo guardarse", 3000, Position.TOP_CENTER)
					.addThemeVariants(NotificationVariant.LUMO_ERROR);
			ex.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private ComboBox<Establecimiento> crearComboEstablecimientos() {
		List<Establecimiento> listEstablecimiento = (List<Establecimiento>) establecimientoController
				.traerEstablecimientos();
		ComboBox<Establecimiento> comboTipo = new ComboBox<Establecimiento>();
		comboTipo.setLabel("Establecimiento");
		comboTipo.setItemLabelGenerator(Establecimiento::getNombre);
		comboTipo.setItems(listEstablecimiento);
		return comboTipo;
	}

	private void configureBinder() {
		binder = new Binder<>(Area.class);
		area = new Area();

		binder.forField(txtArea).asRequired("El campo Nombre es requerido").bind("area");

		binder.forField(establecimientos).asRequired("El campo Tipo es requerido").bind("establecimiento");

		binder.setBean(area);
		binder.bindInstanceFields(this);
	}
}
