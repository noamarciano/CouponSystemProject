package com.NoamMarciano.main;

import java.util.Date;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.beans.Company;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.dao.CompaniesDAO;
import com.NoamMarciano.dbdao.CompaniesDBDAO;
import com.NoamMarciano.dbdao.CouponsDBDAO;
import com.NoamMarciano.dbdao.CustomerDBDAO;
import com.NoamMarciano.exception.CompanyAlreadyExistException;
import com.NoamMarciano.exception.EmailAlreadyExistException;
import com.NoamMarciano.exception.LoginDeniedException;
import com.NoamMarciano.facade.AdminFacade;
import com.NoamMarciano.security.ClientType;
import com.NoamMarciano.security.LoginManager;
import com.NoamMarciano.utils.CheckTitle;

public class TestAll {

	static CheckTitle checkTitle = new CheckTitle();

	public static void companiesDBDAO() {

		checkTitle.companiesDBDAOcheck();

		CompaniesDAO companiesDBDAO = new CompaniesDBDAO();

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

		System.out.println("--------add companies--------");
		companiesDBDAO.addCompany(c1);
		companiesDBDAO.addCompany(c2);
		companiesDBDAO.addCompany(c3);
		companiesDBDAO.getAllCompanies();
		System.out.println();

		System.out.println("--------check if company exist--------");
		companiesDBDAO.isCompanyExists(c1.getEmail(), c1.getPassword());
		System.out.println();

		System.out.println("--------update company--------");// TODO need to fix the update+the wrong update
		c2.setPassword("4321");
		companiesDBDAO.updateCompany(c2);
		companiesDBDAO.getOneCompany(2);
		System.out.println();

		System.out.println("--------delete company--------");
		companiesDBDAO.deleteCompany(1);
		companiesDBDAO.getAllCompanies();
		System.out.println();

		System.out.println("--------get all companies--------");
		companiesDBDAO.getAllCompanies();
		System.out.println();

		System.out.println("--------get one comapny--------");
		companiesDBDAO.getOneCompany(2);
		System.out.println();

		System.out.println("--------try to get company that not exist--------");// check for wrong insert
		companiesDBDAO.getOneCompany(5);

		checkTitle.printStars();
	}

	public static void customerDBDAO() {
		CustomerDBDAO customerDBDAO = new CustomerDBDAO();
		checkTitle.customersDBDAOcheck();

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

		System.out.println("--------add customers--------");
		customerDBDAO.addCustomer(cu1);
		customerDBDAO.addCustomer(cu2);
		customerDBDAO.addCustomer(cu3);
		customerDBDAO.getAllCustomers();
		System.out.println();

		System.out.println("--------check if customer exist--------");
		customerDBDAO.isCustomerExists(cu1.getEmail(), cu1.getPassword());
		System.out.println();

		System.out.println("--------update customer--------");// TODO need to fix the update+the wrong update
		cu2.setPassword("4321");
		customerDBDAO.updateCustomer(2, cu2);
		customerDBDAO.getOneCustomer(2);
		System.out.println();

		System.out.println("--------delete customer--------");
		customerDBDAO.deleteCustomer(1);
		customerDBDAO.getOneCustomer(1);
		System.out.println();

		System.out.println("--------get all customers--------");
		customerDBDAO.getAllCustomers();
		System.out.println();

		System.out.println("--------get one customer--------");
		customerDBDAO.getOneCustomer(3);
		System.out.println();

		System.out.println("--------try to get customer that not exist--------");// check for wrong insert
		customerDBDAO.getOneCustomer(9);

		checkTitle.printStars();

	}

	public static void couponsDBDAO() {
		CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
		checkTitle.couponsDBDAOcheck();

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

		System.out.println("--------add coupons--------");
		couponsDBDAO.addCoupon(cou1);
		couponsDBDAO.addCoupon(cou2);
		couponsDBDAO.addCoupon(cou3);
		couponsDBDAO.getAllCoupons();
		System.out.println();

		System.out.println("--------update coupon--------");// TODO need to fix the update+the wrong update
		cou2.setAmount(200);
		cou2.setPrice(79);
		couponsDBDAO.updateCoupon(cou2);
		couponsDBDAO.getOneCoupon(2);
		System.out.println();

		System.out.println("--------delete coupon--------");
		couponsDBDAO.deleteCoupon(cou1);
		couponsDBDAO.getOneCoupon(1);
		System.out.println();

		System.out.println("--------get all coupons--------");
		couponsDBDAO.getAllCoupons();
		System.out.println();

		System.out.println("--------get one coupon--------");
		couponsDBDAO.getOneCoupon(3);
		System.out.println();

		System.out.println("--------try to get coupon that not exist--------");// check for wrong insert
		couponsDBDAO.getOneCoupon(9);

		checkTitle.printStars();
	}

	public static void adminFacade() throws EmailAlreadyExistException, CompanyAlreadyExistException {

		AdminFacade adminFacade = new AdminFacade();
		checkTitle.adminFacadeCheck();

//			checkTitle.printCheckName("Fake info test");
//			adminFacade = (AdminFacade) LoginManager.getInstance().login("admin111@admin.com", "admin",
//					ClientType.Administrator);
			checkTitle.printCheckName("Real info test");
			try {
				adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin",
						ClientType.Administrator);
			} catch (LoginDeniedException e) {
				System.out.println(e.getMessage());
			}
			
			checkTitle.printCheckName("Admin Facae - add companies ");
			Company c4 = new Company();
			c4.setName("Hilton");
			c4.setEmail("hilton@company.com");
			c4.setPassword("1234");

			Company c5 = new Company();
			c5.setName("Herodes");
			c5.setEmail("herodes@company.com");
			c5.setPassword("1234");
			
			adminFacade.addCompany(c4);
			adminFacade.addCompany(c5);
			System.out.println(adminFacade.getOneCompany(4));
			System.out.println(adminFacade.getOneCompany(5));
			System.out.println();
			
			checkTitle.printCheckName("Admin Facae - add !WRONG! companies ");
			Company c6 = new Company();
			c6.setName("Hilton");
			c6.setEmail("hilton111@company.com");
			c6.setPassword("1234");
			
			Company c7 = new Company();
			c7.setName("Hilton111");
			c7.setEmail("hilton@company.com");
			c7.setPassword("1234");
			
			adminFacade.addCompany(c6);
			adminFacade.addCompany(c7);
			System.out.println(adminFacade.getOneCompany(6));
			System.out.println(adminFacade.getOneCompany(7));
			System.out.println();
			
			checkTitle.printCheckName("Admin Facae - update company ");
			c4.setPassword("4321");
			adminFacade.updateCompany(c4);
			System.out.println(adminFacade.getOneCompany(4));
			System.out.println();
			
			checkTitle.printCheckName("Admin Facae - !WRONG! update company ");// TODO need to fix the update method
			c4.setId(123);
			c4.setName("New Hilton");
			adminFacade.updateCompany(c4);
			System.out.println();
			
			checkTitle.printCheckName("Admin Facae - add customers ");
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
			
			adminFacade.addCustomer(cu4);
			adminFacade.addCustomer(cu5);
			System.out.println(adminFacade.getOneCustomer(4));
			System.out.println(adminFacade.getOneCustomer(5));
			System.out.println();
			
			checkTitle.printCheckName("Admin Facae - !WRONG! add customers ");
			
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
			
			adminFacade.addCustomer(cu6);
			adminFacade.addCustomer(cu7);
//			System.out.println(adminFacade.getOneCustomer(6));
//			System.out.println(adminFacade.getOneCustomer(7));
			System.out.println();
			
			checkTitle.printCheckName("Admin Facae - get all companies ");
			System.out.println(adminFacade.getAllCompanies());
			System.out.println();
			
			checkTitle.printCheckName("Admin Facae - get all customers ");
			System.out.println(adminFacade.getAllCustomers());
			System.out.println();
			
			checkTitle.printCheckName("Admin Facae - get one company ");
			System.out.println(adminFacade.getOneCompany(2));
			System.out.println();
			
			checkTitle.printCheckName("Admin Facae - !WRONG! get one company ");
			System.out.println(adminFacade.getOneCompany(9));
			System.out.println();
			
			checkTitle.printCheckName("Admin Facae - get one customer ");
			System.out.println(adminFacade.getOneCustomer(2));
			System.out.println();
			
			checkTitle.printCheckName("Admin Facae - !WRONG! get one customer ");
			System.out.println(adminFacade.getOneCustomer(9));
			System.out.println();
			
			checkTitle.printStars();
			
		}

	}


//Coupon cou4 = new Coupon();
//cou4.setCompanyID(4);
//cou4.setCategory(Category.Vacation);
//cou4.setTitle("10%");
//cou4.setDescription("book a room ang get 10% off");
//cou4.setStartDate(new Date(2020, 07, 20));
//cou4.setEndDate(new Date(2020, 10, 07));
//cou4.setAmount(70);
//cou4.setPrice(250);
//cou4.setImage("img.com");
