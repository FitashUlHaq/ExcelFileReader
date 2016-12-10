package dwhAssignment2ETL;

public class SemesterRecord 
{
	private String SID,Degree,Semester,Course,Marks,Discipline;
	private String region;

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString()
	{
		return "'"+getSID()+"'"+",'"+getDegree() +"','"+getSemester()+"','"+getCourse()
		+"','"+getMarks()+"','"+getDiscipline()+"'";
	}
	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}

	public String getDegree() {
		return Degree;
	}

	public void setDegree(String degree) {
		Degree = degree;
	}

	public String getSemester() {
		return Semester;
	}

	public void setSemester(String semester) {
		Semester = semester;
	}

	public String getCourse() {
		return Course;
	}

	public void setCourse(String course) {
		Course = course;
	}

	public String getMarks() {
		return Marks;
	}

	public void setMarks(String marks) {
		Marks = marks;
	}

	public String getDiscipline() {
		return Discipline;
	}

	public void setDiscipline(String discipline)
	{
		Discipline = discipline;
	}
}
