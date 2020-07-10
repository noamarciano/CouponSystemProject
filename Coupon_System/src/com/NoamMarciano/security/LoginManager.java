package com.NoamMarciano.security;

import com.NoamMarciano.exception.LoginDeniedException;
import com.NoamMarciano.facade.AdminFacade;
import com.NoamMarciano.facade.ClientFacade;
import com.NoamMarciano.facade.CompanyFacade;
import com.NoamMarciano.facade.CustomerFacade;

public class LoginManager {

	private static LoginManager instance = null;

	private LoginManager() {

	}

	public static LoginManager getInstance() {
		if (instance == null) {
			synchronized (LoginManager.class) {
				if (instance == null) {
					instance = new LoginManager();
				}
			}
		}
		return instance;
	}

	public ClientFacade login(String email, String password, ClientType clientType) throws LoginDeniedException {
		AdminFacade adminFacade = new AdminFacade();
		CompanyFacade companyFacade = new CompanyFacade();
		CustomerFacade customerFacade = new CustomerFacade();

		switch (clientType) {
		case Administrator:
			adminFacade.login(email, password);
			System.out.println("Admin Facade - Login successful");
			return adminFacade;

		case Company:
			companyFacade.login(email, password);
			System.out.println("Company Facade - Login successful");
			return companyFacade;

		case Customer:
			customerFacade.login(email, password);
			System.out.println("Customer Facade - Login successful");
			return customerFacade;
		default:
			System.out.println("One or more details are wrong, please try again..");
			break;
		}
		return null;

	}

}
