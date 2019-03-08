package com.cg.payroll.controllers;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailNotFoundException;
import com.cg.payroll.services.PayrollServices;

@Controller
public class PayrollServiceControllers {
  @Autowired
   private PayrollServices payrollServices;
  @RequestMapping("/registerAssociate")//action value in registrationPageform in view folder
   public ModelAndView registerAssociate(@Valid@ModelAttribute Associate associate, BindingResult result) {
   if(result.hasErrors()) return new ModelAndView("RegistrationPage");  
   associate = payrollServices.acceptAssociateDetails(associate);
   return new ModelAndView("RegistrationSuccessPage", "associate", associate);
		}
   @RequestMapping("/findAssociate")
   public ModelAndView getAssociateDetails(@RequestParam int associateId)throws  AssociateDetailNotFoundException{
   Associate associate=payrollServices.getAssociateDetails(associateId);
   return new ModelAndView("findAssociateDetails","associate",associate);
        }
   @RequestMapping("/netSalary")
    public ModelAndView calculateNetSalary(@RequestParam int associateId)throws AssociateDetailNotFoundException{
	Associate associate=payrollServices.calculateNetSalary(associateId);
	return new ModelAndView("ShowNetSalary","associate",associate);
       }
   @RequestMapping("/allAssociate")
    public ModelAndView allAssociate() {
	java.util.List<Associate> associate= payrollServices.getAllAssociatesDetails();
	return new ModelAndView("ShowAllAssociate", "associate", associate);
       }
}


