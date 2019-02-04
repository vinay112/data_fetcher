package resources;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import main.Controller;

public class News
{
	public void getNews()
	
	{
    	  
		//Code to replace NEWS in the NewsLetter
			      	  
			      	  Controller.driver.get("https://news.google.com/news/headlines/section/topic/TECHNOLOGY.en_in/Technology?ned=in&hl=en-IN&gl=IN");
			   
			          FluentWait wait1= new FluentWait(Controller.driver);
			      	  wait1.withTimeout(5, TimeUnit.SECONDS);
			      
			      	 JavascriptExecutor jse = (JavascriptExecutor) Controller.driver;
			 	jse.executeScript("window.scrollBy(0,500)", "");
			 		
			 		
			 		System.out.println("News from Google");
			           for(int i=1;i<6;i++)
			 		{
			 			
			 			String href= Controller.driver.findElement(By.xpath("//c-wiz[@data-label='Technology']//c-wiz[@data-node-index='0;"+i+"']//a[@aria-level='2']")).getAttribute("href");
//			   
			 		    String text= Controller.driver.findElement(By.xpath("//c-wiz[@data-label='Technology']//c-wiz[@data-node-index='0;"+i+"']//a[@aria-level='2']")).getText();
			 		    System.out.println(text);
				 	//	String value = text.substring(text.indexOf(" "));
				 		//String text= value.trim();
			 	
				        if(text.contains("‘"))
				        {
				        	text.replaceAll("‘", "'");
				        	Controller.contentx = Controller.contentx.replace("NEWS"+i+"HEADLINE",text);
					        Controller.contentx = Controller.contentx.replace("NEWS"+i+"TAG", href);
				        }
				        else
				        {
				        	Controller.contentx = Controller.contentx.replace("NEWS"+i+"HEADLINE",text);
					        Controller.contentx = Controller.contentx.replace("NEWS"+i+"TAG", href);
				        }
				 		
			 		
				     
				
			 		}
			 		
			 		// End of code to replace NEWS in the NewsLetter
			 				       
	 }
}
