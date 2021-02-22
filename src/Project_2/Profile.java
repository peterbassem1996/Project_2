package Project_2;

/**
 * @author Peter
 *
 */
public class Profile {
    private String name; //lastName,firstName
    private String department; //CS, ECE, IT
    private Date dateHired;

	/**
	 * 
	 */
	public Profile(){
        this.name = null;
        this.department = null;
        this.dateHired = null;
    }

    /**
     * @param name
     * @param department
     * @param dateHired
     */
    public Profile(String name, String department, Date dateHired){
        this.name = name;
        this.department = department;
        this.dateHired = dateHired;
    }

    /**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the dateHired
	 */
	public Date getDateHired() {
		return dateHired;
	}

	/**
	 *
	 */
	@Override
    public boolean equals(Object object){
        if(object instanceof Profile){
        	Profile input = (Profile)object;
            return (this.name.equals(input.name) && this.department.equals(input.department)
                    && this.dateHired.equals(input.dateHired));
        }
        return false;
    }

    /**
     *
     */
    @Override
    public String toString(){
        return (this.name + "::" + this.department + "::" + this.dateHired.toString());
    }

    public static void main(String[] args){
        Profile peter = new Profile("Dawoud,Peter", "CS", new Date("2/20/2021"));
        //Profile marshy = new Profile("Patel, Marshy", "CS", new Date("2/20/2021"));
        System.out.println(peter.toString());
        System.out.println(peter.equals(peter));
    }
}
