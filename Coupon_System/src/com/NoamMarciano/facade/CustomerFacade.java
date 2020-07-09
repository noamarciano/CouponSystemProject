package com.NoamMarciano.facade;

import java.util.ArrayList;
import java.util.List;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.exception.CouponAlreadyPurchasedException;
import com.NoamMarciano.exception.CouponNotAvailableException;
import com.NoamMarciano.exception.LoginDeniedException;

public class CustomerFacade extends ClientFacade {

	private int customerID;

	public CustomerFacade() {
	}

	@Override
	public boolean login(String email, String password) throws LoginDeniedException {
		if (customerDBDAO.isCustomerExists(email, password)) {
			System.out.println("Login successful!");
			return true;
		}
		throw new LoginDeniedException();

	}

	public void purchaseCoupon(Coupon coupon) throws CouponAlreadyPurchasedException, CouponNotAvailableException {
		List<Coupon> coupons = couponsDBDAO.getAllCouponsByCustumerID(customerID);
			for (Coupon c : coupons) {
				if (coupon.getId() == c.getId()) {
					throw new CouponAlreadyPurchasedException();
				} else if (coupon.getAmount() < 1) {
					throw new CouponNotAvailableException();
				}
			}
		couponsDBDAO.addCouponPurchase(customerID, coupon.getId());
		coupon.setAmount(coupon.getAmount() - 1);

	}

	public ArrayList<Coupon> getCustomerCoupons() {
		try {
			return (ArrayList<Coupon>) couponsDBDAO.getAllCouponsByCustumerID(customerID);
		} catch (Exception e) {
			System.out.println("This customer doesn't have coupons..");
		}
		return null;
	}

	public ArrayList<Coupon> getCustomerCoupons(Category category) {

		List<Coupon> coupons = new ArrayList<>();
		List<Coupon> couponsByCategory = new ArrayList<>();
		try {
			coupons = couponsDBDAO.getAllCouponsByCustumerID(customerID);
			for (Coupon c : coupons) {
				if (c.getCategory().equals(category)) {
					couponsByCategory.add(c);
				}
			}
			return (ArrayList<Coupon>) couponsByCategory;
		} catch (Exception e) {
			System.out.println("This customer doesn't have coupons..");
		}
		return null;
	}

	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) {
		List<Coupon> coupons = new ArrayList<>();
		List<Coupon> couponsByPrice = new ArrayList<>();
		try {
			coupons = couponsDBDAO.getAllCouponsByCustumerID(customerID);
			for (Coupon c : coupons) {
				if (c.getPrice() < maxPrice) {
					couponsByPrice.add(c);
				}
			}

			return (ArrayList<Coupon>) couponsByPrice;
		} catch (Exception e) {
			System.out.println("This customer doesn't have coupons..");
		}
		return null;
	}

	public Customer getCustomerDetails() {

		Customer customer;
		customer = customerDBDAO.getOneCustomer(customerID);
		try {
			customer.setCoupons(getCustomerCoupons());
			return customer;
		} catch (Exception e) {
			System.out.println("This customer doesn't have coupons..");
		}
		return null;
	}

}
