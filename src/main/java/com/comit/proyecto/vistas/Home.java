package com.comit.proyecto.vistas;

import com.comit.proyecto.GreetService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)

@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@CssImport("./styles/styles.css")
public class Home extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public Home(@Autowired GreetService service) {

//        // Use TextField for standard text input
//        TextField textField = new TextField("Your name");
//        textField.addThemeName("bordered");
//
//        // Button click listeners can be defined as lambda expressions
//        Button button = new Button("Say hello",
//                e -> Notification.show(service.greet(textField.getValue())));
//
//        // Theme variants give you predefined extra styles for components.
//        // Example: Primary button has a more prominent look.
//        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//
//        // You can specify keyboard shortcuts for buttons.
//        // Example: Pressing enter in this view clicks the Button.
//        button.addClickShortcut(Key.ENTER);
//
//        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
//        addClassName("centered-content");
//
//        add(textField, button);
    	Div contenedor = new Div();
    	contenedor.addClassName("contenedor");
    	
    	Div menu = new Div();
    	menu.addClassName("menu");
    	
    	Anchor itemEstablecimiento = new Anchor("https://vaadin.com", "Establecimientos");
    	itemEstablecimiento.getElement().getClassList().add("menuItem");
    	
    		
    	
    	
    	Anchor itemProfesionales = new Anchor("https://vaadin.com","Profesionales");
    	itemProfesionales.getElement().getClassList().add("menuItem");
    	
    	
    	
    	Anchor itemIngresar = new Anchor("https://vaadin.com","Ingresar");
    	itemIngresar.getElement().getClassList().add("menuItem");
    	
    	menu.add(itemIngresar);
    	menu.add(itemProfesionales);
    	menu.add(itemEstablecimiento);
    	
    	contenedor.add(menu);
    	add(contenedor);
    	
    	
    	
    }

}

