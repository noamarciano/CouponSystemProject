package com.NoamMarciano.dao;

import java.util.List;

import com.NoamMarciano.beans.Customer;

public interface CustomerDAO {

	boolean isCustomerExists(String email, String Password);

	void addCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(int customerID);

	List<Customer> getAllCustomers();

	Customer getOneCustomer(int customerID);

}
