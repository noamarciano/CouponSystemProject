package com.NoamMarciano.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.dao.CouponsDAO;
import com.NoamMarciano.data.ConnectionPool;
import com.NoamMarciano.utils.DateUtils;

public class CouponsDBDAO implements CouponsDAO {
	private static Connection connection = null;

	private static final String CREATE_METHOD_ADD_COUPONS = "INSERT INTO `coupon_system`.`coupons` (`Company_ID`, `Category_ID`, `Title`, `Description`, `Start_Date`, `End_Date`, `Amount`, `Price`, `Image`)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String CREATE_METHOD_UPDATE_COUPON = "UPDATE `coupon_system`.`coupons` SET `Title` = ?, `Description` = ?, `Start_Date` = ?, `End_Date` = ?, `Amount` = ?, `Price` = ?, `Image` = ? WHERE (`ID` = ?);";

	private static final String CREATE_METHOD_DELETE_COUPON = "DELETE FROM `coupon_system`.`coupons` WHERE `ID` = ?";
	private static final String CREATE_METHOD_GET_ALL_COUPONS = "SELECT * FROM `coupon_system`.`coupons`";
	private static final String CREATE_METHOD_GET_ALL_COUPONS_BY_COMPANYID = "SELECT * FROM `coupon_system`.`coupons` WHERE `Company_ID` = ?";
	private static final String CREATE_METHOD_GET_ALL_COUPONS_BY_CUSTOMERID = "SELECT * FROM `coupon_system`.`customers_vs_coupons` WHERE `Customer_ID` = ?";

	private static final String CREATE_METHOD_GET_ONE_COUPON = "SELECT * FROM `coupon_system`.`coupons` WHERE `ID`= ? ";

	private static final String CREATE_METHOD_ADD_COUPON_PURCHASE = "INSERT INTO `coupon_system`.`customers_vs_coupons` (`Customer_ID`, `Coupon_ID`) VALUES (?, ?);";
	private static final String CREATE_METHOD_DELETE_COUPON_PURCHASE = "DELETE FROM `coupon_system`.`customers_vs_coupons` WHERE `Customer_ID` = ? AND `Coupon_ID` = ?";

	@Override
	public void addCoupon(Coupon coupon) {

		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = CREATE_METHOD_ADD_COUPONS;

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, coupon.getCompanyID());
			statement.setInt(2, coupon.getCategory().ordinal() + 1);
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, DateUtils.convertUtilDateToSQL(coupon.getStartDate()));
			statement.setDate(6, DateUtils.convertUtilDateToSQL(coupon.getEndDate()));
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.executeUpdate();
			System.out.println("Add coupon successful!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public void updateCoupon(int couponID, Coupon coupon) {

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_UPDATE_COUPON;

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, coupon.getTitle());
			statement.setString(2, coupon.getDescription());
			statement.setDate(3, DateUtils.convertUtilDateToSQL(coupon.getStartDate()));
			statement.setDate(4, DateUtils.convertUtilDateToSQL(coupon.getEndDate()));
			statement.setInt(5, coupon.getAmount());
			statement.setDouble(6, coupon.getPrice());
			statement.setString(7, coupon.getImage());
			statement.setInt(8, couponID);
			statement.executeUpdate();
			System.out.println("Update coupon (" + couponID + ") successful!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public void deleteCoupon(int couponID) {

		try {

			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_DELETE_COUPON;

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, couponID);
			statement.executeUpdate();
			System.out.println("Coupon (" + couponID + ") has been deleted..");
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

			String sql = CREATE_METHOD_GET_ALL_COUPONS;
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			// STEP 4
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				int companyID = resultSet.getInt(2);
				Category category = (Category.values()[resultSet.getInt(3) - 1]);
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
		Coupon coupon = new Coupon();

		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_GET_ONE_COUPON;
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, couponID);
			ResultSet resultSet = statement.executeQuery();

			// STEP 4
			while (resultSet.next()) {

				couponID = resultSet.getInt(1);
				int companyID = resultSet.getInt(2);
				Category category = (Category.values()[resultSet.getInt(3) - 1]);
				String title = resultSet.getString(4);
				String description = resultSet.getString(5);
				Date startDate = resultSet.getDate(6);
				Date endDate = resultSet.getDate(7);
				int amount = resultSet.getInt(8);
				double price = resultSet.getDouble(9);
				String image = resultSet.getString(10);
				coupon = new Coupon(couponID, companyID, category, title, description, startDate, endDate, amount,
						price, image);
				return coupon;

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

		try {
			connection = ConnectionPool.getInstance().getConnection();
			String sql = CREATE_METHOD_ADD_COUPON_PURCHASE;

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			statement.setInt(2, couponID);
			statement.executeUpdate();
			System.out.println("Coupon (" + couponID + ") purchased successfuly!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public void deleteCouponPurchase(int customerID, int couponID) {
		try {

			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_DELETE_COUPON_PURCHASE;

			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			statement.setInt(2, couponID);
			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

	}

	@Override
	public List<Coupon> getAllCouponsByCompanyID(int companyID) {
		List<Coupon> coupons = new ArrayList<>();
		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_GET_ALL_COUPONS_BY_COMPANYID;
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, companyID);
			ResultSet resultSet = statement.executeQuery();

			// STEP 4
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				companyID = resultSet.getInt(2);
				Category category = (Category.values()[resultSet.getInt(3) - 1]);
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
	public List<Coupon> getAllCouponsByCustumerID(int customerID) {
		List<Coupon> coupons = new ArrayList<Coupon>();
		try {
			// STEP 2
			connection = ConnectionPool.getInstance().getConnection();

			String sql = CREATE_METHOD_GET_ALL_COUPONS_BY_CUSTOMERID;
			// STEP 3
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, customerID);
			ResultSet resultSet = statement.executeQuery();
			List<Integer> nums = new ArrayList<>();
			// STEP 4
			while (resultSet.next()) {
				nums.add(resultSet.getInt(1));
			}
			if (nums != null) {
				for (int i = 0; i < nums.size(); i++) {
					sql = "SELECT * FROM `coupon_system`.`coupons` WHERE `ID`= ? ";
					statement = connection.prepareStatement(sql);
					statement.setInt(1, nums.get(i));
					resultSet = statement.executeQuery();
					while (resultSet.next()) {
						int id = resultSet.getInt(1);
						int companyID = resultSet.getInt(2);
						Category category = Category.values()[resultSet.getInt(3) - 1];
						String title = resultSet.getString(4);
						String description = resultSet.getString(5);
						Date startDate = resultSet.getDate(6);
						Date endDate = resultSet.getDate(7);
						int amount = resultSet.getInt(8);
						double price = resultSet.getDouble(9);
						String image = resultSet.getString(10);
						coupons.add(new Coupon(id, companyID, category, title, description, startDate, endDate, amount,
								price, image));
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(connection);
		}

		return coupons;
	}

}
