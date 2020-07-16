package com.NoamMarciano.main;

import java.sql.SQLException;

import com.NoamMarciano.data.ConnectionPool;
import com.NoamMarciano.data.Database;

public class Test {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
		System.out.println("START");
		ConnectionPool.getInstance();
		// STEP 1 - Loading JDBC Driver - Of MySQL
		Class.forName("com.mysql.cj.jdbc.Driver");

		Database.dropAllTabales();
		Database.createAllTabales();

		try {
			TestAll.doTheMagic();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		ConnectionPool.getInstance().closeAllConnection();
		System.out.println("END");

	}
}
