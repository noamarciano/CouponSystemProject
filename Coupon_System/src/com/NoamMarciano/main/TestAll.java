package com.NoamMarciano.main;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.beans.Company;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.dao.CompaniesDAO;
import com.NoamMarciano.dbdao.CompaniesDBDAO;
import com.NoamMarciano.dbdao.CouponsDBDAO;
import com.NoamMarciano.dbdao.CustomerDBDAO;
import com.NoamMarciano.exception.CannotUpdateException;
import com.NoamMarciano.exception.CompanyAlreadyExistException;
import com.NoamMarciano.exception.CouponTitleAlreadyExistException;
import com.NoamMarciano.exception.EmailAlreadyExistException;
import com.NoamMarciano.exception.LoginDeniedException;
import com.NoamMarciano.exception.PurchasedCouponException;
import com.NoamMarciano.facade.AdminFacade;
import com.NoamMarciano.facade.CompanyFacade;
import com.NoamMarciano.facade.CustomerFacade;
import com.NoamMarciano.security.ClientType;
import com.NoamMarciano.security.LoginManager;
import com.NoamMarciano.utils.CheckTitle;

public class TestAll {

	static AdminFacade adminFacade = new AdminFacade();
	static CompanyFacade companyFacade = new CompanyFacade();
	static CustomerFacade customerFacade = new CustomerFacade();
	static CustomerDBDAO customerDBDAO = new CustomerDBDAO();
	static CompaniesDAO companiesDBDAO = new CompaniesDBDAO();
	static CouponsDBDAO couponsDBDAO = new CouponsDBDAO();

	public static void companiesDBDAO() {

		CheckTitle.companiesDBDAOcheck();

		Company c1 = new Company();
		c1.setName("Osem");
		c1.setEmail("osem@company.com");
		c1.setPassword("1234");

		Company c2 = new Company();
		c2.setName("Samsung");
		c2.setEmail("samsung@company.com");
		c2.setPassword("1234");

		Company c3 = new Company();
		c3.setName("Burger King");
		c3.setEmail("burgerking@company.com");
		c3.setPassword("1234");

		CheckTitle.printTestLine("add companies");
		companiesDBDAO.addCompany(c1);
		companiesDBDAO.addCompany(c2);
		companiesDBDAO.addCompany(c3);
		CheckTitle.printCompaniesTable(companiesDBDAO.getAllCompanies());

		CheckTitle.printTestLine("check if company exist");//// Should return true
		System.out.println(companiesDBDAO.isCompanyExists("osem@company.com", "1234"));

		CheckTitle.printTestLine("!WRONG! check if company exist");// Should return false
		System.out.println(companiesDBDAO.isCompanyExists("osem111@company.com", "1234"));

		CheckTitle.printTestLine("update company");
		c2.setPassword("4321");
		c2.setEmail("samsung1111@company.com");
		companiesDBDAO.updateCompany(2, c2);
		CheckTitle.printOneCompany(companiesDBDAO.getOneCompany(2));

		CheckTitle.printTestLine("delete company");
		companiesDBDAO.deleteCompany(1);
		CheckTitle.printCompaniesTable(companiesDBDAO.getAllCompanies());

		CheckTitle.printTestLine("get all companies");
		CheckTitle.printCompaniesTable(companiesDBDAO.getAllCompanies());

		CheckTitle.printTestLine("get one comapany");
		CheckTitle.printOneCompany(companiesDBDAO.getOneCompany(2));

		CheckTitle.separatorLine();
	}

	public static void customerDBDAO() {

		CheckTitle.customersDBDAOcheck();

		Customer cu1 = new Customer();
		cu1.setFirstName("Koby");
		cu1.setLastName("Shasha");
		cu1.setEmail("koby@gmail.com");
		cu1.setPassword("1234");

		Customer cu2 = new Customer();
		cu2.setFirstName("Noam");
		cu2.setLastName("Marciano");
		cu2.setEmail("noam@gmail.com");
		cu2.setPassword("1234");

		Customer cu3 = new Customer();
		cu3.setFirstName("Orit");
		cu3.setLastName("Cohen");
		cu3.setEmail("orit@gmail.com");
		cu3.setPassword("1234");

		CheckTitle.printTestLine("add customer");

		customerDBDAO.addCustomer(cu1);
		customerDBDAO.addCustomer(cu2);
		customerDBDAO.addCustomer(cu3);
		CheckTitle.printCustomersTable(customerDBDAO.getAllCustomers());

		CheckTitle.printTestLine("check if customer exist");// Should return true
		System.out.println(customerDBDAO.isCustomerExists("koby@gmail.com", "1234"));

		CheckTitle.printTestLine("!WRONG! check if customer exist");//// Should return false
		System.out.println(customerDBDAO.isCustomerExists("koby@gmail.com", "1111"));

		CheckTitle.printTestLine("update customer");
		cu2.setPassword("4321");
		cu2.setFirstName("Eliyahu");
		customerDBDAO.updateCustomer(2, cu2);
		CheckTitle.printOneCustomer(customerDBDAO.getOneCustomer(2));

		CheckTitle.printTestLine("delete customer");
		customerDBDAO.deleteCustomer(1);
		CheckTitle.printCustomersTable(customerDBDAO.getAllCustomers());

		CheckTitle.printTestLine("get all customers");
		CheckTitle.printCustomersTable(customerDBDAO.getAllCustomers());

		CheckTitle.printTestLine("get one customer");
		CheckTitle.printOneCustomer(customerDBDAO.getOneCustomer(3));

		CheckTitle.separatorLine();

	}

	public static void couponsDBDAO() {

		CheckTitle.couponsDBDAOcheck();

		Coupon cou1 = new Coupon();
		cou1.setCompanyID(1);
		cou1.setCategory(Category.Vacation);
		cou1.setTitle("10%");
		cou1.setDescription("get 10% off");
		cou1.setStartDate(new Date(2020, 07, 20));
		cou1.setEndDate(new Date(2020, 10, 07));
		cou1.setAmount(70);
		cou1.setPrice(250);
		cou1.setImage("img.com");

		Coupon cou2 = new Coupon();
		cou2.setCompanyID(2);
		cou2.setCategory(Category.Electricity);
		cou2.setTitle("50%");
		cou2.setDescription("discount 50%");
		cou2.setStartDate(new Date(2021, 02, 15));
		cou2.setEndDate(new Date(2021, 04, 12));
		cou2.setAmount(100);
		cou2.setPrice(39.9);
		cou2.setImage("img.com");

		Coupon cou3 = new Coupon();
		cou3.setCompanyID(3);
		cou3.setCategory(Category.Food);
		cou3.setTitle("happy hour");
		cou3.setDescription("buy one, get two");
		cou3.setStartDate(new Date(2020, 06, 30));
		cou3.setEndDate(new Date(2020, 07, 05));
		cou3.setAmount(50);
		cou3.setPrice(50);
		cou3.setImage("img.com");

		CheckTitle.printTestLine("add coupon");
		couponsDBDAO.addCoupon(cou1);
		couponsDBDAO.addCoupon(cou2);
		couponsDBDAO.addCoupon(cou3);
		CheckTitle.printCouponsTable(couponsDBDAO.getAllCoupons());

		CheckTitle.printTestLine("update coupon");
		cou2.setAmount(200);
		cou2.setPrice(79);
		couponsDBDAO.updateCoupon(2, cou2);
		CheckTitle.printOneCoupon(couponsDBDAO.getOneCoupon(2));

		CheckTitle.printTestLine("delete coupon");
		couponsDBDAO.deleteCoupon(1);
		CheckTitle.printCouponsTable(couponsDBDAO.getAllCoupons());

		CheckTitle.printTestLine("get all coupons");
		CheckTitle.printCouponsTable(couponsDBDAO.getAllCoupons());

		CheckTitle.printTestLine("get one coupon");
		CheckTitle.printOneCoupon(couponsDBDAO.getOneCoupon(3));
		System.out.println();

		CheckTitle.separatorLine();
	}

	public static void adminFacade() {

		CheckTitle.adminFacadeCheck();

		CheckTitle.printTestLine("Admin Facade - Real login test");
		try {
			adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin",
					ClientType.Administrator);
		} catch (LoginDeniedException e) {
			System.out.println(e.getMessage());
		}

		CheckTitle.printTestLine("Admin Facade - !WRONG! login test");
		try {
			adminFacade = (AdminFacade) LoginManager.getInstance().login("admin111@admin.com", "admin111",
					ClientType.Administrator);
		} catch (LoginDeniedException e) {
			System.out.println(e.getMessage());
		}

		CheckTitle.printTestLine("Admin Facade - add companies");
		Company c4 = new Company();
		c4.setName("Hilton");
		c4.setEmail("hilton@company.com");
		c4.setPassword("1234");

		Company c5 = new Company();
		c5.setName("Herodes");
		c5.setEmail("herodes@company.com");
		c5.setPassword("1234");

		try {
			adminFacade.addCompany(c4);
			adminFacade.addCompany(c5);
		} catch (CompanyAlreadyExistException e) {
			System.out.println(e.getMessage());
		}

		CheckTitle.printCompaniesTable(adminFacade.getAllCompanies());

		CheckTitle.printTestLine("Admin Facade - !WRONG! add companies");
		Company c6 = new Company();
		c6.setName("Hilton");
		c6.setEmail("hilton111@company.com");
		c6.setPassword("1234");

		Company c7 = new Company();
		c7.setName("Hilton111");
		c7.setEmail("hilton@company.com");
		c7.setPassword("1234");

		try {
			adminFacade.addCompany(c6);
			adminFacade.addCompany(c7);
		} catch (CompanyAlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		CheckTitle.printCompaniesTable(adminFacade.getAllCompanies());

		CheckTitle.printTestLine("Admin Facade - update company");

		try {
			Company company = adminFacade.getOneCompany(4);

//		company.getName();
			company.setEmail("hilton123@company.com");
			company.setPassword("9876");
			adminFacade.updateCompany(4, company);
		} catch (CannotUpdateException e2) {
			System.out.println(e2.getMessage());
		}
		CheckTitle.printOneCompany(adminFacade.getOneCompany(4));
//		CheckTitle.printCompaniesTable(adminFacade.getAllCompanies());

		CheckTitle.printTestLine("Admin Facade - !WRONG! update company");
		c4.getName();
		c4.setName("New Hilton");
		c4.setId(123);
		try {
			adminFacade.updateCompany(4, c4);
		} catch (CannotUpdateException e1) {
			System.out.println(e1.getMessage());
		}

		CheckTitle.printTestLine("Admin Facade - delete company");
		adminFacade.deleteCompany(5);
		CheckTitle.printCompaniesTable(adminFacade.getAllCompanies());

		CheckTitle.printTestLine("Admin Facade - !WRONG! delete company");
		adminFacade.deleteCompany(22);
		CheckTitle.printCompaniesTable(adminFacade.getAllCompanies());

		CheckTitle.printTestLine("Admin Facade - add customers");

		Customer cu4 = new Customer();
		cu4.setFirstName("Dana");
		cu4.setLastName("Levi");
		cu4.setEmail("dana@gmail.com");
		cu4.setPassword("1234");

		Customer cu5 = new Customer();
		cu5.setFirstName("Avi");
		cu5.setLastName("Barda");
		cu5.setEmail("avi@gmail.com");
		cu5.setPassword("1234");

		try {
			CheckTitle.printCustomersTable(adminFacade.getAllCustomers());
			System.out.println();
			adminFacade.addCustomer(cu4);
			adminFacade.addCustomer(cu5);
			System.out.println();
		} catch (EmailAlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		CheckTitle.printCustomersTable(adminFacade.getAllCustomers());

		CheckTitle.printTestLine("Admin Facade - !WRONG! add customers");

		Customer cu6 = new Customer();
		cu6.setFirstName("Rut");
		cu6.setLastName("Bad");
		cu6.setEmail("dana@gmail.com");
		cu6.setPassword("1234");

		Customer cu7 = new Customer();
		cu7.setFirstName("Eden");
		cu7.setLastName("Levi");
		cu7.setEmail("avi@gmail.com");
		cu7.setPassword("1234");

		try {
			adminFacade.addCustomer(cu6);
			adminFacade.addCustomer(cu7);
			System.out.println("Add customer successful!");
		} catch (EmailAlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		CheckTitle.printCustomersTable(adminFacade.getAllCustomers());

		CheckTitle.printTestLine("Admin Facae - get all companies");
		CheckTitle.printCompaniesTable(adminFacade.getAllCompanies());

		CheckTitle.printTestLine("Admin Facade - get all customers");
		CheckTitle.printCustomersTable(adminFacade.getAllCustomers());

		CheckTitle.printTestLine("Admin Facade - get one company");
		CheckTitle.printOneCompany(adminFacade.getOneCompany(2));

		CheckTitle.printTestLine("Admin Facade - !WRONG! get one company");
		CheckTitle.printOneCompany(adminFacade.getOneCompany(9));

		CheckTitle.printTestLine("Admin Facade - get one customer");
		CheckTitle.printOneCustomer(adminFacade.getOneCustomer(2));

		CheckTitle.printTestLine("Admin Facade - !WRONG! get one customer");
		CheckTitle.printOneCustomer(adminFacade.getOneCustomer(20));

		CheckTitle.separatorLine();

	}

	public static void companyFacade() {

		CheckTitle.companyFacadeCheck();

		CheckTitle.printTestLine("Company Facade - Real login test");
		try {
			companyFacade = (CompanyFacade) LoginManager.getInstance().login("burgerking@company.com", "1234",
					ClientType.Company);
		} catch (LoginDeniedException e) {
			System.out.println(e.getMessage());
		}

		companyFacade.setCompanyID(3);

		CheckTitle.printTestLine("Company Facade - !WRONG! login test");
		try {
			companyFacade = (CompanyFacade) LoginManager.getInstance().login("burger@company.com", "1234",
					ClientType.Company);
		} catch (LoginDeniedException e) {
			System.out.println(e.getMessage());
		}

		CheckTitle.printTestLine("Company Facade - add coupons");

		Coupon cou4 = new Coupon();
		cou4.setCompanyID(3);
		cou4.setCategory(Category.Restaurant);
		cou4.setTitle("1+1");
		cou4.setDescription("buy one, get one");
		cou4.setStartDate(new Date(2020, 06, 23));
		cou4.setEndDate(new Date(2021, 03, 11));
		cou4.setAmount(100);
		cou4.setPrice(59.9);
		cou4.setImage("img.com");

		Coupon cou5 = new Coupon();
		cou5.setCompanyID(3);
		cou5.setCategory(Category.Food);
		cou5.setTitle("Free fries");
		cou5.setDescription("buy a meal and get fries for FREE!");
		cou5.setStartDate(new Date(2020, 07, 15));
		cou5.setEndDate(new Date(2021, 01, 17));
		cou5.setAmount(70);
		cou5.setPrice(49.9);
		cou5.setImage("img.com");

		try {
			companyFacade.addCoupon(cou4);
			companyFacade.addCoupon(cou5);
		} catch (CouponTitleAlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		CheckTitle.printCouponsTable(companyFacade.getCompanyCoupons());

		CheckTitle.printTestLine("Company Facade - !WRONG! add coupons");

		Coupon cou6 = new Coupon();
		cou6.setCompanyID(3);
		cou6.setCategory(Category.Food);
		cou6.setTitle("Free fries");
		cou6.setDescription("buy a meal and get fries for FREE!");
		cou6.setStartDate(new Date(2021, 03, 15));
		cou6.setEndDate(new Date(2022, 02, 17));
		cou6.setAmount(40);
		cou6.setPrice(49.9);
		cou6.setImage("img.com");

		Coupon cou7 = new Coupon();
		cou7.setCompanyID(3);
		cou7.setCategory(Category.Food);
		cou7.setTitle("FreeFries");
		cou7.setDescription("buy a meal and get fries for free");
		cou7.setStartDate(new Date(2021, 07, 15));
		cou7.setEndDate(new Date(2022, 01, 17));
		cou7.setAmount(71);
		cou7.setPrice(49);
		cou7.setImage("img.comm");

		try {
			companyFacade.addCoupon(cou6);
			companyFacade.addCoupon(cou7);
		} catch (CouponTitleAlreadyExistException e) {
			System.out.println(e.getMessage());
		}
		CheckTitle.printCouponsTable(companyFacade.getCompanyCoupons());

		CheckTitle.printTestLine("Company Facade - update coupon");

		try {
			Coupon coupon = couponsDBDAO.getOneCoupon(5);

			coupon.setTitle("FreeFries");
			coupon.setDescription("buy a meal and get fries");
			coupon.setStartDate(new Date(2020, 02, 10));
			coupon.setEndDate(new Date(2021, 01, 15));
			coupon.setAmount(40);
			coupon.setPrice(59.9);
			coupon.setImage("img.comm");
			companyFacade.updateCoupon(coupon);
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		CheckTitle.printCouponsTable(companyFacade.getCompanyCoupons());

		CheckTitle.printTestLine("Company Facade - !WRONG! update coupon");

		try {
			Coupon coupon = couponsDBDAO.getOneCoupon(5);

			coupon.setId(10);
			coupon.setCompanyID(15);
			companyFacade.updateCoupon(coupon);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		CheckTitle.printCouponsTable(companyFacade.getCompanyCoupons());

		CheckTitle.printTestLine("Company Facade - delete coupon");

		companyFacade.deleteCoupon(4);
		CheckTitle.printCouponsTable(companyFacade.getCompanyCoupons());
		System.out.println();

		CheckTitle.printTestLine("Company Facade - !WRONG! delete coupon");
		companyFacade.deleteCoupon(9);
		CheckTitle.printCouponsTable(companyFacade.getCompanyCoupons());

		CheckTitle.printTestLine("Company Facade - get company coupons");
		CheckTitle.printCouponsTable(companyFacade.getCompanyCoupons());

		CheckTitle.printTestLine("Company Facade - get company coupons by category");

		CheckTitle.printCouponsTable(companyFacade.getCompanyCouponsByCategory(Category.Food));

		CheckTitle.printTestLine("Company Facade - !WRONG! get company coupons by category");

		CheckTitle.printCouponsTable(companyFacade.getCompanyCouponsByCategory(Category.Electricity));

		CheckTitle.printTestLine("Company Facade - get company coupons by maxPrice");

		CheckTitle.printCouponsTable(companyFacade.getCompanyCouponsByMaxPrice(52));

		CheckTitle.printTestLine("Company Facade - !WRONG! get company coupons by maxPrice");

		CheckTitle.printCouponsTable(companyFacade.getCompanyCouponsByMaxPrice(5.99));

		CheckTitle.printTestLine("Company Facade - get company details");
		CheckTitle.printOneCompany(companyFacade.getCompanyDetails());

		CheckTitle.separatorLine();
	}

	public static void customerFacade() {

		CheckTitle.customerFacadeCheck();

		CheckTitle.printTestLine("Customer Facade - !WRONG! login test");
		try {
			customerFacade = (CustomerFacade) LoginManager.getInstance().login("mosh@gmail.com", "1234",
					ClientType.Customer);
		} catch (LoginDeniedException e) {
			System.out.println(e.getMessage());
		}

		CheckTitle.printTestLine("Customer Facade - Real login test");
		try {
			customerFacade = (CustomerFacade) LoginManager.getInstance().login("dana@gmail.com", "1234",
					ClientType.Customer);
		} catch (LoginDeniedException e) {
			System.out.println(e.getMessage());
		}
		customerFacade.setCustomerID(2);

		CheckTitle.printTestLine("Customer Facade - purchase coupon");// TODO need to fix the purchase method instead of
																		// customer id it brings the company id

		try {
			Coupon coupon = couponsDBDAO.getOneCoupon(2);
			CheckTitle.printOneCoupon(coupon);

			customerFacade.purchaseCoupon(coupon);
		} catch (PurchasedCouponException e) {
			System.out.println(e.getMessage());
		}

		CheckTitle.printCouponsTable(customerFacade.getCustomerCoupons());

		CheckTitle.printTestLine("Customer Facade - !WRONG! purchase coupon");

		Coupon coupon2 = couponsDBDAO.getOneCoupon(10);

		try {
			customerFacade.purchaseCoupon(coupon2);
		} catch (PurchasedCouponException e) {
			System.out.println(e.getMessage());
		}

		CheckTitle.printCouponsTable(customerFacade.getCustomerCoupons());

		CheckTitle.printTestLine("Customer Facade - get customer coupons");

		CheckTitle.printCouponsTable(customerFacade.getCustomerCoupons());

		CheckTitle.printTestLine("Customer Facade - get customer coupons by category");

		CheckTitle.printCouponsTable(customerFacade.getCustomerCouponsByCategory(Category.Electricity));

		CheckTitle.printTestLine("Customer Facade - !WRONG! get customer coupons by category");

		CheckTitle.printCouponsTable(customerFacade.getCustomerCouponsByCategory(Category.Food));

		CheckTitle.printTestLine("Customer Facade - get customer coupons by maxPrice");

		CheckTitle.printCouponsTable(customerFacade.getCustomerCouponsByMaxPrice(89));

		CheckTitle.printTestLine("Customer Facade - !WRONG! get customer coupons by maxPrice");

		CheckTitle.printCouponsTable(customerFacade.getCustomerCouponsByMaxPrice(4.99));

		CheckTitle.printTestLine("Customer Facade - get customer details");
		CheckTitle.printOneCustomer(customerFacade.getCustomerDetails());

		CheckTitle.separatorLine();

	}

	public static void doTheMagic() {
		companiesDBDAO();
		customerDBDAO();
		couponsDBDAO();
		adminFacade();
		companyFacade();
		customerFacade();
	}

}
