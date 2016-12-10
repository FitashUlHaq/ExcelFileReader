package dwhAssignment2ETL;
import java.util.ArrayList;

public class Program 
{
	private ArrayList<Student> listStudents;
	private ArrayList<SemesterRecord> listRecords;
	Program()
	{
		listStudents = new ArrayList<>();
		listRecords = new ArrayList<>();
	}
	public static void main(String[] args)
	{
		long startTime = System.currentTimeMillis();
		Program p = new Program();
		Extract extract = new Extract();
		Transform transform = new Transform();
		Load load = new Load();
		extract.extractData(p.listStudents , p.listRecords);
		transform.anomalies(p.listStudents);
		transform.anomalies(p.listRecords);
		transform.ACOrrection(p.listStudents);
		System.out.println("");
		System.out.println("All Anomalies Solved");
		load.dbinsert(p.listStudents, p.listRecords);
		long endTime = System.currentTimeMillis();
		System.out.println("Time Taken: "+(endTime-startTime) + "MS");
	}
}
