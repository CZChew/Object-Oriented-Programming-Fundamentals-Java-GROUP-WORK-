import java.util.date

public class Appointment {
	
	private String doctor_name;
	private int patient_id;
	private boolean appointment_status;
	private double appointment_cost;
	private date date;
	
	public Appointment(final String doctor_name, final int patient_id, final boolean appointment_status, final double appointment_cost, final date date); {
		this.doctor_name = doctor_name;
        this.patient_id = patient_id;
        this.appointment_status = appointment_cost;
        this.appointment_cost = appointment_cost;
        this.date = date;
    
	}
	
	public String getDoctor_name() {
		return this.doctor_name;
	}
	
	public void setDoctor_name(final String doctor_name) {
		this.doctor_name = doctor_name;
	}
	
	public int getPatient_id() {
		return this.patient_id;
	}
	
	public void setPatient_id() {
		this.patient_id = patient_id;
	}
	
	public boolean getAppointment_status() {
		return this.appointment_status;
	}
	
	public void setAppointment_status(final boolean appointment_status) {
		this.appointment_status = appointment_status;
	}
	
	public double getAppointment_cost() {
		return this.appointment_cost; 
	}
	
	public void setAppointment_cost(final double appointment_cost) {
		this.appointment_cost = appointment_cost;
	}

	@Override
	public String toString() {
		return String.format("Appointment [doctor_name=%s, patient_id=%s, appointment_status=%s, appointment_cost=%s]",
				doctor_name, patient_id, appointment_status, appointment_cost);
	}
	
	
}
      
    
    

