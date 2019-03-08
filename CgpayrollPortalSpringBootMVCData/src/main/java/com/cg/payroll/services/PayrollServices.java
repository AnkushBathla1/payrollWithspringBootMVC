package com.cg.payroll.services;

import java.util.List;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailNotFoundException;

public interface PayrollServices {
	Associate acceptAssociateDetails(Associate associate);
	
	Associate calculateNetSalary(int associateId) throws AssociateDetailNotFoundException;
	Associate getAssociateDetails(int associateId) throws AssociateDetailNotFoundException;
	List<Associate> getAllAssociatesDetails();
	Associate calculateTax(int associateId) throws AssociateDetailNotFoundException;
}