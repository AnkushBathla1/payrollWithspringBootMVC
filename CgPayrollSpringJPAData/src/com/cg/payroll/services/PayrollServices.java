package com.cg.payroll.services;

import java.util.List;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailNotFoundException;

public interface PayrollServices {
	int acceptAssociateDetails(String firstName, String lastName, String emailId, String department, 
			String designation, String pancard, int yearlyInvestmentUnder8oC, int basicSalary, 
			int epf, int companyPf, long accountNumber,String bankName, String ifscCode);
	
	Associate calculateNetSalary(int associateId) throws AssociateDetailNotFoundException;
	Associate getAssociateDetails(int associateId) throws AssociateDetailNotFoundException;
	List<Associate> getAllAssociatesDetails();
	Associate calculateTax(int associateId) throws AssociateDetailNotFoundException;
}