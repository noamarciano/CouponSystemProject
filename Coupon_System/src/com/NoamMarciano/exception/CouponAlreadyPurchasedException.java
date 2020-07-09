package com.NoamMarciano.exception;

public class CouponAlreadyPurchasedException extends Exception {

	public CouponAlreadyPurchasedException() {
		System.out.println("Coupon already purchased by this customer..");
	}

}
