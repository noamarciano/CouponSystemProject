package com.NoamMarciano.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.dao.CustomerDAO;
import com.NoamMarciano.utils.ConnectionPool;

public class CustomerDBDAO implements CustomerDAO {
	private static Connection connection = null;

	private static final String CREATE_METHOD_IS_CUSTOMER_EXISTS = "SELECT * FROM `coupon_system`.`customers` WHERE email = ? AND password = ?";
	private static final String CREATE_METHOD_ADD_CUSTOMER = "INSERT INTO `coupon_system`.`customers` (`First_Name`, `Last_Name`, `Email`, `Password`) VALUES (?, ?, ?, ?);";

	private static final String CREATE_METHOD_UPDATE_CUSTOMER = "UPDATE `coupon_system`.`customers` SET `First_Name`=?,"
			+ " `Last_Name`=?, `Email`=?, `Password`=? WHERE `ID`=?";

	private static final String CREATE_METHOD_DELETE_CUSTOMER = "DELETE FROM `coupon_system`.`customers` WHERE `ID`=?";
	private static final String CREATE_METHOD_GET_ALL_CUSTOMER = "SELECT * FROM `coupon_system`.`customers`";
	private static final String CREATE_METHOD_GET_ONE_CUSTOMER = "SELECT * FROM `coupon_system`.`customers` WHERE `ID` = ?";

	@Override
	public boolean isCustomerExists(String email, String Password) {
		boolean flag = false;

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_IS_CUSTOMER_EXISTS;
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, Password);
			ResultSet resultSet = statement.executeQuery();

			// STEP 4
			while (resultSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return flag;

	}

	@Override
	public void addCustomer(Customer customer) {

		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = CREATE_METHOD_ADD_CUSTOMER;

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public void updateCustomer(int customerID, Customer customer) {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_UPDATE_CUSTOMER;

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.setInt(5, customerID);

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public void deleteCustomer(int customerID) {

		try {

			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_DELETE_CUSTOMER;

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_GET_ALL_CUSTOMER;
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			// STEP 4
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				String email = resultSet.getString(4);
				String password = resultSet.getString(5);
				customers.add(new Customer(id, firstName, lastName, email, password, null));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return customers;
	}

	@Override
	public Customer getOneCustomer(int customerID) {

		Customer customer = null;

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_GET_ONE_CUSTOMER;
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			ResultSet resultSet = statement.executeQuery();

			// STEP 4
			while (resultSet.next()) {
				customerID = resultSet.getInt(1);
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				String email = resultSet.getString(4);
				String password = resultSet.getString(5);
				customer = new Customer(customerID, firstName, lastName, email, password);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return customer;
	}

}
