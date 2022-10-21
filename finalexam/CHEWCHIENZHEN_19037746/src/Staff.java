
public class Staff {
	
	private String name;
	private String gender;
	private String next_of_kin;
	private int employee_number;
	private int contact_number;
	
	public Staff(final String name, final String gender, final String next_of_kin, final int employee_number, final int contact_number) {
		this.name = name;
		this.gender = gender;
		this.next_of_kin = next_of_kin;
		this.employee_number = employee_number;
		this.contact_number = contact_number;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public void setGender(final String gender) {
		this.gender = gender;
	}
	
	public String getNext_of_kin() {
		return this.next_of_kin;
	}
	
	public void setNext_of_kin(final String next_of_kin) {
		this.next_of_kin = next_of_kin;
	}
	
	public int getEmployee_number() {
		return this.employee_number;
	}
	
	public void setEmployee_number(final int employee_number) {
		this.employee_number = employee_number;
	}
	
	public int getContact_number() {
		return this.contact_number;
	}
	
	public void setContact_number() {
		this.contact_number = contact_number;
	}
	
	@Override
	public String toString() {
		return String.format("Staff [name=%s, gender=%s, next_of_kin=%s, employee_number=%s, contact_number=%s]", name, gender, next_of_kin, employee_number, contact_number);
	}
}

