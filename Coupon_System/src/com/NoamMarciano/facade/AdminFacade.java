package com.NoamMarciano.facade;

import java.util.ArrayList;
import java.util.List;

import com.NoamMarciano.beans.Company;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.exception.CannotUpdateException;
import com.NoamMarciano.exception.CompanyAlreadyExistException;
import com.NoamMarciano.exception.EmailAlreadyExistException;
import com.NoamMarciano.exception.LoginDeniedException;

public class AdminFacade extends ClientFacade {

	public AdminFacade() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws LoginDeniedException {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;
		}
		throw new LoginDeniedException();
	}

	public void addCompany(Company company) throws CompanyAlreadyExistException {
		List<Company> companies = companiesDBDAO.getAllCompanies();
		for (Company c : companies) {
			if (c.getEmail().equals(company.getEmail()) || c.getName().equals(company.getName())) {
				throw new CompanyAlreadyExistException();
			}
		}
		companiesDBDAO.addCompany(company);
	}

	public void updateCompany(int companyID, Company company) throws CannotUpdateException {

		if (company.getId() == companyID) {
			companiesDBDAO.updateCompany(companyID, company);
			return;
		}
		throw new CannotUpdateException();
	}

	public void deleteCompany(int companyID) {
		List<Coupon> coupons = couponsDBDAO.getAllCouponsByCompanyID(companyID);
		List<Company> companies = companiesDBDAO.getAllCompanies();
		for (Coupon c : coupons) {
			if (coupons != null) {
				couponsDBDAO.deleteCouponPurchase(companyID, c.getId());
				couponsDBDAO.deleteCoupon(c.getId());
			}
		}
		for (Company company : companies) {
			if (company.getId() == companyID) {
				companiesDBDAO.deleteCompany(companyID);
				return;
			}
		}
		System.out.println("Sorry, There is no company with this ID (" + companyID + ") ");

	}

	public ArrayList<Company> getAllCompanies() {
		return (ArrayList<Company>) companiesDBDAO.getAllCompanies();
	}

	public Company getOneCompany(int companyID) {
		if (companiesDBDAO.getOneCompany(companyID) != null) {
			return companiesDBDAO.getOneCompany(companyID);
		} else {
			System.out.println("This company doesn't exist..");

		}
		return null;
	}

	public void addCustomer(Customer customer) throws EmailAlreadyExistException {
		List<Customer> customers = customerDBDAO.getAllCustomers();
		for (Customer c : customers) {
			if (c.getEmail().equals(customer.getEmail())) {
				throw new EmailAlreadyExistException();
			}
		}
		customerDBDAO.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) {
		customerDBDAO.updateCustomer(1, customer);
	}

	public void deleteCustomer(int customerID) {
		List<Coupon> coupons = couponsDBDAO.getAllCoupons();
		for (Coupon c : coupons) {
			if (c.getCompanyID() == customerID) {
				couponsDBDAO.deleteCoupon(c.getId());
				couponsDBDAO.deleteCouponPurchase(customerID, c.getId());
			}
		}
		customerDBDAO.deleteCustomer(customerID);
	}

	public ArrayList<Customer> getAllCustomers() {
		return (ArrayList<Customer>) customerDBDAO.getAllCustomers();
	}

	public Customer getOneCustomer(int customerID) {
		if (customerDBDAO.getOneCustomer(customerID) != null) {
			return customerDBDAO.getOneCustomer(customerID);
		} else {
			System.out.println("This customer doesn't exist..");
		}
		return null;
	}

}
