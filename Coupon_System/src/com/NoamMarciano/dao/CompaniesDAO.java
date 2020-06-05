package com.NoamMarciano.dao;

import java.util.List;

import com.NoamMarciano.beans.Company;

public interface CompaniesDAO {

	boolean isCompanyExists(String email, String Password);

	void addCompany(Company company);
	
	void updateCompany(int id, Company company);
	
	void deleteCompany(int id, Company company);
	
	List<Company> getAllCompanies();
	
	Company getOneCompany(int companyID);
	
}
