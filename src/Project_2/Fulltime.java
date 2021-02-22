package Project_2;

import java.text.DecimalFormat; 

public class Fulltime extends Employee{
	protected static final int NUBMER_OF_PAY_PERIODS = 26;
    private double annualSalary;
    
    public Fulltime() {
    	super();
    	annualSalary = 0;
    }
    
    public Fulltime(String name, String department, Date dateHired, double annualSalary) {
    	super(name, department, dateHired);
    	this.annualSalary = annualSalary;
    }
    
    private boolean ValidateAnnualSalary() {
		return annualSalary >= 0;
	}
    
    @Override
    public boolean validate() {
    	if(!this.ValidateAnnualSalary()) {
    		this.setErrNo(Employee.ANNUAL_SAL_ERR);
    		return false;
    	}
    	else {
    		return super.validate();
    	}
    }
    
    @Override
    public double processPayment() {
    	this.setPayment(this.annualSalary / NUBMER_OF_PAY_PERIODS);
    	return this.getPayment();
    }
    
//    @Override
//    public boolean equals(Object obj){
//    	if(obj instanceof Fulltime) {
//    		Fulltime input = (Fulltime)obj;
//    		return super.equals(obj) && input.annualSalary == this.annualSalary;
//    	}
//    	return false;
//    }
    
    @Override
    public String toString() {
    	DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
    	return super.toString() + "::Payment $" + decimalFormat.format(this.getPayment()) + "::FULL TIME::Annual Salary $" + decimalFormat.format(this.annualSalary);
    }
}
