package com.NoamMarciano.main;

import java.sql.SQLException;

import com.NoamMarciano.data.ConnectionPool;
import com.NoamMarciano.data.Database;
import com.NoamMarciano.job.DailyJob;

public class Test {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
		System.out.println("START");
		DailyJob t1 = new DailyJob();
		
		ConnectionPool.getInstance();

		// STEP 1 - Loading JDBC Driver - Of MySQL
		Class.forName("com.mysql.cj.jdbc.Driver");

		try {
			t1.start();
			Database.dropAllTabales();
			Database.createAllTabales();
			TestAll.doTheMagic();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		t1.stopJob();
		ConnectionPool.getInstance().closeAllConnection();
		System.out.println("END");

	}
}
