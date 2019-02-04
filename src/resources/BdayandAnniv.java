package resources;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import main.Controller;

public class BdayandAnniv
{
	public void getBDayandAnniv()
	{
		//Code to replace the Birthday anniversary from excel   


		{

			{

				Date todaysDate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				//System.out.println(dtf.format(now));

				try {
					String a="";
					XSSFWorkbook wb = new XSSFWorkbook(System.getProperty("user.dir")+"\\bdays and anniversary.xlsx");
					XSSFSheet sheet = wb.getSheetAt(0);
					Date d1 = sdf1.parse((sdf1.format(todaysDate)));
					HashMap<String,String> hm = new HashMap<String,String>();
					ArrayList<String> al1 = new ArrayList<>();
					ArrayList<String> al2 = new ArrayList<>();
					ArrayList<String> finalList = new ArrayList<>();
					String excelMonth="";
					String todayDateMonth="";
					String excelDate="";
					String todayDateDate="";
					XSSFCell cell	;	      
					for (java.util.Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();)
					{
						XSSFRow row = (XSSFRow) iterator.next();
						for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) 
						{
							cell = row.getCell(i);
							if(cell.getCellType()==0)
							{
								a = cell.getDateCellValue().toString();
							}
							else
							{
								a = cell.getStringCellValue(); 
							}
							if(i==0)
							{
								al1.add(a);
							}
							else
							{
								if(!(a.equals("DOB")))
								{
									Date d2 = (Date)sdf.parse(a);
									Calendar cal = Calendar.getInstance();
									cal.setTime(d2);
									String formatedDate1 = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
									//         	  System.out.println(formatedDate1);
									cal.setTime(d1);
									String formatedDate2 = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
									//         	  System.out.println(formatedDate2);
									excelMonth = formatedDate1.substring(formatedDate1.indexOf('/')+1, formatedDate1.lastIndexOf('/'));
									todayDateMonth = formatedDate2.substring(formatedDate2.indexOf('/')+1, formatedDate2.lastIndexOf('/'));
									excelDate = formatedDate1.substring(0, formatedDate1.indexOf('/'));
									todayDateDate = formatedDate2.substring(0, formatedDate2.indexOf('/'));
									al2.add(excelMonth+"/"+excelDate);
								}
								else
								{
									al2.add(a);
								}
							}
						}
						hm.put(al2.toString().substring(al2.toString().indexOf('[')+1,al2.toString().length()-1), al1.toString().substring(al1.toString().indexOf('[')+1,al1.toString().length()-1));

						al1.clear();
						al2.clear();
						if(excelMonth.equals(todayDateMonth)&&!(excelMonth.equals("")))
						{
							if(excelDate.equals(todayDateDate))
							{
								finalList.add(hm.get((excelMonth+"/"+excelDate)));
							}
						}
					}



					// System.out.println( hm.toString());
					// System.out.println(finalList.toString());
					String bday = finalList.toString();
					bday =  bday.replace("[","");
					bday = bday.replace("]", "");
					System.out.println(bday);

					String  noparty = " No Party Today ";
					//       while(finalList.isEmpty())
					//       {
					if (bday.isEmpty())
					{
						System.out.println(noparty);
						Controller.contentx = Controller.contentx.replace("BD",noparty);   
					}
					else
					{System.out.println(bday);
					Controller.contentx = Controller.contentx.replace("BD",bday);
					}
				}







				catch (Exception e)
				{

					System.out.println(e.getMessage());

				}
			}

			//@Test
			//public void Anniversary()
			//{

			Date todaysDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			//System.out.println(dtf.format(now));

			//Date date1= dtf.parse()
			try {
				String a="";
				XSSFWorkbook wb = new XSSFWorkbook(System.getProperty("user.dir")+"\\bdays and anniversary.xlsx");
				XSSFSheet sheet = wb.getSheetAt(1);
				Date d1 = sdf1.parse((sdf1.format(todaysDate)));
				HashMap<String,String> hm = new HashMap<String,String>();
				ArrayList<String> al1 = new ArrayList<>();
				ArrayList<String> al2 = new ArrayList<>();
				ArrayList<String> finalList = new ArrayList<>();
				String excelMonth="";
				String todayDateMonth="";
				String excelDate="";
				String todayDateDate="";
				XSSFCell cell	;	      
				for (java.util.Iterator<Row> iterator = sheet.rowIterator(); iterator.hasNext();)
				{
					XSSFRow row = (XSSFRow) iterator.next();
					for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) 
					{
						cell = row.getCell(i);
						if(cell.getCellType()==0)
						{
							a = cell.getDateCellValue().toString();
						}
						else
						{
							a = cell.getStringCellValue(); 
						}
						if(i==0)
						{
							al1.add(a);
						}
						else
						{
							if(!(a.equals("DOB")))
							{
								Date d2 = (Date)sdf.parse(a);
								Calendar cal = Calendar.getInstance();
								cal.setTime(d2);
								String formatedDate1 = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
								//         	  System.out.println(formatedDate1);
								cal.setTime(d1);
								String formatedDate2 = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
								//         	  System.out.println(formatedDate2);
								excelMonth = formatedDate1.substring(formatedDate1.indexOf('/')+1, formatedDate1.lastIndexOf('/'));
								todayDateMonth = formatedDate2.substring(formatedDate2.indexOf('/')+1, formatedDate2.lastIndexOf('/'));
								excelDate = formatedDate1.substring(0, formatedDate1.indexOf('/'));
								todayDateDate = formatedDate2.substring(0, formatedDate2.indexOf('/'));
								al2.add(excelMonth+"/"+excelDate);
							}
							else
							{
								al2.add(a);
							}
						}
					}
					hm.put(al2.toString().substring(al2.toString().indexOf('[')+1,al2.toString().length()-1), al1.toString().substring(al1.toString().indexOf('[')+1,al1.toString().length()-1));
					al1.clear();
					al2.clear();
					if(excelMonth.equals(todayDateMonth)&&!(excelMonth.equals("")))
					{
						if(excelDate.equals(todayDateDate))
						{
							finalList.add(hm.get((excelMonth+"/"+excelDate)));
						}
					}
				}
				/* System.out.println(hm.toString());
       System.out.println(finalList.toString());*/
				String anniversary = finalList.toString();
				anniversary =   anniversary.replace("[","");
				anniversary =  anniversary.replace("]", "");
				System.out.println(anniversary);
				String  noparty = " No Party Today ";

				if (finalList.isEmpty())
				{System.out.println(" ");
				Controller.contentx= Controller.contentx.replace("ANNY",noparty);   
				}
				else
				{
					Controller.contentx = Controller.contentx.replace("ANNY",anniversary);
				}
			}






			catch (Exception e)
			{

				System.out.println(e.getMessage());

			}
		}

		//End of code to replace the  Birthday anniversary from   excel
	}

}
