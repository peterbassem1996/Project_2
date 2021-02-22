package Project_2;

import java.util.Scanner;
import java.util.StringTokenizer;

public class PayrollProcessing {
	
	private static final int MAX_NUM_OF_COMMAND_PARTS = 7;
    private static final int FIRST_ARG_INDEX = 0;
    private static final int SECOND_ARG_INDEX = 1;
    private static final int THIRD_ARG_INDEX = 2;
    private static final int FORTH_ARG_INDEX = 3;
    private static final int FIFTH_ARG_INDEX = 4;
    private static final int SIXTH_ARG_INDEX = 5;
    
    private void errorHandle(Employee employee) {
    	int errorCode = employee.getErrNo();
    	switch(errorCode) {
    	case Employee.NAME_ERR:
    		System.out.println("Name cannot be empty!");
    		break;
    	case Employee.DEP_ERR:
    		System.out.println(employee.getDepartment() + " is not a valid department!");
    		break;
    	case Employee.ANNUAL_SAL_ERR:
    		System.out.println("Salary cannot be negative!");
    		break;
    	case Employee.MANG_ERR:
    		System.out.println("Invalid management Code!");
    		break;
    	case Employee.RATE_ERR:
    		System.out.println("Pay rate cannot be negative!");
    		break;
    	case Employee.DATE_ERR:
    		System.out.println(employee.getDateHired() + " is not a valid Date!");
    		break;
    	case Employee.HOURS_ERR_NEG:
    		System.out.println("Working Hours cannot be negative!");
    		break;
    	case Employee.HOURS_ERR_EXCEED:
    		System.out.println("Invalid Hours: Over 100!");
    		break;
    	case Employee.NO_ERR:
    		System.out.println("Employee Not Found!");
    		break;
    	}
    }
	
    private boolean commandExecution(String command, Company company) {
        // variables used
        String[] commandParts = new String[MAX_NUM_OF_COMMAND_PARTS];
        StringTokenizer tokens = new StringTokenizer(command, " ");
        int counter = 0;

        // tokenizing the command into parts
        while (tokens.hasMoreTokens() && counter < MAX_NUM_OF_COMMAND_PARTS) {
            commandParts[counter] = tokens.nextToken();
            counter++;
        }
        
        Employee emp;

        if (commandParts[FIRST_ARG_INDEX] == null) {
            return true;
        } else if (commandParts[FIRST_ARG_INDEX].equals("AP")) {
        	
        	try {
        		emp = new PartTime(commandParts[SECOND_ARG_INDEX], commandParts[THIRD_ARG_INDEX], 
        				new Date(commandParts[FORTH_ARG_INDEX]), Double.parseDouble(commandParts[FIFTH_ARG_INDEX]));
        	}
        	catch(Exception e) {
        		System.out.println("The command doesn't match the form!");
        		return true;
        	}
        	
        	if(!company.add(emp)) {
        		errorHandle(emp);
        	}
        	else {
        		System.out.println("Employee added.");
        	}

        } else if (commandParts[FIRST_ARG_INDEX].equals("AF")) {
        	
        	try {
        		emp = new Fulltime(commandParts[SECOND_ARG_INDEX], commandParts[THIRD_ARG_INDEX], 
        				new Date(commandParts[FORTH_ARG_INDEX]), Double.parseDouble(commandParts[FIFTH_ARG_INDEX]));
        	}
        	catch(Exception e) {
        		System.out.println("The command doesn't match the form!");
        		return true;
        	}
        	
        	if(!company.add(emp)) {
        		errorHandle(emp);
        	}
        	else {
        		System.out.println("Employee added.");
        	}

        } else if (commandParts[FIRST_ARG_INDEX].equals("AM")) {
        	
        	try {
        		emp = new Manager(commandParts[SECOND_ARG_INDEX], commandParts[THIRD_ARG_INDEX], 
        				new Date(commandParts[FORTH_ARG_INDEX]), Double.parseDouble(commandParts[FIFTH_ARG_INDEX]), 
        				Integer.parseInt(commandParts[SIXTH_ARG_INDEX]));
        	}
        	catch(Exception e) {
        		System.out.println("The command doesn't match the form!");
        		return true;
        	}
        	
        	if(!company.add(emp)) {
        		errorHandle(emp);
        	}
        	else {
        		System.out.println("Employee added.");
        	}

        } else if (commandParts[FIRST_ARG_INDEX].equals("R")) {
        	
        	try {
        		emp = new Employee(commandParts[SECOND_ARG_INDEX], commandParts[THIRD_ARG_INDEX], 
        				new Date(commandParts[FORTH_ARG_INDEX]));
        	}
        	catch(Exception e) {
        		System.out.println("The command doesn't match the form!");
        		return true;
        	}
        	
        	if(!company.remove(emp)) {
        		errorHandle(emp);
        	}
        	else {
        		System.out.println("Employee removed.");
        	}

        } else if (commandParts[FIRST_ARG_INDEX].equals("C")) {
        	
        	company.processPayments();
        	System.out.println("Calculation of employee payments is done!");

        } else if (commandParts[FIRST_ARG_INDEX].equals("S")) {
        	
        	double hours;
        	
        	try {
        		emp = new Employee(commandParts[SECOND_ARG_INDEX], commandParts[THIRD_ARG_INDEX], 
        				new Date(commandParts[FORTH_ARG_INDEX]));
        		
        		hours = Double.parseDouble(commandParts[FIFTH_ARG_INDEX]);
        	}
        	catch(Exception e) {
        		System.out.println("The command doesn't match the form!");
        		return true;
        	}
        	
        	if (hours < 0) {
        		System.out.println("Working Hours cannot be negative!");
        		return true;
        	}
        	
        	else if (hours < 0) {
        		System.out.println("Invalid Hours: over 100!");
        		return true;
        	}
        	
        	if(!company.setHours(emp, hours)) {
        		errorHandle(emp);
        	}
        	else {
        		System.out.println("Hours Added.");
        	}

        } else if (commandParts[FIRST_ARG_INDEX].equals("PA")) {
        	
        	company.print();

        } else if (commandParts[FIRST_ARG_INDEX].equals("PH")) {
        	
        	company.printByDate();

        } else if (commandParts[FIRST_ARG_INDEX].equals("PD")) {
        	
        	company.printByDepartment();

        } else if (commandParts[FIRST_ARG_INDEX].equals("Q")) {
            System.out.println("Kiosk session ended.");
            return false;
        } else {
            System.out.println("Invalid Command!");
        }
        return true;
    }
	
	public void run() {
		System.out.println("Welcome to the Payroll app");
        System.out.println("Please, enter a command:");
		
		Company company = new Company();
		Scanner sc = new Scanner(System.in);
	    boolean continuation;
	    
	    do {
            continuation = commandExecution(sc.nextLine(), company);
        } while (continuation);
	}
	
	public static void main(String [] args) {
		PayrollProcessing run = new PayrollProcessing();
		run.run();
	}
}
