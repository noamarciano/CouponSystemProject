package com.NoamMarciano.facade;

import java.util.ArrayList;
import java.util.List;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.beans.Company;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.exception.CannotUpdateException;
import com.NoamMarciano.exception.CouponTitleAlreadyExistException;
import com.NoamMarciano.exception.LoginDeniedException;

public class CompanyFacade extends ClientFacade {

	private int companyID;

	public CompanyFacade() {
		super();
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
			return true;
		} else {
			throw new LoginDeniedException();
		}
	}

	public void addCoupon(Coupon coupon) throws CouponTitleAlreadyExistException {
		List<Coupon> coupons = couponsDBDAO.getAllCouponsByCompanyID(companyID);
		for (Coupon c : coupons) {
			if (c.getTitle().equalsIgnoreCase(coupon.getTitle())) {
				throw new CouponTitleAlreadyExistException();
			}
		}
		couponsDBDAO.addCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon) throws CannotUpdateException {
		if (companyID != coupon.getCompanyID()) {
			throw new CannotUpdateException();
		}
		couponsDBDAO.updateCoupon(coupon.getId(), coupon);
	}

	public void deleteCoupon(int couponID) {
		List<Coupon> coupons = couponsDBDAO.getAllCoupons();
		for (Coupon c : coupons) {
			if (c.getId() == couponID) {
				couponsDBDAO.deleteCouponPurchase(c.getCompanyID(), couponID);
				couponsDBDAO.deleteCoupon(couponID);
				return;
			}
		}
		System.out.println("Not found matching coupons");
	}

	public List<Coupon> getCompanyCoupons() {
		if (couponsDBDAO.getAllCouponsByCompanyID(companyID) != null) {
			return couponsDBDAO.getAllCouponsByCompanyID(companyID);
		}
		System.out.println("This company doesn't have any coupons..");
		return null;
	}

	public ArrayList<Coupon> getCompanyCouponsByCategory(Category category) {
		List<Coupon> coupons = couponsDBDAO.getAllCouponsByCompanyID(companyID);
		ArrayList<Coupon> couponsByCategory = new ArrayList<>();
		for (Coupon c : coupons) {
			if (c.getCategory().equals(category)) {
				couponsByCategory.add(c);
			}
		}
		return couponsByCategory;
	}

	public ArrayList<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) {
		List<Coupon> coupons = couponsDBDAO.getAllCouponsByCompanyID(companyID);
		ArrayList<Coupon> couponsByPrice = new ArrayList<>();
		for (Coupon c : coupons) {
			if (c.getPrice() <= maxPrice) {
				couponsByPrice.add(c);
			}
		}
		return couponsByPrice;

	}

	public Company getCompanyDetails() {
		Company company = companiesDBDAO.getOneCompany(companyID);
		try {
			company.setCoupons(getCompanyCoupons());
			return company;
		} catch (Exception e) {
			System.out.println("This company doesn't have coupons..");
		}
		return null;

	}

}
