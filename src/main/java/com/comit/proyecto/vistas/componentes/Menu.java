package com.comit.proyecto.vistas.componentes;


import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;

@CssImport("./styles/styles.css")
@Tag("menu")
public class Menu extends Div{
	
	public Menu() {
		
		
		Div contenedor = new Div();
		contenedor.addClassName("contenedor");
		
		Div menu = new Div();
		menu.addClassName("menu");
		
		Anchor itemEstablecimiento = new Anchor("/establecimientos", "Establecimientos");
		itemEstablecimiento.getElement().getClassList().add("menuItem");
			
		
		
		Anchor itemProfesionales = new Anchor("https://vaadin.com","Profesionales");
		itemProfesionales.getElement().getClassList().add("menuItem");
		
		
		
		Anchor itemIngresar = new Anchor("/login","Ingresar");
		itemIngresar.getElement().getClassList().add("menuItem");
		
		menu.add(itemIngresar);
		menu.add(itemProfesionales);
		menu.add(itemEstablecimiento);
		
		contenedor.add(menu);
		
		add(contenedor);
	}
}
