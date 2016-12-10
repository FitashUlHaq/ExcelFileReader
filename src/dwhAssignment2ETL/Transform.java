package dwhAssignment2ETL;

import java.lang.reflect.Field;
import java.util.ArrayList;
public class Transform 
{
	
	public void anomliescorrection(ArrayList listtocorrect , anomaliesO object, int n)
	{
		for(int k = 0 ; k <listtocorrect.size(); k ++ )
		{
			Field[] listFields=listtocorrect.get(0).getClass().getDeclaredFields();
			try{
				Field f = listFields[n];
				f.setAccessible(true);
				String temp=null;
				if(f.get(listtocorrect.get(k))!=null)
				{
					temp = f.get(listtocorrect.get(k)).toString();
				}
				if((f.get(listtocorrect.get(k))==null) || temp.equals("NULL") )
				{
					f.setAccessible(true);
					f.set(listtocorrect.get(k), object.namevariable);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void anomalies(ArrayList list)
	{
		Field[] listFields=list.get(0).getClass().getDeclaredFields();
		for(int i =0 ; i < listFields.length;  i++)
		{
			boolean check1 = false , check2=false;
			ArrayList<anomaliesO> listrun = new ArrayList<>();
			for(int j = 0 ; j < list.size() ; j++)
			{
				try{
					Field f = listFields[i];
					f.setAccessible(true);
					//f.get(list.get(i)).toString();
					anomaliesO object  = new anomaliesO();
					String temp=null;
					if(f.get(list.get(j))!=null)
					{
						temp = f.get(list.get(j)).toString();
					}
					if(f.get(list.get(j))!=null && !(temp.equals("NULL")))
					{
						object.namevariable=f.get(list.get(j)).toString();
						object.count=1;
					}
					else
					{
						object=null;
					}
					if(listrun.size()==0 && object!=null)
					{
						listrun.add(object);
						check2=true;
					}
					
					else
					{
						for(int x = 0 ; x < listrun.size(); x++)
						{
							if(object==null)
							{
								break;
								
							}
							if(listrun.get(x).namevariable.equals(object.namevariable))
							{
								listrun.get(x).count++;
								check1=true;
							}
						}
						if((object!=null) && check1 == false)
						{
							if(check2==true)
							{
								listrun.add(object);
							}
						}
						else
						{
							check1=false;
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

			}
			anomaliesO corrected = new anomaliesO();
			if(listrun.size() > 0 )
			{
				corrected=listrun.get(0);
			}
			else
			{
				corrected=null;
			}
			for(int p = 1 ; p < listrun.size(); p ++)
			{
				if(listrun.get(p).count > corrected.count)
				{
					corrected = listrun.get(p);
				}
			}
			if(corrected !=null)
			{
				anomliescorrection(list,corrected,i);
			}
			//correct anomalies//
		}
	}
	public void ACOrrection(ArrayList<Student> list)
	{
		for(int i = 0 ; i < list.size() ; i++)
		{
			if(list.get(i).getGender().equals("1"))
			{
				list.get(i).setGender("M");
			}
			else if(list.get(i).getGender().equals("0"))
			{
				list.get(i).setGender("F");
			}
			if(list.get(i).getLast_Degree().equals("M.sc"))
			{
				list.get(i).setLast_Degree("MS");
			}
			if(list.get(i).getLast_Degree().equals("F.Sc")||list.get(i).getLast_Degree().equals("F.Sc.")   || list.get(i).getLast_Degree().equals("fSc") || list.get(i).getLast_Degree().equals("Fsc"))
			{
				list.get(i).setLast_Degree("FSC");
			}
			if(list.get(i).getLast_Degree().equals("A level"))
			{
				list.get(i).setLast_Degree("A-Level");
			}
		}
	}
}
