package com.NoamMarciano.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.dao.CouponsDAO;
import com.NoamMarciano.utils.ConnectionPool;

public class CouponsDBDAO implements CouponsDAO {
	private static Connection connection = null;

	@Override
	public void addCoupon(Coupon coupon) {

		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = "INSERT INTO `coupon_system`.`coupons` (`Company_ID`, `Category_ID`, `Title`, `Description`, `Start_Date`, `End_Date`, `Amount`, `Price`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, coupon.getCompanyID());
//			statement.setInt(3, coupon.getCategoryID());
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, (Date) coupon.getStartDate());
			statement.setDate(6, (Date) coupon.getEndDate());
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
//			statement.setString(9, coupon.getImage());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public void updateCoupon(Coupon coupon) {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "UPDATE `coupon_system`.`coupons` SET companyID=?, category=?, title=?, description=?, startDate=?, endDate=?, amount=?, price=?, image=? WHERE id=?";

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, coupon.getCompanyID());
//			statement.setString(2, coupon.getCategory());
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, (Date) coupon.getStartDate());
			statement.setDate(6, (Date) coupon.getEndDate());
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.setInt(10, coupon.getId());

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public void deleteCoupon(Coupon coupon) {

		try {

			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "DELETE FROM `coupon_system`.`coupons` WHERE id=?";

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, coupon.getId());
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public List<Coupon> getAllCoupons() {
		List<Coupon> coupons = new ArrayList<Coupon>();

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT * FROM `coupon_system`.`coupons`";
			// STEP 3
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			// STEP 4
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				int companyID = resultSet.getInt(2);
				Category category = (Category) resultSet.getObject(3);
				String title = resultSet.getString(4);
				String description = resultSet.getString(5);
				Date startDate = resultSet.getDate(6);
				Date endDate = resultSet.getDate(7);
				int amount = resultSet.getInt(8);
				double price = resultSet.getDouble(9);
				String image = resultSet.getString(10);
				coupons.add(new Coupon(id, companyID, category, title, description, startDate, endDate, amount, price,
						image));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return coupons;
	}

	@Override
	public Coupon getOneCoupon(int couponID) {
		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = "SELECT companyID, category, title, description, startDate, endDate, amount, price, image FROM `coupon_system`.`coupons`";
			// STEP 3
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			// STEP 4
			while (resultSet.next()) {

				resultSet.getInt(1);
				resultSet.getInt(2);
				resultSet.getString(3);
				resultSet.getString(4);
				resultSet.getDate(5);
				resultSet.getDate(6);
				resultSet.getInt(7);
				resultSet.getDouble(8);
				resultSet.getString(9);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return null;
	}

	@Override
	public void addCouponPurchase(int customerID, int couponID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCouponPurchase(int customerID, int couponID) {
		// TODO Auto-generated method stub

	}

}
