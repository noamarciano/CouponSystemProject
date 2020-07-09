package com.NoamMarciano.facade;

import com.NoamMarciano.dbdao.CompaniesDBDAO;
import com.NoamMarciano.dbdao.CouponsDBDAO;
import com.NoamMarciano.dbdao.CustomerDBDAO;
import com.NoamMarciano.exception.LoginDeniedException;

public abstract class ClientFacade {

	protected CompaniesDBDAO companiesDBDAO;
	protected CustomerDBDAO customerDBDAO;
	protected CouponsDBDAO couponsDBDAO;

	public ClientFacade() {
		this.companiesDBDAO = new CompaniesDBDAO();
		this.customerDBDAO = new CustomerDBDAO();
		this.couponsDBDAO = new CouponsDBDAO();
	}

	public abstract boolean login(String email, String password) throws LoginDeniedException;

}
