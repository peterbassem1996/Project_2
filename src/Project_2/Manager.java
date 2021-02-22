package Project_2;

import java.text.DecimalFormat;

public class Manager extends Fulltime{
	private static final int MANAGER = 1;
	private static final int DEP_HEAD = 2;
	private static final int DIR = 3;
	private static final double MANAGER_COMP = 5000;
	private static final double DEP_HEAD_COMP = 9500;
	private static final double DIR_COMP = 12000;
	private int managerLevel;
	
	public Manager() {
		super();
		this.managerLevel = 1;
	}
	
	public Manager(String name, String department, Date dateHired, double annualSalary, int managerLevel) {
		super(name, department, dateHired, annualSalary);
		this.managerLevel = managerLevel;
	}
	
	private boolean ValidateManagerLevel() {
		return this.managerLevel == MANAGER || this.managerLevel == DEP_HEAD || this.managerLevel == DIR;
	}
    
    @Override
    public boolean validate() {
    	if(!this.ValidateManagerLevel()) {
    		this.setErrNo(Employee.MANG_ERR);
    		return false;
    	}
    	else {
    		return super.validate();
    	}
    }
	
	@Override
    public double processPayment() {
		double additionalComp = 0;
		if(this.managerLevel == MANAGER) {
			additionalComp = MANAGER_COMP / Fulltime.NUBMER_OF_PAY_PERIODS;
		}
		
		else if(this.managerLevel == DEP_HEAD) {
			additionalComp = DEP_HEAD_COMP / Fulltime.NUBMER_OF_PAY_PERIODS;
		}
		
		else if(this.managerLevel == DIR) {
			additionalComp = DIR_COMP / Fulltime.NUBMER_OF_PAY_PERIODS;
		}
    	this.setPayment(super.processPayment() + additionalComp);
    	return this.getPayment();
    }
    
//    @Override
//    public boolean equals(Object obj){
//    	if(obj instanceof Manager) {
//    		Manager input = (Manager)obj;
//    		return super.equals(obj) && input.managerLevel == this.managerLevel;
//    	}
//    	return false;
//    }
    
    @Override
    public String toString() {
    	DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
    	String additionalStr = "";
		if(this.managerLevel == MANAGER) {
			additionalStr = "::Manager Compensation$" + decimalFormat.format(MANAGER_COMP / Fulltime.NUBMER_OF_PAY_PERIODS);
		}
		
		else if(this.managerLevel == DEP_HEAD) {
			additionalStr = "::Department Head Compensation$" + decimalFormat.format(DEP_HEAD_COMP / Fulltime.NUBMER_OF_PAY_PERIODS);
		}
		
		else if(this.managerLevel == DIR) {
			additionalStr = "::Director Compensation$" + decimalFormat.format(DIR_COMP / Fulltime.NUBMER_OF_PAY_PERIODS);
		}
    	return super.toString() + additionalStr;
    }
    
    public static void main(String[] args){
        Employee peter = new Manager("Dawoud,Peter", "CS", new Date("2/20/2021"), 100000, 3);
        Employee dav = new Manager("Dawoud,Peter", "CS", new Date("2/20/2021"), 100000, 2);
        System.out.println(peter.toString());
        System.out.println(peter.equals(dav));
        System.out.println(peter.validate());
    }
}
