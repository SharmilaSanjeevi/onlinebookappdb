
/*Author @sharmila
 * version 0.1
 * 
 */
package com.bookapp.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ModelDAO {

	static Connection connection = null;

	public static Connection openConnection() {

		Properties properties = new Properties();
		try {
			properties.load(new FileReader("jdbc.properties"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		String url = (String) properties.getProperty("driver");
		String username = (String) properties.getProperty("username");
		String password = (String) properties.getProperty("password");

		try {
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
