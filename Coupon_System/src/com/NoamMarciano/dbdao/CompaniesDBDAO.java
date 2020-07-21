package com.NoamMarciano.dbdao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.NoamMarciano.beans.Company;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.dao.CompaniesDAO;
import com.NoamMarciano.data.ConnectionPool;

public class CompaniesDBDAO implements CompaniesDAO {
	private static Connection connection = null;

	private static final String CREATE_METHOD_IS_COMPANY_EXISTS = "SELECT * FROM `coupon_system`.`companies` WHERE email = ? AND password = ?";
	private static final String CREATE_METHOD_ADD_COMPANY = "INSERT INTO `coupon_system`.`companies` (`Name`, `Email`, `Password`) VALUES (?, ?, ?)";
	private static final String CREATE_METHOD_UPDATE_COMPANY = "UPDATE `coupon_system`.`companies` SET `Email`=?, `Password`=? WHERE (`ID`=?)";
	private static final String CREATE_METHOD_DELETE_COMPANY = "DELETE FROM `coupon_system`.`companies` WHERE `ID`=?";
	private static final String CREATE_METHOD_GET_ALL_COMPANIES = "SELECT * FROM `coupon_system`.`companies`";
	private static final String CREATE_METHOD_GET_ONE_COMPANY = "SELECT * FROM `coupon_system`.`companies` WHERE `ID` = ?";

	@Override
	public boolean isCompanyExists(String email, String Password) {
		boolean flag = false;

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_IS_COMPANY_EXISTS;
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
		connection = null;
		return flag;
	}

	@Override
	public void addCompany(Company company) {

		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = CREATE_METHOD_ADD_COMPANY;

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());

			statement.executeUpdate();
			System.out.println("Add company successful!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		connection = null;
	}

	@Override
	public void updateCompany(Company company) {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_UPDATE_COMPANY;

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, company.getEmail());
			statement.setString(2, company.getPassword());
			statement.setInt(3, company.getId());

			statement.executeUpdate();
			System.out.println("Update company successful!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		connection = null;
	}

	@Override
	public void deleteCompany(int companyID) {

		try {

			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_DELETE_COMPANY;

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, companyID);
			statement.executeUpdate();
			System.out.println("Company has been deleted..");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		connection = null;
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<Company>();

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_GET_ALL_COMPANIES;
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
		connection = null;
		return companies;
	}

	@Override
	public Company getOneCompany(int companyID) {

		Company company = null;

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_GET_ONE_COMPANY;
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, companyID);
			ResultSet resultSet = statement.executeQuery();

			// STEP 4
			while (resultSet.next()) {
				companyID = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				String password = resultSet.getString(4);
				company = new Company(companyID, name, email, password, new ArrayList<Coupon>());

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		connection = null;
		return company;
	}

}
