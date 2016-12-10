package dwhAssignment2ETL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
public class Extract 
{

	public void extractData(ArrayList<Student> listStudents, ArrayList<SemesterRecord> listRecords) 
	{
		String files[]={"Lhr_Student_MS_101.txt","Lhr_Student_MS_102.txt","Lhr_Student_MS_103.txt","Lhr_Student_MS_104.txt"};
		for (String string : files) {

			try{
				int i =0;
				for (String line : Files.readAllLines(Paths.get("/Users/Fitash/Documents/workspace/dwhAssignment2ETL/DataSet/Lahore/"+string))) 
				{
					if(i!=0)
					{
						String[] array= line.split(",");
						Student stu = new Student();
						stu.setSID(array[0]);
						stu.setSt_Name(array[1]);
						stu.setFather_Name(array[2]);
						stu.setGender(array[3]);
						stu.setAddress(array[4]);
						stu.setDate_of_Birth(array[5]);
						stu.setReg_Date(array[6]);
						stu.setReg_Status(array[7]);
						stu.setDegree_Status(array[8]);
						stu.setLast_Degree(array[9]);
						listStudents.add(stu);
					}
					i++;
				}	
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			//////////////////////////////////////////////////////////////////////////////////
			String files2[]={"MS_P_101_Student.txt","MS_P_102_Student.txt","MS_P_103_Student.txt","MS_P_104_Student.txt"};
			for (String string2 : files2) 
			{
				try{
					int i =0;
					for (String line : Files.readAllLines(Paths.get("/Users/Fitash/Documents/workspace/dwhAssignment2ETL/DataSet/PSH/"+string2))) 
					{
						if(i!=0)
						{
							String[] array= line.split(",");
							Student stu = new Student();
							stu.setSID(array[0]);
							stu.setSt_Name(array[1]);
							stu.setFather_Name(array[2]);
							stu.setGender(null);
							stu.setAddress(array[3]);
							stu.setDate_of_Birth(array[4]);
							stu.setReg_Date(array[5]);
							stu.setReg_Status(array[6]);
							stu.setDegree_Status(array[7]);
							stu.setLast_Degree(array[8]);
							listStudents.add(stu);
						}
						i++;
					}	
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		//////////////////////////////////////////////////////////////////////////////////////
		String files3[]={"MS_P_101_Reg.txt","MS_P_102_Reg.txt","MS_P_103_Reg.txt","MS_P_104_Reg.txt"};
		for (String string3 : files3) 
		{
			try{
				int i =0;
				for (String line : Files.readAllLines(Paths.get("/Users/Fitash/Documents/workspace/dwhAssignment2ETL/DataSet/PSH/"+string3))) 
				{
					if(i!=0)
					{
						String[] array= line.split(",");

						SemesterRecord stu =new SemesterRecord();
						stu.setSID(array[0]);
						stu.setCourse(array[1]);
						stu.setMarks(array[2]);
						stu.setDiscipline(array[3]);
						stu.setSemester(array[4]+"-"+array[5]);
						String[] temp = array[3].split("-");
						stu.setDegree(temp[0]);
						stu.setRegion("PSH");
						listRecords.add(stu);
					}
					i++;
				}	
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		String files4[]={"Lhr_Detail_MS_101.txt","Lhr_Detail_MS_102.txt","Lhr_Detail_MS_103.txt","Lhr_Detail_MS_104.txt"};
		for (String string3 : files4) 
		{
			try{
				int i =0;
				for (String line : Files.readAllLines(Paths.get("/Users/Fitash/Documents/workspace/dwhAssignment2ETL/DataSet/Lahore/"+string3))) 
				{
					if(i!=0)
					{
						String[] array= line.split(",");

						SemesterRecord stu =new SemesterRecord();
						stu.setSID(array[0]);
						stu.setDegree(array[1]);
						stu.setSemester(array[2]);
						stu.setCourse(array[3]);
						stu.setMarks(array[4]);
						stu.setDiscipline(array[5]);
						stu.setRegion("LHR");
						listRecords.add(stu);
					}
					i++;
				}	
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}



		//	System.out.println(listStudents.size());
		//	System.out.println(listRecords.size());
		excelRead(listStudents, listRecords);
		acessDBread(listStudents, listRecords);
	}
	public void excelRead(ArrayList<Student> liststudents, ArrayList<SemesterRecord> listrecords)
	{
		HSSFWorkbook workbook = null;
		FileInputStream file;
		try 
		{
			file = new FileInputStream(new File("/Users/Fitash/Documents/workspace/dwhAssignment2ETL/DataSet/Karachi/Student.xls"));
			//Get the workbook instance for XLS file 
			//Get the workbook instance for XLS file 
			workbook = new HSSFWorkbook(file);

			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			int i =0;
			while(rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();

				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				String record ="";
				while(cellIterator.hasNext()) 
				{
					Cell cell = (Cell) cellIterator.next();

					switch(cell.getCellType()) 
					{
					case Cell.CELL_TYPE_BOOLEAN:
						//	System.out.print(cell.getBooleanCellValue() + "\t\t");
						record=record+cell.getBooleanCellValue()+",";
						break;
					case Cell.CELL_TYPE_NUMERIC:
						//	System.out.print(cell.getNumericCellValue() + "\t\t");
						record=record+cell.getNumericCellValue()+",";
						break;
					case Cell.CELL_TYPE_STRING:
						//	System.out.print(cell.getStringCellValue() + "\t\t");
						record=record+cell.getStringCellValue()+",";
						break;
					}
				}
				//		System.out.println(record);

				///////////////////////////////////////
				if(i > 0 & record!="")
				{	
					String[] array  = record.split(",");
					Student st = new Student();
					st.setSID(array[0]);
					st.setSt_Name(array[1]);
					st.setFather_Name(array[2]);
					st.setDate_of_Birth(array[3]);
					st.setGender(array[4]);
					st.setReg_Date(array[5]);
					st.setReg_Status(array[6]);
					st.setDegree_Status(array[7]);
					st.setAddress(array[8]);
					st.setLast_Degree(array[9]);
					
					liststudents.add(st);
				}
				i++;

			}
			//////////////////////////////////////	
			sheet = workbook.getSheetAt(1);

			//Iterate through each rows from first sheet
			rowIterator = sheet.iterator();
			i =0;
			while(rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();

				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				String record ="";
				while(cellIterator.hasNext()) 
				{
					Cell cell = (Cell) cellIterator.next();

					switch(cell.getCellType()) 
					{
					case Cell.CELL_TYPE_BOOLEAN:
						//	System.out.print(cell.getBooleanCellValue() + "\t\t");
						record=record+cell.getBooleanCellValue()+",";
						break;
					case Cell.CELL_TYPE_NUMERIC:
						//	System.out.print(cell.getNumericCellValue() + "\t\t");
						record=record+cell.getNumericCellValue()+",";
						break;
					case Cell.CELL_TYPE_STRING:
						//	System.out.print(cell.getStringCellValue() + "\t\t");
						record=record+cell.getStringCellValue()+",";
						break;
					}
				}
				//				System.out.println(record);

				///////////////////////////////////////
				if(i > 0 & record!="")
				{	
					String[] array  = record.split(",");
					Student st = new Student();
					st.setSID(array[0]);
					st.setSt_Name(array[1]);
					st.setFather_Name(array[2]);
					st.setDate_of_Birth(array[3]);
					st.setGender(array[4]);
					st.setReg_Date(array[5]);
					st.setReg_Status(array[6]);
					st.setDegree_Status(array[7]);
					st.setAddress(array[8]);
					st.setLast_Degree(array[9]);
					liststudents.add(st);
				}
				i++;
			}

			file.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//////////////////////////////////////////////////////////




		try
		{
			workbook = null;
			file = new FileInputStream(new File("/Users/Fitash/Documents/workspace/dwhAssignment2ETL/DataSet/Karachi/Reg_MS_KHR.xls"));
			//Get the workbook instance for XLS file 
			//Get the workbook instance for XLS file 
			workbook = new HSSFWorkbook(file);

			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			int i =0;
			while(rowIterator.hasNext()) {
				Row row = (Row) rowIterator.next();

				//For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();
				String record ="";
				while(cellIterator.hasNext()) 
				{
					Cell cell = (Cell) cellIterator.next();

					switch(cell.getCellType()) 
					{
					case Cell.CELL_TYPE_BOOLEAN:
						//	System.out.print(cell.getBooleanCellValue() + "\t\t");
						record=record+cell.getBooleanCellValue()+",";
						break;
					case Cell.CELL_TYPE_NUMERIC:
						//	System.out.print(cell.getNumericCellValue() + "\t\t");
						record=record+cell.getNumericCellValue()+",";
						break;
					case Cell.CELL_TYPE_STRING:
						//	System.out.print(cell.getStringCellValue() + "\t\t");
						record=record+cell.getStringCellValue()+",";
						break;
					}
				}
	//					System.out.println(record);

				///////////////////////////////////////
				if(i > 0 & record!="")
				{	
					String[] array  = record.split(",");
					SemesterRecord st =new SemesterRecord();
					st.setSID(array[0]);
					st.setCourse(array[1]);
					st.setMarks(array[2]);
					st.setSemester(array[3]);
					st.setDiscipline(array[4]);
					String[] temp = array[4].split("-");
					st.setDegree(temp[0]);
					st.setRegion("KAR");
					listrecords.add(st);
				}
				i++;

			}
			file.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}


	public void acessDBread(ArrayList<Student> studentlist , ArrayList<SemesterRecord> semesterRecords)
	{
		String conURL = "jdbc:ucanaccess:///Users/Fitash/Documents/workspace/dwhAssignment2ETL/DataSet/ISB.accdb";
		Connection conn = null;
		Statement st = null;
		try {
			conn = DriverManager.getConnection(conURL);
			st = conn.createStatement();
			String sql = "";
			sql = "SELECT * FROM MS_Students";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				Student stud = new Student();
				stud.setSID(rs.getString(1));
				stud.setSt_Name(rs.getString(2));
				stud.setFather_Name(rs.getString(3));
				stud.setReg_Date(rs.getString(4));
				stud.setReg_Status(rs.getString(5));
				stud.setDegree_Status(rs.getString(6));
				stud.setDate_of_Birth(rs.getString(7));
				stud.setLast_Degree(rs.getString(8));
				stud.setGender(rs.getString(9));
				stud.setAddress(rs.getString(10));
				studentlist.add(stud);
			}
			
			sql = "SELECT * FROM Registration";
			ResultSet res = st.executeQuery(sql);
			while(res.next())
			{
				SemesterRecord stud =new SemesterRecord();
				stud.setSID(res.getString(1));
				stud.setCourse(res.getString(2));
				stud.setMarks(res.getString(3));
				stud.setDiscipline(res.getString(4));
				stud.setSemester(res.getString(5));
				stud.setRegion("ISL");
				try
				{
					String [] temp = res.getString(4).split("-");
					stud.setDegree(temp[0]);
				}
				catch(Exception e)
				{
					stud.setDegree("BS");
				}
				
				semesterRecords.add(stud);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.print("Number of Records : "+studentlist.size()+":"+semesterRecords.size());
	}
}
