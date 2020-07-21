package com.NoamMarciano.dao;

import java.util.List;

import com.NoamMarciano.beans.Company;

public interface CompaniesDAO {

	boolean isCompanyExists(String email, String Password);

	void addCompany(Company company);

	void updateCompany(Company company);

	void deleteCompany(int companyID);

	List<Company> getAllCompanies();

	Company getOneCompany(int companyID);

}
