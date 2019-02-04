package resources;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;

import main.Controller;

public class Word 
{
	public void getWord() throws InterruptedException
	{
	     //Code to replace WOTD in the NewsLetter
          Thread.sleep(5000);
          Controller.driver.get("http://www.wordthink.com/");
          FluentWait wait21= new FluentWait(Controller.driver);
          wait21.withTimeout(5, TimeUnit.SECONDS);
       
          String word= Controller.driver.findElement(By.xpath("//div[h1[text( )=\"Today's Word of the Day\"]]//h2[@class='title']/..//p//b")).getText();
          Controller.contentx = Controller.contentx.replace("MEANING",word);
          
          String wordMeaning= Controller.driver.findElement(By.xpath("//div[h1[text( )=\"Today's Word of the Day\"]]//h2[@class='title']/..//p")).getAttribute("innerText");
       //wordMeaning = wordMeaning.substring(wordMeaning.indexOf("("));
        // wordMeaning=wordMeaning.replace("?"", "");
        if(wordMeaning.contains("“"))
        {
        	wordMeaning = wordMeaning.replaceAll("“", "\"");
        }
        if(wordMeaning.contains("”"))
        {
        	wordMeaning = wordMeaning.replaceAll("”", "\"");
        }
    if(wordMeaning.contains("1."))
         {
    		wordMeaning=wordMeaning.substring(0, wordMeaning.indexOf("2"));
    		wordMeaning = wordMeaning.replace("1.", "");
      	   System.out.println(wordMeaning);
      	   Controller.contentx = Controller.contentx.replace("WOTD",wordMeaning);
         }
    else
         {
        	Controller.contentx = Controller.contentx.replace("WOTD",wordMeaning);
         }
    System.out.println("Word:" +word+"\t WordMeaning"+wordMeaning);
           //End of code to replace WOTD in the NewsLetter
	}

}
