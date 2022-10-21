
public class Doctor extends Staff {
	
	private String qualification;
	private int GDA_reg_number;
	
	public Doctor(String doctname, String doctmobile, String doctID, String doctnextofkin, char doctgender, String qualification, int GDA_reg_number) {
		super(doctname, doctmobile, doctID, doctnextofkin, doctgender);
		this.qualification = qualification;
		this.GDA_reg_number = GDA_reg_number;
	}
	
	public String getQualification() {
		return this.qualification;
	}
	
	public void setQualification( final String qualification) {
		this.qualification = qualification;
	}
	
	public int getGDA_reg_number() {
		return GDA_reg_number;
	}
	
	public voidsettGDA_reg_number(int GDA_reg_number) {
		this.GDA_reg_number = GDA_reg_number;
	}
}
