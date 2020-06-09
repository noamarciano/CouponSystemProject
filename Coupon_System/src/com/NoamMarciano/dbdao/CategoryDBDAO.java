package com.NoamMarciano.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.utils.ConnectionPool;

public class CategoryDBDAO {

	Connection connection = null;
	
	public int getCategoryID(Category category) throws InterruptedException {
		int x = 0;
		
		try {
		connection = ConnectionPool.getInstance().getConnection();
		String sql = "SELECT ID FROM `coupon_system`.categories WHERE name = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, category.toString());
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			x = resultSet.getInt(1);		
		}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionPool.getInstance().closeAllConnection();
		}
		return x;
	}
	
	
	
}
