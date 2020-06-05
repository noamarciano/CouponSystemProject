package com.NoamMarciano.dao;

import java.util.List;

import com.NoamMarciano.beans.Coupon;

public interface CouponsDAO {

	void addCoupon(Coupon coupon);
	
	void updateCoupon(Coupon coupon);
	
	void deleteCoupon(Coupon coupon);
	
	List<Coupon> getAllCoupons();
	
	Coupon getOneCoupon(int couponID);
	
	void addCouponPurchase(int customerID, int couponID);
	
	void deleteCouponPurchase(int customerID, int couponID);
	
}
