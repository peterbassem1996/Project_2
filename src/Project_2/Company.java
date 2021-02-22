package Project_2;

public class Company {
	private static final int NOT_FOUND = -1;
	private Employee[] empList;
	private int numEmployee;
	
	public Company() {
		empList = new Employee[4];
		numEmployee = 0;
	}
	
	
	
	/**
	 * @return the numEmployee
	 */
	public int getNumEmployee() {
		return numEmployee;
	}


	private int find(Employee employee) {
		for (int i = 0; i < numEmployee; i++) {
			if (empList[i].equals(employee)) {
				return i;
			}
		}
		return NOT_FOUND;
	}
	
	private void grow() {
		if (numEmployee == empList.length) {
			Employee[] newlist = new Employee[empList.length * 2];
			for (int i = 0; i < numEmployee; i++) {
				newlist[i] = empList[i];
			}
			empList = newlist;
		}
	}
	
	public boolean add(Employee employee) {
		if (employee.validate()) {
			empList[numEmployee] = employee;
			numEmployee++;
			this.grow();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean remove(Employee employee) {
		if (employee.validate()) {
			int index = find(employee);
			if (index > NOT_FOUND) {
				if (index < numEmployee - 1) {
					for (int i = index; i < numEmployee; i++) {
						empList[i] = empList[i + 1];
					}
				}
				numEmployee--;
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public boolean setHours(Employee employee, double hours) {
		if (employee.validate()) {
			int index = find(employee);
			if (index > NOT_FOUND) {
				if(empList[index] instanceof PartTime) {
					PartTime entry= (PartTime)empList[index];
					entry.setHours(hours);
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public void processPayments() {
		for (int i = 0; i < numEmployee; i++) {
			empList[i].processPayment();
		}
	}
	
	public void print() {
		
		if(this.numEmployee <= 0 ) {
			System.out.println("Employee Database is empty!");
			return;
		}
		
		for (int i = 0; i < numEmployee; i++) {
			System.out.println(empList[i].toString());
		}
	}
	
	public void printByDepartment() {
		
		if(this.numEmployee <= 0 ) {
			System.out.println("Employee Database is empty!");
			return;
		}
		
		for (int i = 0; i < numEmployee; i++) {
			if(empList[i].getDepartment().equals("CS")) {
				System.out.println(empList[i].toString());
			}
		}
		for (int i = 0; i < numEmployee; i++) {
			if(empList[i].getDepartment().equals("ECE")) {
				System.out.println(empList[i].toString());
			}
		}
		for (int i = 0; i < numEmployee; i++) {
			if(empList[i].getDepartment().equals("IT")) {
				System.out.println(empList[i].toString());
			}
		}
	}
	
	
	public void printByDate() {
		
		if(this.numEmployee <= 0 ) {
			System.out.println("Employee Database is empty!");
			return;
		}
		
		Employee[] tempEmpList = new Employee[empList.length];
		for (int i = 0; i < numEmployee; i++) {
			tempEmpList[i] = empList[i];
		}
		
        for (int i = 0; i < numEmployee; i++) {
            for (int j = 0; j < numEmployee-i-1; j++)
                if (tempEmpList[j].getDateHired().compareTo(tempEmpList[j+1].getDateHired()) > 0)
                {
                    // swap temp and arr[i]
                    Employee temp = tempEmpList[j];
                    tempEmpList[j] = tempEmpList[j+1];
                    tempEmpList[j+1] = temp;
                }
		}
        
        for (int i = 0; i < numEmployee; i++) {
        	System.out.println(tempEmpList[i].toString());
		}
	}
	
	public static void main(String[] args) {
		Company ourComp = new Company();
		ourComp.printByDate();
		ourComp.add(new Manager("Dawoud,Peter", "CS", new Date("2/20/2021"), 100000, 3));
		ourComp.add(new Manager("Dawoud,Peter", "IT", new Date("2/19/2021"), 100000, 3));
		ourComp.add(new Manager("Dawoud,Peter", "IT", new Date("2/18/2021"), 100000, 3));
		ourComp.add(new Manager("Dawoud,Peter", "CS", new Date("2/17/2021"), 100000, 3));
		ourComp.printByDate();
		
		System.out.println();
		
		ourComp.remove(new Employee("Dawoud,Peter", "CS", new Date("2/20/2021")));
		ourComp.printByDepartment();
	}

}
