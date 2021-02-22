package Project_2;

import java.text.DecimalFormat;

public class PartTime extends Employee{
	private double payRate;
	private double hours;
	
	public PartTime() {
		super();
		this.hours = 0;
	}
	
	public PartTime(String name, String department, Date dateHired, double payRate) {
		super(name, department, dateHired);
		this.hours = 0;
		this.payRate = payRate;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(double hours) {
		if (hours > 100) {
			this.hours = 100;
		}
		else {
			this.hours = hours;
		}
	}
	
	private boolean ValidateHoursNotNeg() {
		return this.hours >= 0;
	}
	
	private boolean ValidateHoursNotExec() {
		return this.hours >= 0;
	}
	
	private boolean ValidatePayRate() {
		return this.payRate >= 0;
	}
    
    @Override
    public boolean validate() {
    	if(!this.ValidateHoursNotNeg()) {
    		this.setErrNo(Employee.HOURS_ERR_NEG);
    		return false;
    	}
    	
    	else if(!this.ValidateHoursNotExec()) {
    		this.setErrNo(Employee.HOURS_ERR_EXCEED);
    		return false;
    	}
    	
    	else if(!this.ValidatePayRate()) {
    		this.setErrNo(Employee.RATE_ERR);
    		return false;
    	}
    	
    	else {
    		return super.validate();
    	}
    }
	
	@Override
	public double processPayment() {
		//double tempHours = this.hours;
		double returnedVal = 0;
		if (hours > 80) {
			returnedVal = (((hours - 80) * 1.5) + 80) * payRate;
		}
		else {
			returnedVal = hours * payRate;
		}
		this.setPayment(returnedVal);
		return this.getPayment();
	}
	
//	@Override
//    public boolean equals(Object obj) {
//    	if(obj instanceof PartTime){
//    		PartTime input = (PartTime)obj;
//            return super.equals(obj) && input.payRate == this.payRate && input.hours == this.hours;
//        }
//        return false;
//    }
    
	@Override
    public String toString() {
    	DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
    	return super.toString() + "::Payment $" + decimalFormat.format(this.getPayment()) + "::PART TIME::HourlyRate $" 
    			+ decimalFormat.format(this.payRate) + "::Hours worked this period: " + String.format("%.2f", this.hours);
    }

}
