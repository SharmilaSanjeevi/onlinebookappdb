package com.bookapp.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ModelDAO {

	static Connection connection;

	public static Connection openConnection() {

		Properties properties = new Properties();
		try {
			properties.load(new FileReader("jdbc.properties"));

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String url = (String) properties.getProperty("driver");
		String username = (String) properties.getProperty("username");
		String password = (String) properties.getProperty("password");
		//String sql = "create table book(title varchar(80),author varchar(80),category varchar(50),bookid integer,price integer)";

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);


		} catch (Exception e) {
			e.printStackTrace();

		}
		return connection;
	}
		
		public static void closeConnection() {
			
			try {

				if (connection != null)
					connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
