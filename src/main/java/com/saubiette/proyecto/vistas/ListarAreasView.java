package com.saubiette.proyecto.vistas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saubiette.proyecto.controladores.AreaController;
import com.saubiette.proyecto.controladores.EstablecimientoController;
import com.saubiette.proyecto.entidades.Area;
import com.saubiette.proyecto.entidades.Establecimiento;
import com.saubiette.vistas.componentes.Menu;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Shortcuts;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@Component
@Route("areas")
@CssImport("styles/styles.css")
@PreserveOnRefresh
public class ListarAreasView extends VerticalLayout implements BeforeEnterObserver {

	private static final long serialVersionUID = 1L;

	AreaController areaController;
	EstablecimientoController establecimientoController;

	Iterable<Area> areaList;
	List<Area> result;
	Div contenedor;
	Menu menu;
	Grid<Area> grid;

	Dialog dialog;
	TextField txtArea;

	Area area;
	ComboBox<Establecimiento> establecimientos;
	Binder<Area> binder;

	@Autowired
	public ListarAreasView(AreaController areaController, EstablecimientoController establecimientoController) {
		this.areaController = areaController;
		this.establecimientoController = establecimientoController;

		init();
		configureBinder();
	}

	private void init() {

		contenedor = new Div();
		contenedor.addClassName("contenedor");

		result = new ArrayList<Area>();

		menu = new Menu();

		grid = new Grid<Area>();

		grid.addColumn(Area::getArea).setHeader("Nombre");

		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS,
				GridVariant.LUMO_ROW_STRIPES);

		grid.addColumn((area) -> {
			return area.getEstablecimiento().getNombre();
		}).setHeader("Establecimiento");

		grid.addComponentColumn(area -> {
			Div div = new Div();

			Icon eliminar = VaadinIcon.CLOSE.create();

			eliminar.setColor("red");

			eliminar.addClickListener(e -> setDialogElimina(area));

			Icon editar = new Icon(VaadinIcon.EDIT);

			editar.addClickListener(e -> setDialogEdita(area));

			div.add(editar, eliminar);

			return div;

		});

		contenedor.add(grid);

		loadData();

		add(menu, contenedor);
	}

	private void setDialogElimina(Area area) {

		Div divButton = new Div();
		Div div = new Div();

		dialog = new Dialog();
		dialog.setCloseOnEsc(false);
		dialog.setCloseOnOutsideClick(false);

		Span titulo = new Span("Eliminar area");

		Button confirmar = new Button("Confirmar", event -> {
			areaController.eliminarArea(area);

			result = new ArrayList<Area>();

			grid.setItems(result);

			loadData();

			dialog.close();
		});

		confirmar.addClassName("confirmar");

		Button cancelar = new Button("Cancelar", event -> {

			dialog.close();
		});
		// Cancel action on ESC press
		Shortcuts.addShortcutListener(dialog, () -> {
			dialog.close();
		}, Key.ESCAPE);

		cancelar.addClassName("cancelar");

		divButton.add(confirmar, cancelar);

		div.addClassName("dialog-elimina");
		div.add(titulo, new Span("Desea eliminar el area: " + area.getArea() + "?"));
		dialog.add(div, divButton);
		dialog.open();
	}

	private void setDialogEdita(Area area) {

		dialog = new Dialog();
		Div div = new Div();
		Div divButton = new Div();

		div.addClassName("dialog-edita");
		Text titulo = new Text("Editar area");

		txtArea.focus();
		txtArea.setValue(area.getArea());

		establecimientos.setValue(area.getEstablecimiento());

		dialog.add(txtArea);

		Button confirmar = new Button("Confirmar", e -> {
			area.setArea(txtArea.getValue());
			areaController.actualizarRol(area);
			dialog.close();
			grid.getDataProvider().refreshItem(area);
		});
		confirmar.addClassName("confirmar");

		Button cancelar = new Button("Cancelar", e -> {
			dialog.close();
		});

		cancelar.addClassName("cancelar");

		divButton.add(confirmar, cancelar);
		div.add(titulo, txtArea, establecimientos, divButton);
		div.addClassName("dialog-edita");
		dialog.add(div);

		dialog.open();

	}

	private void loadData() {

		areaList = areaController.traerAreas();
		areaList.forEach(result::add);

		grid.setItems(result);

		grid.getDataProvider().refreshAll();
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
		txtArea = new TextField("Ingrese el area");
		establecimientos = crearComboEstablecimientos();
		binder.forField(txtArea).asRequired("El campo Nombre es requerido").bind("area");

		binder.forField(establecimientos).asRequired("El campo Tipo es requerido").bind("establecimiento");

		binder.setBean(area);
		binder.bindInstanceFields(this);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {

		result = new ArrayList<Area>();

		grid.setItems(result);

		loadData();
	}
}
