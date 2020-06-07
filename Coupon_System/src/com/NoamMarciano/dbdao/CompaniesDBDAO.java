package com.NoamMarciano.dbdao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.NoamMarciano.beans.Company;
import com.NoamMarciano.dao.CompaniesDAO;
import com.NoamMarciano.utils.ConnectionPool;
	
	 


public class CompaniesDBDAO implements CompaniesDAO {
	private static Connection connection = null;
	
	@Override
	public boolean isCompanyExists(String email, String Password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addCompany(Company company) {
		

		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "INSERT INTO `coupon_system`.`companies` (`Name`, `Email`, `Password`) VALUES (?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public void updateCompany(int id, Company company) {
		

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "UPDATE `coupon_system`.`companies` SET name=?, email=? WHERE id=?";

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.setInt(4, id);

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public void deleteCompany(int id, Company company) {

		try {

			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "DELETE FROM `coupon_system`.`companies` WHERE id=?";

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<Company>();


		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM `coupon_system`.`companies`";
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			// STEP 4
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				String password = resultSet.getString(4);
				companies.add(new Company(id, name, email, password, null));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return companies;
	}

	@Override
	public Company getOneCompany(int companyID) {
		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT name, email, password"
					+ "  FROM `coupon_system`.`companies`";
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			// STEP 4
			while (resultSet.next()) {
				
				resultSet.getString("name");
				resultSet.getString("email");
				resultSet.getString("password");
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return null;
	}

}
