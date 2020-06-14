package Test;

import java.sql.SQLException;

import com.NoamMarciano.beans.Category;
import com.NoamMarciano.beans.Company;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.dbdao.CategoryDBDAO;
import com.NoamMarciano.dbdao.CompaniesDBDAO;
import com.NoamMarciano.dbdao.CouponsDBDAO;
import com.NoamMarciano.dbdao.CustomerDBDAO;
import com.NoamMarciano.utils.ConnectionPool;
import com.NoamMarciano.utils.Database;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, SQLException {
		System.out.println("START");
		// ConnectionPool.getInstance();
		// STEP 1 - Loading JDBC Driver - Of MySQL
		Class.forName("com.mysql.cj.jdbc.Driver");
//		CompaniesDBDAO myUserDBDAO = new CompaniesDBDAO();

		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
		CustomerDBDAO customerDBDAO = new CustomerDBDAO();
		CategoryDBDAO categoryDBDAO = new CategoryDBDAO();
		CouponsDBDAO couponsDBDAO = new CouponsDBDAO();

//		Database.createAllTabales();


//		Company c1 = new Company("SomeCompany-1", "company1@company.com", "1234", null);
		Company c2 = new Company("SomeCompany-2", "company2@company.com", "1234", null);
//		Company c3 = new Company("SomeCompany-3", "company3@company.com", "1234", null);
//		Company c4 = new Company("SomeCompany-4", "company4@company.com", "1234", null);
//		Company c5 = new Company("SomeCompany-5", "company5@company.com", "1234", null);

//		Customer cu1 = new Customer("firstName-1", "lastName-1", "customer1@customer.com", "1234");
//		Customer cu2 = new Customer("firstName-2", "lastName-2", "customer2@customer.com", "1234");
//		Customer cu3 = new Customer("firstName-3", "lastName-3", "customer3@customer.com", "1234");
//		Customer cu4 = new Customer("firstName-4", "lastName-4", "customer4@customer.com", "1234");
//		Customer cu5 = new Customer("firstName-5", "lastName-5", "customer5@customer.com", "1234");
		
		
//		companiesDBDAO.addCompany(c1);
//		companiesDBDAO.addCompany(c2);
//		companiesDBDAO.addCompany(c3);
//		companiesDBDAO.addCompany(c4);
//		companiesDBDAO.addCompany(c5);

//		customerDBDAO.addCustomer(cu1);
//		customerDBDAO.addCustomer(cu2);
//		customerDBDAO.addCustomer(cu3);
//		customerDBDAO.addCustomer(cu4);
//		customerDBDAO.addCustomer(cu5);
		
		

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

		ConnectionPool.getInstance().closeAllConnection();
		System.out.println("END");

	}
}
