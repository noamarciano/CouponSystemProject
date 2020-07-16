package com.NoamMarciano.security;

import com.NoamMarciano.exception.LoginDeniedException;
import com.NoamMarciano.facade.AdminFacade;
import com.NoamMarciano.facade.ClientFacade;
import com.NoamMarciano.facade.CompanyFacade;
import com.NoamMarciano.facade.CustomerFacade;

public class LoginManager {

	private static LoginManager instance = null;
	private ClientFacade clientFacade;

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
//		AdminFacade adminFacade = new AdminFacade();
//		CompanyFacade companyFacade = new CompanyFacade();
//		CustomerFacade customerFacade = new CustomerFacade();

		switch (clientType) {
		case Administrator:
			clientFacade = (ClientFacade) new AdminFacade();
			if (clientFacade.login(email, password)) {
				System.out.println("Admin Facade - Login successful");
				return clientFacade;
			} else {
				return null;
			}
		case Company:
			clientFacade = (ClientFacade) new CompanyFacade();
			if (clientFacade.login(email, password)) {
				System.out.println("Company Facade - Login successful");
				return clientFacade;
			} else {
				return null;
			}

		case Customer:
			clientFacade = (ClientFacade) new CustomerFacade();
			if (clientFacade.login(email, password)) {
				System.out.println("Cuatomer Facade - Login successful");
				return clientFacade;
			} else {
				return null;
			}
		default:
			clientFacade = null;
			System.out.println("One or more details are wrong, please try again..");
			break;
		}
		return null;

	}

}
