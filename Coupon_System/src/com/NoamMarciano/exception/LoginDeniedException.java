package com.NoamMarciano.exception;

public class LoginDeniedException extends Exception {

	public LoginDeniedException() {
		System.out.println("The email or the password are wrong, pls try again..");
	}

}
