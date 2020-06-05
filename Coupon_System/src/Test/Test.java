package Test;
import java.sql.SQLException;

import com.NoamMarciano.beans.Company;
import com.NoamMarciano.dbdao.CompaniesDBDAO;
import com.NoamMarciano.utils.ConnectionPool;
import com.NoamMarciano.utils.Database;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, SQLException {
		System.out.println("START");
		// ConnectionPool.getInstance();
		// STEP 1 - Loading JDBC Driver - Of MySQL
		Class.forName("com.mysql.cj.jdbc.Driver");
//		CompaniesDBDAO myUserDBDAO = new CompaniesDBDAO();
		Database.createAllTabales();
		
		
		
		
//		Database.createTableCompanies();
//
//		Company u1 = new Company(1, "Zara", "zara@zara.com", "1234", null);
////		User u2 = new User(2, "Cohen", "Rinat");
////		User u3 = new User(3, "Cohen", "Rina");
////		User u4 = new User(4, "Moshe", null);
//
//		myUserDBDAO.addCompany(u1);
//		myUserDBDAO.insertUser(u2);
//		u1.setFirst("Kobi");
//		myUserDBDAO.updateUser(1, u1);
//
//		myUserDBDAO.insertUser(u3);
//
//		System.out.println(myUserDBDAO.getAllUsers());
//
//		myUserDBDAO.deleteUser(3);
//
//		System.out.println(myUserDBDAO.getAllUsers());
//
		ConnectionPool.getInstance().closeAllConnection();
		System.out.println("END");

	}
}
