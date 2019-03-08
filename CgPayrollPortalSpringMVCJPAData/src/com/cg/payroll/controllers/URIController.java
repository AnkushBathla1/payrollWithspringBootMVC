package com.cg.payroll.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.payroll.beans.Associate;

@Controller
public class URIController {

private Associate associate;	
@RequestMapping(value={"/","Index"})
public String getIndexPage() {
	return "IndexPage";
}
@RequestMapping("/registration")
public String registerAssociate() {
	return "RegistrationPage";
	}
@RequestMapping("/findAssociateDetails")
public String findAssociate() {
	return "findAssociateDetails";
}
@RequestMapping("/calculateNetSalary")
public String calculateNetSalary(){
	return "NetSalary";
}

@ModelAttribute
public Associate getAssociate() {
	associate=new Associate();
	return associate;
}
}
