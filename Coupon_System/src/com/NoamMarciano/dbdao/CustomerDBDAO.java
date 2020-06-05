package com.NoamMarciano.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.dao.CustomerDAO;
import com.NoamMarciano.utils.ConnectionPool;

public class CustomerDBDAO implements CustomerDAO {

	@Override
	public boolean isCustomerExists(String email, String Password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addCustomer(Customer customer) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "INSERT INTO `coupon_system`.`customers` (id,firstName,lastName,email,password) VALUES (?,?,?,?,?)";

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customer.getId());
			statement.setString(2, customer.getFirstName());
			statement.setString(3, customer.getLastName());
			statement.setString(4, customer.getEmail());
			statement.setString(5, customer.getPassword());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public void updateCustomer(Customer customer) {
		Connection connection = null;

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "UPDATE `coupon_system`.`customers` SET firstName=?, lastName=?, email=?, password=? WHERE id=?";

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.setInt(5, customer.getId());

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public void deleteCustomer(Customer customer) {
		Connection connection = null;
		try {

			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "DELETE FROM `coupon_system`.`customers` WHERE id=?";

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customer.getId());
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
		Connection connection = null;

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM `coupon_system`.`customers`";
			// STEP 3
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

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
	public Customer getOneCompany(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

}
