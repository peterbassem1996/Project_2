package Project_2;

/**
 * @author Peter
 *
 */
public class Employee {
	public static final int NO_ERR = -1;
	public static final int NAME_ERR = 0;
	public static final int DEP_ERR = 1;
	public static final int DATE_ERR = 2;
	public static final int ANNUAL_SAL_ERR = 3;
	public static final int MANG_ERR = 4;
	public static final int RATE_ERR = 5;
	public static final int HOURS_ERR_NEG= 6;
	public static final int HOURS_ERR_EXCEED= 7;
	
    private Profile employeeProfile;
    private int errNo;
    private double payment;
    
    /**
     * 
     */
    public Employee(){
    	this.employeeProfile = null;
    	errNo = -1;
    }
    
    /**
     * @param name
     * @param department
     * @param dateHired
     */
    public Employee(String name, String department, Date dateHired) {
    	this.employeeProfile = new Profile(name, department, dateHired);
    	errNo = -1;
    }
    
    /**
	 * @return the employeeProfile
	 */
	public Profile getEmployeeProfile() {
		return employeeProfile;
	}
	
	/**
	 * @return the errNo
	 */
	public int getErrNo() {
		return errNo;
	}
	
	/**
     * @return
     */
    public String getDepartment() {
    	return this.employeeProfile.getDepartment();
    }
    
    /**
     * @return
     */
    public Date getDateHired() {
    	return this.employeeProfile.getDateHired();
    }

	/**
	 * @param errNo the errNo to set
	 */
	public void setErrNo(int errNo) {
		this.errNo = errNo;
	}
	
	

	/**
	 * @return the payment
	 */
	public double getPayment() {
		return payment;
	}

	/**
	 * @param payment the payment to set
	 */
	public void setPayment(double payment) {
		this.payment = payment;
	}

	private boolean ValidateName() {
		return employeeProfile.getName() != null;
	}
	
	private boolean ValidateDep() {
		return (employeeProfile.getDepartment().equals("CS") || employeeProfile.getDepartment().equals("IT") 
				|| employeeProfile.getDepartment().equals("ECE"));
	}
	
	private boolean ValidateDate() {
		return employeeProfile.getDateHired().isValid();
	}
	
	public boolean validate() {
		if (!this.ValidateName()) {
			errNo = NAME_ERR;
			return false;
		}
		
		else if (!this.ValidateDep()) {
			errNo = DEP_ERR;
			return false;
		}
		else if (!this.ValidateDate()) {
			errNo = DATE_ERR;
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * @return
	 */
	public double processPayment() {
		payment = 0;
		return payment;
	};
    
    /**
     *
     */
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Employee){
    		Employee input = (Employee)obj;
            return (this.employeeProfile.equals(input.employeeProfile));
        }
        return false;
    }
    
    /**
     *
     */
    @Override
    public String toString() {
    	return this.employeeProfile.toString();
    }
}
