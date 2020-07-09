package com.NoamMarciano.facade;

import java.util.ArrayList;
import java.util.List;

import com.NoamMarciano.beans.Company;
import com.NoamMarciano.beans.Coupon;
import com.NoamMarciano.beans.Customer;
import com.NoamMarciano.exception.CompanyAlreadyExistException;
import com.NoamMarciano.exception.CompanyDoesNotExistException;
import com.NoamMarciano.exception.EmailAlreadyExistException;
import com.NoamMarciano.exception.LoginDeniedException;

public class AdminFacade extends ClientFacade {

	public AdminFacade() {
	}

	@Override
	public boolean login(String email, String password) throws LoginDeniedException {
		if ("admin@admin.com".equals(email) && "admin".equals(password)) {
			return true;
		}
		return false;
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

//	NEED TO FIX !!
	public void updateCompany(Company company) {

		companiesDBDAO.updateCompany(company);

	}

	public void deleteCompany(int companyID) {
		List<Coupon> coupons = couponsDBDAO.getAllCoupons();
		for (Coupon c : coupons) {
			if (c.getCompanyID() == companyID) {
				couponsDBDAO.deleteCouponPurchase(companyID, c.getId());
				couponsDBDAO.deleteCoupon(c);
			}
		}
		companiesDBDAO.deleteCompany(companyID);
	}

	public ArrayList<Company> getAllCompanies() {
		return (ArrayList<Company>) companiesDBDAO.getAllCompanies();
	}

	public Company getOneCompany(int companyID) {
		return companiesDBDAO.getOneCompany(companyID);
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
				couponsDBDAO.deleteCoupon(c);
				couponsDBDAO.deleteCouponPurchase(customerID, c.getId());
			}
		}
		customerDBDAO.deleteCustomer(customerID);
	}

	public ArrayList<Customer> getAllCustomers() {
		return (ArrayList<Customer>) customerDBDAO.getAllCustomers();
	}

	public Customer getOneCustomer(int customerID) {
		return customerDBDAO.getOneCustomer(customerID);
	}

}
