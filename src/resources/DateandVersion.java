package resources;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

import main.Controller;

//import main.Controller;

public class DateandVersion
{
	

	public void changeDateandVersion() throws Exception
	{	 
		//Code to replace date in the NewsLetter


		Date currentDate = new Date();
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyy/MMM/dd");
		LocalDateTime now= LocalDateTime.now();
		Controller.contentx = Controller.contentx.replace("DATE", (dtf.format(now)).toString());

		//End of code to replace date in the NewsLetter


		//Code to replace the version in newsletter          

		OutputStream out = null;
        try 
        {

            Properties props = new Properties();

            File f = new File("config.properties");
            if(f.exists())
            {

                props.load(new FileReader(f));
                //Change your values here
                String Version1 = (props.getProperty("version"));
                int version = Integer.parseInt(Version1.toString())+1;
                String v = Integer.toString(version);
                props.setProperty("version", ""+v+"");
            }
            else
            {

                //Set default values?
                props.setProperty("version", "0");
                props.setProperty("pass", "intel@123");
                props.setProperty("Email", "QA_AutoMobs@intelegencia.com");

                f.createNewFile();
            }



            out = new FileOutputStream( f );
            props.store(out, "This is an optional header comment string");

            String v = ( props.get("version").toString());
            Controller.contentx = Controller.contentx.replace("VERSION",v);
       }
        catch (Exception e ) 
        {
            e.printStackTrace();
        }
        finally
        {

            if(out != null){

                try 
                {

                    out.close();
                } 
                catch (Exception ex)
                {

                    System.out.println("IOException: Could not close myApp.properties output stream; " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
	
		 

		//End of the code to replace the version in newsletter   

	}

}
