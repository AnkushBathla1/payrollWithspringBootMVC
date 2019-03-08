package com.cg.payroll.client;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.payroll.exceptions.AssociateDetailNotFoundException;
import com.cg.payroll.services.PayrollServices;

public class MainClass {
	
	
	public static  Scanner sc = new Scanner(System.in);
	private static ApplicationContext context;
	public static void main(String[] args) throws AssociateDetailNotFoundException {
		
		mainScreen();
    int userChoice=sc.nextInt();
	startMenu(userChoice);
	}
	public static void startMenu(int userChoice) throws AssociateDetailNotFoundException{
		context = new ClassPathXmlApplicationContext("payrollBeans.xml");
		PayrollServices services=(PayrollServices) context.getBean("payrollServices");
		switch(userChoice) {
		case 1:    System.out.println("Enter first name:");
		                String firstName = sc.next();
		                System.out.println("Enter last name:");
		                String lastName  = sc.next();
		                System.out.println("Enter department:");
		            	String department = sc.next();
		                System.out.println("Enter your pan number:");
		                String panCard = sc.next();
		                System.out.println("Enter designation:");
		                String designation = sc.next();
		                System.out.println("Enter Email:");
		                String emailId = sc.next();
						System.out.println("Enter basicSalary:");
						int basicSalary = sc.nextInt();
						System.out.println("Enter epf:");
						int epf =sc.nextInt();
						System.out.println("Enter company PF:");
						int companypf=sc.nextInt();
						System.out.println("Enter yearly investment under 80C:");
						int yearlyInvestmentUnder80C= sc.nextInt();
						System.out.println("Enter Bank Name:");
						String bankName = sc.next();
						System.out.println("Enter Account number:");
						long accountNo = sc.nextLong();
						System.out.println("Enter IFSC:");
						String ifsc = sc.next();
						int associate = services.acceptAssociateDetails(firstName, lastName, emailId, department, designation, panCard, yearlyInvestmentUnder80C, basicSalary, epf, companypf, accountNo, bankName, ifsc);
						System.out.println("******ASSOCIATE ADDED SUCCESSFULLY******");
						System.out.println(associate);
						break;
		case 2:System.out.println("Enter Associate Id:");
					    try{
					    int associateId=sc.nextInt(); 
					    System.out.println(services.calculateNetSalary(associateId));	
					    }catch(AssociateDetailNotFoundException e) { 
					        e.printStackTrace();
					        main(null);}
						break;
		case 3: System.out.println(services.getAllAssociatesDetails());
		              break;
		case 4: System.out.println("Enter Associate Id:");
	    try{
	    int associateId=sc.nextInt(); 			    	 
	    System.out.println(services.getAssociateDetails(associateId));	
	    }catch(AssociateDetailNotFoundException e) { 
	        e.printStackTrace();
	        main(null);}
		              break;
		default: 
						System.out.println("Invalid Choice,Please Try Again!!!!!!");
		}
		System.out.println("Do you want to continue ?");
		System.out.println("1. Yes,Continue");
		System.out.println("2. No");
		int choice =sc.nextInt();
		if(choice==2)
			System.exit(0);
		main(null);
	}
	public static void mainScreen(){
		System.out.println("__________________________________PayRoll Management System.__________________________________");
		System.out.println("Please enter any one of the given choices :");
		System.out.println("1. Add associate");
		System.out.println("2. Calculate Net Salary");
		System.out.println("3. Get all Associates ");
		System.out.println("4. Get Particular Associate");
	}
	
	
	}


