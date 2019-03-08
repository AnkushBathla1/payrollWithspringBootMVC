package com.cg.payroll.aspect;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.cg.payroll.exceptions.AssociateDetailNotFoundException;

@ControllerAdvice
public class PayrollExceptionAspect {
@ExceptionHandler(AssociateDetailNotFoundException.class)
public ModelAndView handelAssociateDetailsNotFoundException(Exception e) {
	System.out.println(e.getMessage());
	return new ModelAndView("findAssociateDetails", "errorMessage", e.getMessage());
}
}
