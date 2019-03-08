
package com.cg.payroll.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.daoservice.AssociateDAO;
import com.cg.payroll.exceptions.AssociateDetailNotFoundException;

@Component("payrollServices")
public class PayrollServicesImpl implements PayrollServices {
	@Autowired
	AssociateDAO associateDAO;
	@Override
	public int acceptAssociateDetails(String firstName, String lastName, String emailId, String department,
		String designation, String pancard, int yearlyInvestmentUnder8oC, int basicSalary, int epf, int companyPf,
		long accountNumber, String bankName, String ifscCode) {
		Associate associate = new Associate(yearlyInvestmentUnder8oC,firstName,lastName,department,designation,pancard,emailId,new Salary(basicSalary, epf, companyPf), new BankDetails(accountNumber, bankName, ifscCode));
		associate = associateDAO.save(associate);
		return  associate.getAssociateId();
	}
	public Associate calculateNetSalary(int associateId) throws AssociateDetailNotFoundException{
		Associate associate=calculateTax(associateId);
	    double netSalary=(associate.salary.getCalculateTaxableAmount()-(associate.salary.getMonthlyTax()*12)+associate.salary.getEpf()+associate.salary.getCompanyPf()+associate.getYearlyInvestmentUnder80C());
	    associate.salary.setNetSalary(netSalary);
	    return associateDAO.save(associate);
	}
	public Associate calculateTax(int associateId) throws AssociateDetailNotFoundException {
		double taxAmount =0;
		Associate associate=getAssociateDetails(associateId);
		associate.salary.setHra((associate.salary.getBasicSalary()*30/100));
		associate.salary.setConveyenceAllowance((associate.salary.getBasicSalary()*30/100));
		associate.salary.setOtherAllowance((associate.salary.getBasicSalary()*35/100));
		associate.salary.setPersonalAllowance(associate.salary.getBasicSalary()/5);
		associate.salary.setGrossSalary(associate.salary.getBasicSalary()+associate.salary.getConveyenceAllowance()+associate.salary.getHra()+associate.salary.getOtherAllowance()+associate.salary.getPersonalAllowance());
		double taxableAmount =(associate.salary.getGrossSalary()-associate.getYearlyInvestmentUnder80C());
		while(taxableAmount>250000) {
			if(taxableAmount>250000&&taxableAmount<=500000) {
				taxableAmount = taxableAmount - 250000;
				taxAmount = taxAmount+taxableAmount/10;		
			}
			if(taxableAmount>500000&&taxableAmount<=1000000) {
				taxableAmount=taxableAmount-500000;
				taxAmount =taxAmount+taxableAmount/20;
			}
			if(taxableAmount>1000000) {
				taxableAmount = taxableAmount-1000000;
				taxAmount=taxAmount+taxableAmount/30;
			}
		}
		associate.salary.setMonthlyTax(taxAmount/12);
		associate.salary.setCalculateTaxableAmount(taxableAmount);
		return associate;}
	
	@Override
	public Associate getAssociateDetails(int associateId) throws AssociateDetailNotFoundException {
		// TODO Auto-generated method stub
		Associate associate = associateDAO.findById(associateId).orElseThrow(()-> new AssociateDetailNotFoundException("Assoicate details not found for this id"));
		 return associate;}
	
	@Override
	public List<Associate> getAllAssociatesDetails() {
		return associateDAO.findAll();
	}
}
