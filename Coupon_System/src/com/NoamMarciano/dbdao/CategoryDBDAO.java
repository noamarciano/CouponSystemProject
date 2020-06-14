package com.NoamMarciano.dbdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.dao.CategoryDAO;
import com.NoamMarciano.utils.ConnectionPool;
import java.sql.Connection;

public class CategoryDBDAO implements CategoryDAO {

	private static Connection connection = null;

	@Override
	public int getCategoryID(Category category) {
		int x = 0;
		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT id FROM `coupon_system`.`companies` WHERE name = ? ";
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, category.toString());
			ResultSet resultSet = statement.executeQuery();

			// STEP 4
			while (resultSet.next()) {
				x = resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		return x;
	}

	
	public Category getCategoryName(int ID) {
		String category = null;

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT name FROM `coupon_system`.`companies` WHERE id = ?";
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, ID);
			ResultSet resultSet = statement.executeQuery();

			// STEP 4
			while (resultSet.next()) {
				category = resultSet.getString(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}
		return Category.valueOf(category);
	}

}
