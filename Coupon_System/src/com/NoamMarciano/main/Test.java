package com.NoamMarciano.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.beans.Company;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.dao.CompaniesDAO;
import com.NoamMarciano.dao.CouponsDAO;
import com.NoamMarciano.dao.CustomerDAO;
import com.NoamMarciano.data.ConnectionPool;
import com.NoamMarciano.data.Database;
import com.NoamMarciano.dbdao.CompaniesDBDAO;
import com.NoamMarciano.dbdao.CouponsDBDAO;
import com.NoamMarciano.dbdao.CustomerDBDAO;
import com.NoamMarciano.exception.CompanyAlreadyExistException;
import com.NoamMarciano.exception.CompanyDoesNotExistException;
import com.NoamMarciano.exception.CouponAlreadyPurchasedException;
import com.NoamMarciano.exception.CouponNotAvailableException;
import com.NoamMarciano.exception.CouponTitleAlreadyExistException;
import com.NoamMarciano.exception.EmailAlreadyExistException;
import com.NoamMarciano.exception.LoginDeniedException;
import com.NoamMarciano.exception.NoAccessException;
import com.NoamMarciano.facade.AdminFacade;
import com.NoamMarciano.facade.CompanyFacade;
import com.NoamMarciano.facade.CustomerFacade;
import com.NoamMarciano.security.ClientType;
import com.NoamMarciano.security.LoginManager;
import com.NoamMarciano.utils.CheckTitle;

public class Test {

	public static void insertDataToTables()
			throws CompanyAlreadyExistException, EmailAlreadyExistException, LoginDeniedException,
			CouponTitleAlreadyExistException, CouponAlreadyPurchasedException, CouponNotAvailableException {

		AdminFacade adminFacade = null;
		try {

		} catch (Exception e) {
			System.out.println("No Access..");
		}
		
		

//		c1.setEmail("osem11111@company.com");
//		adminFacade.updateCompany(c1);
//		adminFacade.deleteCompany(2);
//		cu1.setEmail("koby1@gmail.com");
//		adminFacade.updateCustomer(cu1);
//		adminFacade.deleteCustomer(2);
//		System.out.println("**********************");
//		System.out.println(adminFacade.getAllCompanies());
//		System.out.println(adminFacade.getAllCustomers());
//
//		System.out.println("********************");
//
//		CompanyFacade companyFacade = new CompanyFacade();
//		companyFacade.login("osem@company.com", "1234");
//		companyFacade.setCompanyID(1);
//		System.out.println("!!!!!!");
//		companyFacade.addCoupon(cou1);
//		companyFacade.addCoupon(cou2);
//		companyFacade.addCoupon(cou3);
//		companyFacade.addCoupon(cou4);
//
//		System.out.println(companyFacade.getCompanyCoupons());
//		System.out.println(companyFacade.getCompanyCoupons(cou1.getCategory()));
//		System.out.println(companyFacade.getCompanyCoupons());
//		System.out.println(companyFacade.getCompanyDetails());
//
//		System.out.println("***********************");

//		cou1.setTitle("fdgdfg");
//		companyFacade.updateCoupon(cou1);
//		companyFacade.deleteCoupon(2);

//		System.out.println(companyFacade.getCompanyCoupons());
//		System.out.println(companyFacade.getCompanyDetails());

//		System.out.println("*****************");
//
//		CustomerFacade customerFacade = new CustomerFacade();
//		customerFacade.login(cu4.getEmail(), cu4.getPassword());
//		customerFacade.purchaseCoupon(cou1);
//		customerFacade.purchaseCoupon(cou2);
//		customerFacade.purchaseCoupon(cou3);
//		customerFacade.purchaseCoupon(cou4);
//
//		System.out.println(customerFacade.getCustomerCoupons());
//		System.out.println(customerFacade.getCustomerCoupons(Category.Food));
//		System.out.println(customerFacade.getCustomerCoupons(60));
//		System.out.println(customerFacade.getCustomerDetails());
	}

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, SQLException,
			CompanyAlreadyExistException, EmailAlreadyExistException, LoginDeniedException,
			CouponTitleAlreadyExistException, CouponAlreadyPurchasedException, CouponNotAvailableException {
		System.out.println("START");
		 ConnectionPool.getInstance();
		// STEP 1 - Loading JDBC Driver - Of MySQL
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Database.dropAllTabales();
		Database.createAllTabales();
		
		TestAll.companiesDBDAO();
		TestAll.customerDBDAO();
		TestAll.couponsDBDAO();
		TestAll.adminFacade();
		
//		

//		c5.setName("SomeCompany-555");
//		companiesDBDAO.updateCompany(5, c5);
//		
//		cu5.setFirstName("firstName-555");
//		customerDBDAO.updateCustomer(5, cu5);

//		System.out.println(companiesDBDAO.getAllCompanies());
//		System.out.println(customerDBDAO.getAllCustomers());

//		companiesDBDAO.deleteCompany(5);
//		customerDBDAO.deleteCustomer(5);

//		System.out.println(customerDBDAO.getOneCustomer(5));
//		System.out.println(companiesDBDAO.getOneCompany(5));
//		System.out.println(companiesDBDAO.isCompanyExists(c5.getEmail(), c5.getPassword()));
//		System.out.println(customerDBDAO.isCustomerExists(cu5.getEmail(), cu5.getPassword()));

//		Database.dropAllTabales();

		ConnectionPool.getInstance().closeAllConnection();
		System.out.println("END");

	}
}
