
public class Patient {
	
	private String name;
	private String address;
	private int mobile_number;
	private int patient_id;
	
	public Patient(final String name, final String address, final int mobile_number, final int patient_id) {
		this.name = name;
		this.address = address;
		this.mobile_number = mobile_number;
		this.patient_id = patient_id;
	}


    public String getName() {
    	return this.name;
    }


    public void setName(final String name) {
    	this.name = name;
    }


    public String getAddress() {
    	return this.address;
    }
    
    public void setAddress(final String address) {
    	this.address = address;
    }
    
    public int getMobile_number() {
    	return this.mobile_number; 
    }
    
    public void setMobile_number() {
    	this.mobile_number = mobile_number;
    }
    
    @Override
    public String toString() {
    	return String.format("Patient [name=%s, address=%s, mobile_number=%, patient_id=%s", this.name, this.address, this.mobile_number, this.patient_id);
    	
    }
}
    
    
    
    
    
    
  
    
    
    
    