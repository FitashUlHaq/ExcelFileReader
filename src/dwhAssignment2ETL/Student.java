package dwhAssignment2ETL;

public class Student
{
	private String SID,St_Name,Father_Name,Gender,Address,Date_of_Birth,Reg_Date,Reg_Status,Degree_Status,Last_Degree;

	
	@Override
	public String toString()
	{
		return "'"+getSID()+"'"+",'"+getSt_Name() +"','"+getFather_Name()+"','"+getGender()
		+"','"+getAddress()+"','"+getDate_of_Birth()+"','"+getReg_Date()+"','"+getReg_Status()+"','"+getDegree_Status()+"','"+getLast_Degree()+"'";
	}
	
	
	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}

	public String getSt_Name() {
		return St_Name;
	}

	public void setSt_Name(String st_Name) {
		St_Name = st_Name;
	}

	public String getFather_Name() {
		return Father_Name;
	}

	public void setFather_Name(String father_Name) {
		Father_Name = father_Name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getDate_of_Birth() {
		return Date_of_Birth;
	}

	public void setDate_of_Birth(String date_of_Birth) {
		Date_of_Birth = date_of_Birth;
	}

	public String getReg_Date() {
		return Reg_Date;
	}

	public void setReg_Date(String reg_Date) {
		Reg_Date = reg_Date;
	}

	public String getReg_Status() {
		return Reg_Status;
	}

	public void setReg_Status(String reg_Status) {
		Reg_Status = reg_Status;
	}

	public String getDegree_Status() {
		return Degree_Status;
	}

	public void setDegree_Status(String degree_Status) {
		Degree_Status = degree_Status;
	}

	public String getLast_Degree() {
		return Last_Degree;
	}

	public void setLast_Degree(String last_Degree) {
		Last_Degree = last_Degree;
	}
	
}
