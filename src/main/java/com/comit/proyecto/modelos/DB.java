package com.comit.proyecto.modelos;

import java.sql.Connection;
import java.sql.DriverManager;

import com.vaadin.flow.component.notification.Notification;

public class DB {
	
	
	public static Connection conexion;
	
	public static Connection initDB() {
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "proyectoJava";
		String userName = "root";
		String password = "12345";
		

		
		try{
		   
			Class.forName(driver);
		
			conexion = DriverManager.getConnection(url + dbName, userName, password);

		   if(!conexion.isClosed()) {
			   
				return conexion;
		   } else {
			   
		   }
	
		} catch (Exception e) {
		   
			//e.printStack()
		}

		return conexion;
		

	}

}
