package resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.Properties;

public class Testconfig {

	public static void main(String[] args) throws Exception {
		OutputStream out = null;
        try {

            Properties props = new Properties();

            File f = new File("config.properties");
            if(f.exists()){

                props.load(new FileReader(f));
                //Change your values here
                String Version1 = (props.getProperty("version"));
                int version = Integer.parseInt(Version1.toString())+1;
                String v = Integer.toString(version);
                props.setProperty("version", ""+v+"");
            }
            else{

                //Set default values?
                props.setProperty("version", "0");
                props.setProperty("pass", "@Infinity1");
                props.setProperty("Email", "Mayur.choudhary@intelegencia.com");

                f.createNewFile();
            }



            out = new FileOutputStream( f );
            props.store(out, "This is an optional header comment string");
            String v = ( props.get("version").toString());
            System.out.println("***"+v);

       }
        catch (Exception e ) {
            e.printStackTrace();
        }
        finally{

            if(out != null){

                try {

                    out.close();
                } 
                catch (Exception ex) {

                    System.out.println("IOException: Could not close myApp.properties output stream; " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
	}

}
