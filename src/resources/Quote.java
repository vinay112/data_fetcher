package resources;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.FluentWait;

import main.Controller;

public class Quote
{
	public void getQuote()
	{
		 Controller.driver.get("http://www.eduro.com/");
          FluentWait wait2= new FluentWait(Controller.driver);
          wait2.withTimeout(5, TimeUnit.SECONDS);
          String text= Controller.driver.findElements(By.xpath("//h1[text()=\"Today's Quote of the Day\"]/..//p")).get(0).getText();
          System.out.println("Quote of the date"+text);
        //  String value = text.substring(text.indexOf(" "));
          Controller.contentx = Controller.contentx.replace("QOTD",text);
          //End of code to replace QOTD in the NewsLetter
	}

}
