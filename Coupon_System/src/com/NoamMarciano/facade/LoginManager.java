package com.NoamMarciano.facade;

import com.NoamMarciano.exception.LoginDeniedException;

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
			return adminFacade;

		case Company:
			companyFacade.login(email, password);
			return companyFacade;

		case Customer:
			customerFacade.login(email, password);
			return customerFacade;
		default:
			System.out.println("One or more details are wrong, please try again..");
			break;
		}
		return null;

	}

}
