package com.NoamMarciano.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.beans.CustomerVsCoupon;
import com.NoamMarciano.exception.LoginDeniedException;
import com.NoamMarciano.exception.PurchasedCouponException;

public class CustomerFacade extends ClientFacade {

	private int customerID;

	public CustomerFacade() {
		super();
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	@Override
	public boolean login(String email, String password) throws LoginDeniedException {
		if (customerDBDAO.isCustomerExists(email, password)) {
			return true;
		}
		throw new LoginDeniedException();

	}

	public void purchaseCoupon(Coupon coupon) throws PurchasedCouponException {

		if (coupon == null) {
			throw new PurchasedCouponException("This coupon does not exist");
		} else if (couponsDBDAO.getOneCoupon(coupon.getId()) == null) {
			throw new PurchasedCouponException("This id does not exist");
		}

		// You can't purchase coupon more than once
		List<Coupon> coupons = getCustomerCoupons();
		if (coupons != null) {
			for (Coupon c : coupons) {
				if (coupon.getId() == c.getId()) {
					throw new PurchasedCouponException("Coupon already purchased by this customer");
				}
			}
		}

		// You can't purchase coupon when amount=0

		
		if (coupon.getAmount() <= 0) {
			throw new PurchasedCouponException("Sorry, Coupon amount is less then 1");
		}

		// you can't purchase coupon when date is expired
		if (coupon.getEndDate().before(new Date())) {
			throw new PurchasedCouponException("You can't purchase coupon when date is expired");
		}
//		System.out.println(couponFromDB);
		coupon.setAmount(coupon.getAmount() - 1);
//		System.out.println(couponFromDB);
		couponsDBDAO.updateCoupon(coupon);
		couponsDBDAO.addCouponPurchase(customerID, coupon.getId());

	}

	public List<Coupon> getCustomerCoupons() {
		if (couponsDBDAO.getAllCouponsByCustumerID(customerID) != null) {
			return couponsDBDAO.getAllCouponsByCustumerID(customerID);
		} else {
			System.out.println("This customer doesn't have coupons..");
		}
		return null;
	}

	public ArrayList<Coupon> getCustomerCouponsByCategory(Category category) {// TODO need to throw exception + maxPrice
																				// + ID

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
			System.out.println("This customer doesn't have coupons with this category..");
		}
		return null;
	}

	public ArrayList<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) {
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

		Customer customer = customerDBDAO.getOneCustomer(customerID);
		try {
			customer.setCoupons(getCustomerCoupons());
			return customer;
		} catch (Exception e) {
			System.out.println("This customer doesn't have coupons..");
		}
		return null;
	}

}
