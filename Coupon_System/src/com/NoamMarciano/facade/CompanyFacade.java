package com.NoamMarciano.facade;

import java.util.ArrayList;
import java.util.List;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.beans.Company;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.exception.CouponTitleAlreadyExistException;
import com.NoamMarciano.exception.LoginDeniedException;

public class CompanyFacade extends ClientFacade {

	private int companyID;

	public CompanyFacade() {
	}

	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	@Override
	public boolean login(String email, String password) throws LoginDeniedException {
		if (companiesDBDAO.isCompanyExists(email, password)) {
			System.out.println("Company Login successful!");
			return true;
		}
		throw new LoginDeniedException();
	}

	public void addCoupon(Coupon coupon) throws CouponTitleAlreadyExistException {
		List<Coupon> coupons = couponsDBDAO.getAllCoupons();
		for (Coupon c : coupons) {
			if (c.getCompanyID() == coupon.getCompanyID()) {
				throw new CouponTitleAlreadyExistException();
			}
		}
		couponsDBDAO.addCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon) {
		couponsDBDAO.updateCoupon(coupon);
	}

	public void deleteCoupon(int couponID) {
		List<Coupon> coupons = couponsDBDAO.getAllCoupons();
		for (Coupon c : coupons) {
			if (c.getId() == couponID) {
				couponsDBDAO.deleteCouponPurchase(c.getCompanyID(), couponID);
				couponsDBDAO.deleteCoupon(c);
			}
		}
	}

	public ArrayList<Coupon> getCompanyCoupons() {
		return (ArrayList<Coupon>) couponsDBDAO.getAllCouponsByCompanyID(companyID);
	}

	public ArrayList<Coupon> getCompanyCoupons(Category category) {
		List<Coupon> coupons = couponsDBDAO.getAllCouponsByCompanyID(companyID);
		ArrayList<Coupon> couponsByCategory = new ArrayList<>();
		for (Coupon c : coupons) {
			if (c.getCategory().equals(category)) {
				couponsByCategory.add(c);
			}
		}
		return couponsByCategory;
	}

	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) {
		List<Coupon> coupons = couponsDBDAO.getAllCouponsByCompanyID(companyID);
		ArrayList<Coupon> couponsByPrice = new ArrayList<>();
		for (Coupon c : coupons) {
			if (c.getPrice() < maxPrice) {
				couponsByPrice.add(c);
			}
		}
		return couponsByPrice;
	}

	public Company getCompanyDetails() {
		Company company = companiesDBDAO.getOneCompany(companyID);
		if (company != null) {
			company.getCoupons();
		}
		return company;

	}

}
