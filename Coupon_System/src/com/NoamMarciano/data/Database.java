package com.NoamMarciano.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
	private static Connection connection;
	private static final String url = "jdbc:mysql://localhost:3306/coupon_system?createDatabaseIfNotExist=TRUE&useTimezone=TRUE&serverTimezone=UTC";
	private static final String username = "root";
	private static final String password = "1234";

	public static String getUrl() {
		return url;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

	private static final String CREATE_SCHEMA = "CREATE SCHEMA `coupon_system` ;";
	private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE `coupon_system`.`categories` (\r\n"
			+ "  `ID` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `Name` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`ID`))";
	private static final String CREATE_TABLE_COMPANIES = "CREATE TABLE `coupon_system`.`companies` (\r\n"
			+ "  `ID` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `Name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `Email` VARCHAR(45) NOT NULL,\r\n" + "  `Password` VARCHAR(45) NOT NULL,\r\n"
			+ "  PRIMARY KEY (`ID`))";
	private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE `coupon_system`.`customers` (\r\n"
			+ "  `ID` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `First_Name` VARCHAR(45) NOT NULL,\r\n"
			+ "  `Last_Name` VARCHAR(45) NOT NULL,\r\n" + "  `Email` VARCHAR(45) NOT NULL,\r\n"
			+ "  `Password` VARCHAR(45) NULL,\r\n" + "  PRIMARY KEY (`ID`))";
	private static final String CREATE_TABLE_COUPONS = "CREATE TABLE `coupon_system`.`coupons` (\r\n"
			+ "  `ID` INT NOT NULL AUTO_INCREMENT,\r\n" + "  `Company_ID` INT NOT NULL,\r\n"
			+ "  `Category_ID` INT NOT NULL,\r\n" + "  `Title` VARCHAR(45) NOT NULL,\r\n"
			+ "  `Description` VARCHAR(45) NOT NULL,\r\n" + "  `Start_Date` DATE NOT NULL,\r\n"
			+ "  `End_Date` DATE NOT NULL,\r\n" + "  `Amount` INT NOT NULL,\r\n" + "  `Price` DOUBLE NOT NULL,\r\n"
			+ "  `Image` VARCHAR(45) NULL,\r\n" + "  PRIMARY KEY (`ID`))";
	private static final String CREATE_TABLE_CUSTOMERS_VS_COUPONS = "CREATE TABLE `coupon_system`.`customers_vs_coupons` (\r\n"
			+ "  `Customer_ID` INT NOT NULL,\r\n" + "  `Coupon_ID` INT NOT NULL,\r\n"
			+ "  PRIMARY KEY (`Customer_ID`, `Coupon_ID`),\r\n"
			+ "  INDEX `Coupon_ID_idx` (`Coupon_ID` ASC) VISIBLE,\r\n" + "  CONSTRAINT `Customer_ID`\r\n"
			+ "    FOREIGN KEY (`Customer_ID`)\r\n" + "    REFERENCES `coupon_system`.`customers` (`ID`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION,\r\n" + "  CONSTRAINT `Coupon_ID`\r\n"
			+ "    FOREIGN KEY (`Coupon_ID`)\r\n" + "    REFERENCES `coupon_system`.`coupons` (`ID`)\r\n"
			+ "    ON DELETE NO ACTION\r\n" + "    ON UPDATE NO ACTION)";

	private static final String DROP_TABLE_CUSTOMERS_VS_COUPONS = "DROP TABLE `coupon_system`.`customers_vs_coupons`;";
	private static final String DROP_TABLE_COUPONS = "DROP TABLE `coupon_system`.`coupons`;";
	private static final String DROP_TABLE_CUSTOMERS = "DROP TABLE `coupon_system`.`customers`;";
	private static final String DROP_TABLE_COMPANIES = "DROP TABLE `coupon_system`.`companies`;";
	private static final String DROP_TABLE_CATEGORIES = "DROP TABLE `coupon_system`.`categories`;";
	private static final String DROP_SCHEMA = "DROP DATABASE `coupon_system`;";

	private static final String INSERT_TABLE_CATEGORIES_FOOD = "INSERT INTO `coupon_system`.`categories` (`Name`) VALUES ('Food');";
	private static final String INSERT_TABLE_CATEGORIES_ELECTRICITY = "INSERT INTO `coupon_system`.`categories` (`Name`) VALUES ('Electricity');";
	private static final String INSERT_TABLE_CATEGORIES_RESTAURANT = "INSERT INTO `coupon_system`.`categories` (`Name`) VALUES ('Restaurant');";
	private static final String INSERT_TABLE_CATEGORIES_VACATION = "INSERT INTO `coupon_system`.`categories` (`Name`) VALUES ('Vacation');";

	public static void createAllTabales() throws SQLException {
		createSchema();
		createTableCategories();
		createTableCompanies();
		createTableCustomers();
		createTableCoupons();
		createTableCustomers_vs_coupons();
	}

	private static void createSchema() {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_SCHEMA;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	private static void createTableCategories() throws SQLException {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_TABLE_CATEGORIES;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

			sql = INSERT_TABLE_CATEGORIES_FOOD;
			statment = connection.prepareStatement(sql);
			statment.executeUpdate();

			sql = INSERT_TABLE_CATEGORIES_ELECTRICITY;
			statment = connection.prepareStatement(sql);
			statment.executeUpdate();

			sql = INSERT_TABLE_CATEGORIES_RESTAURANT;
			statment = connection.prepareStatement(sql);
			statment.executeUpdate();

			sql = INSERT_TABLE_CATEGORIES_VACATION;
			statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	private static void createTableCompanies() throws SQLException {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_TABLE_COMPANIES;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	private static void createTableCustomers() throws SQLException {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_TABLE_CUSTOMERS;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	private static void createTableCoupons() throws SQLException {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_TABLE_COUPONS;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	private static void createTableCustomers_vs_coupons() throws SQLException {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_TABLE_CUSTOMERS_VS_COUPONS;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	public static void dropAllTabales() throws SQLException {
		dropTableCustomers_vs_coupons();
		dropTableCoupons();
		dropTableCustomers();
		dropTableCompanies();
		dropTableCategories();
		dropSchema();
	}

	private static void dropTableCustomers_vs_coupons() throws SQLException {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DROP_TABLE_CUSTOMERS_VS_COUPONS;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	private static void dropTableCoupons() throws SQLException {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DROP_TABLE_COUPONS;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	private static void dropTableCustomers() throws SQLException {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DROP_TABLE_CUSTOMERS;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	private static void dropTableCompanies() throws SQLException {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DROP_TABLE_COMPANIES;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	private static void dropTableCategories() throws SQLException {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DROP_TABLE_CATEGORIES;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}

	private static void dropSchema() {
		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = DROP_SCHEMA;

			// STEP 3
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// STEP 5
			ConnectionPool.getInstance().returnConnection(connection);
			connection = null;
		}

	}


}
