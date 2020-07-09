package Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.beans.Company;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.dao.CategoryDAO;
import com.NoamMarciano.dao.CompaniesDAO;
import com.NoamMarciano.dao.CouponsDAO;
import com.NoamMarciano.dao.CustomerDAO;
import com.NoamMarciano.dbdao.CategoryDBDAO;
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
import com.NoamMarciano.facade.AdminFacade;
import com.NoamMarciano.facade.CompanyFacade;
import com.NoamMarciano.facade.CustomerFacade;
import com.NoamMarciano.utils.ConnectionPool;
import com.NoamMarciano.utils.Database;

public class Test {

	public static void insertDataToTables()
			throws CompanyAlreadyExistException, EmailAlreadyExistException, LoginDeniedException,
			CouponTitleAlreadyExistException, CouponAlreadyPurchasedException, CouponNotAvailableException {
		CompaniesDAO companiesDBDAO = new CompaniesDBDAO();
		CustomerDAO customerDBDAO = new CustomerDBDAO();
		CouponsDAO couponsDBDAO = new CouponsDBDAO();
		CategoryDAO categoryDBDAO = new CategoryDBDAO();

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

		Company c4 = new Company();
		c4.setName("Hilton");
		c4.setEmail("hilton@company.com");
		c4.setPassword("1234");

//		companiesDBDAO.addCompany(c1);
//		companiesDBDAO.addCompany(c2);
//		companiesDBDAO.addCompany(c3);
//		companiesDBDAO.addCompany(c4);

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

		Customer cu4 = new Customer();
		cu4.setFirstName("Dana");
		cu4.setLastName("Levi");
		cu4.setEmail("dana@gmail.com");
		cu4.setPassword("1234");

//		customerDBDAO.addCustomer(cu1);
//		customerDBDAO.addCustomer(cu2);
//		customerDBDAO.addCustomer(cu3);
//		customerDBDAO.addCustomer(cu4);

		Coupon cou1 = new Coupon();

		cou1.setCompanyID(1);
		cou1.setCategory(Category.Food);
		cou1.setTitle("1+1");
		cou1.setDescription("buy one, get one");
		cou1.setStartDate(new Date(2020, 06, 23));
		cou1.setEndDate(new Date(2021, 03, 11));
		cou1.setAmount(100);
		cou1.setPrice(59.9);
		cou1.setImage("img.com");

		Coupon cou2 = new Coupon();

		cou2.setCompanyID(2);
		cou2.setCategory(Category.Electricity);
		cou2.setTitle("50%");
		cou2.setDescription("discount 50%");
		cou2.setStartDate(new Date(2020, 02, 15));
		cou2.setEndDate(new Date(2020, 04, 12));
		cou2.setAmount(100);
		cou2.setPrice(39.9);
		cou2.setImage("img.com");

		Coupon cou3 = new Coupon();

		cou3.setCompanyID(3);
		cou3.setCategory(Category.Restaurant);
		cou3.setTitle("happy hour");
		cou3.setDescription("buy one, get two");
		cou3.setStartDate(new Date(2020, 06, 30));
		cou3.setEndDate(new Date(2020, 07, 05));
		cou3.setAmount(50);
		cou3.setPrice(50);
		cou3.setImage("img.com");

		Coupon cou4 = new Coupon();

		cou4.setCompanyID(4);
		cou4.setCategory(Category.Vacation);
		cou4.setTitle("10%");
		cou4.setDescription("book a room ang get 10% off");
		cou4.setStartDate(new Date(2020, 07, 20));
		cou4.setEndDate(new Date(2020, 10, 07));
		cou4.setAmount(70);
		cou4.setPrice(250);
		cou4.setImage("img.com");

//		couponsDBDAO.addCoupon(cou1);
//		couponsDBDAO.addCoupon(cou2);
//		couponsDBDAO.addCoupon(cou3);
//		couponsDBDAO.addCoupon(cou4);

		AdminFacade adminFacade = new AdminFacade();
//		adminFacade.login("admin@admin.com", "admin");
//		Company c5 = new Company();
//		c5.setName("bla");
//		c5.setEmail("bla@company.com");
//		c5.setPassword("1234");
		adminFacade.addCompany(c1);
		adminFacade.addCompany(c2);
		adminFacade.addCompany(c3);
		adminFacade.addCompany(c4);

		adminFacade.addCustomer(cu1);
		adminFacade.addCustomer(cu2);
		adminFacade.addCustomer(cu3);
		adminFacade.addCustomer(cu4);

		System.out.println(adminFacade.getAllCompanies());
		System.out.println(adminFacade.getAllCustomers());
		System.out.println(adminFacade.getOneCompany(1));
		System.out.println(adminFacade.getOneCustomer(1));

//		c1.setEmail("osem11111@company.com");
//		adminFacade.updateCompany(c1);
		adminFacade.deleteCompany(2);
		cu1.setEmail("koby1@gmail.com");
		adminFacade.updateCustomer(cu1);
		adminFacade.deleteCustomer(2);
		System.out.println("**********************");
		System.out.println(adminFacade.getAllCompanies());
		System.out.println(adminFacade.getAllCustomers());

		System.out.println("********************");

		CompanyFacade companyFacade = new CompanyFacade();
		companyFacade.login("osem@company.com", "1234");
		companyFacade.setCompanyID(1);
		companyFacade.addCoupon(cou1);
		companyFacade.addCoupon(cou2);
		companyFacade.addCoupon(cou3);
		companyFacade.addCoupon(cou4);

		System.out.println(companyFacade.getCompanyCoupons());
		System.out.println(companyFacade.getCompanyCoupons(cou1.getCategory()));
		System.out.println(companyFacade.getCompanyCoupons());
		System.out.println(companyFacade.getCompanyDetails());

		System.out.println("***********************");

//		cou1.setTitle("fdgdfg");
//		companyFacade.updateCoupon(cou1);
//		companyFacade.deleteCoupon(2);

//		System.out.println(companyFacade.getCompanyCoupons());
//		System.out.println(companyFacade.getCompanyDetails());

		System.out.println("*****************");

		CustomerFacade customerFacade = new CustomerFacade();
		customerFacade.login(cu4.getEmail(), cu4.getPassword());
		customerFacade.purchaseCoupon(cou1);
		customerFacade.purchaseCoupon(cou2);
		customerFacade.purchaseCoupon(cou3);
		customerFacade.purchaseCoupon(cou4);

		System.out.println(customerFacade.getCustomerCoupons());
		System.out.println(customerFacade.getCustomerCoupons(Category.Food));
		System.out.println(customerFacade.getCustomerCoupons(60));
		System.out.println(customerFacade.getCustomerDetails());
	}

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, SQLException,
			CompanyAlreadyExistException, EmailAlreadyExistException, LoginDeniedException,
			CouponTitleAlreadyExistException, CouponAlreadyPurchasedException, CouponNotAvailableException {
		System.out.println("START");
		// ConnectionPool.getInstance();
		// STEP 1 - Loading JDBC Driver - Of MySQL
		Class.forName("com.mysql.cj.jdbc.Driver");
//		CompaniesDBDAO myUserDBDAO = new CompaniesDBDAO();

		Database.dropAllTabales();
		Database.createAllTabales();
		insertDataToTables();

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
