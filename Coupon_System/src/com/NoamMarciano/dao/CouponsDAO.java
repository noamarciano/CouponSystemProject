package com.NoamMarciano.dao;

import java.util.List;

import com.NoamMarciano.beans.Coupon;

public interface CouponsDAO {

	void addCoupon(Coupon coupon);

	void updateCoupon(Coupon coupon);

	void deleteCoupon(int couponID);

	List<Coupon> getAllCoupons();

	List<Coupon> getAllCouponsByCompanyID(int companyID);

	List<Coupon> getAllCouponsByCustumerID(int customerID);// Not request in the document

	Coupon getOneCoupon(int couponID);

	void addCouponPurchase(int customerID, int couponID);

	void deleteCouponPurchase(int customerID, int couponID);

	void deleteCouponPurchaseByCouponID(int couponID);// Not request in the document
}
