package com.NoamMarciano.job;

import java.util.Date;
import java.util.List;

import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.dao.CouponsDAO;
import com.NoamMarciano.dbdao.CouponsDBDAO;

public class DailyJob implements Runnable {

	private CouponsDAO couponsDAO;

	private boolean quit = false;

	public DailyJob() {
		this.couponsDAO = new CouponsDBDAO();
	}

	@Override
	public void run() {

		while (!quit) {
			List<Coupon> coupons = couponsDAO.getAllCoupons();
			for (Coupon coupon : coupons) {
				if (coupon.getEndDate().before(new Date(System.currentTimeMillis()))) {
//					couponsDAO.deleteCouponPurchase(customerID, couponID); TODO need to find the customer id
					couponsDAO.deleteCoupon(coupon.getId());
				}
			}
			try {
				Thread.sleep(24 * 60 * 60 * 1000);
			} catch (Exception e) {
				return;
			}
		}
	}

	public void stopJob() {
		this.quit = true;

	}

}
