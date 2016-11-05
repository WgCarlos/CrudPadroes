package br.com.crudPadroes.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import com.mysql.jdbc.Driver;



public class ConnectionFactory {
	
private static String STR_CONEXAO = "jdbc:mysql://localhost/pessoas";
private static String USUARIO = "root";
private static String SENHA = "";

	public static  Connection getConnection() {
		try {
			return DriverManager.getConnection(STR_CONEXAO, USUARIO, SENHA);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
