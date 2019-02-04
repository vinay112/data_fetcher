package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.collections4.map.HashedMap;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.hdgf.chunks.Chunk;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import resources.BdayandAnniv;
import resources.DateandVersion;
import resources.News;
import resources.Quote;
import resources.Word;

public class Controller
{

	public static Properties prop;
	public static WebDriver driver;
	public static String contentx = "";

	public static void initidriver()
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	public static void getTemplateHTML()
	{
		StringBuilder contentBuilder = new StringBuilder();
		try 
		{
			BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\Test1.html"));
			String str;
			while ((str = in.readLine()) != null) 
			{
				contentBuilder.append(str);
			}
			in.close();
		}
		catch (IOException e) {
		}
		contentx = contentBuilder.toString();
	}

	public static void Configurecopytest() throws Exception 
	{
		FileReader reader = new FileReader(System.getProperty("user.dir") + "\\Config.properties");
		prop = new Properties();
		prop.load(reader);
	}

	public static String getconfigparameters(String toget) throws IOException
	{
		return prop.getProperty(toget);
	}

	public static void sendmailcontent(ArrayList emailList) throws Exception

	{

		// String readLine1 = readLin;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtpout.secureserver.net");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()

		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				String from = "";
				String password = "";
				try
				{
					from = getconfigparameters("Email");
					password = getconfigparameters("pass");

				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // change
				finally 
				{
					return new PasswordAuthentication((from), (password));
				}

			}
		});

		try 
		{

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(getconfigparameters("Email")));
			for (int i = 0; i < emailList.size(); i++)
			{
				message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(emailList.get(i).toString()));
			}

			message.setSubject("Newsletter");

			Multipart multipart = new MimeMultipart();

			// creates body part for the message
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(contentx, "text/html");
			MimeBodyPart attachPart = new MimeBodyPart();
			// code to add attachment...will be revealed later
			attachPart.attachFile(new File(System.getProperty("user.dir") + "\\logo.png"));
			// adds parts to the multipart
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(attachPart);
			// sets the multipart as message's content
			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e)

		{
			throw new RuntimeException(e);
		}

	}

	public static void sendmail() throws Exception
	{
		try 
		{
			BufferedReader CSV = new BufferedReader(
					new FileReader(System.getProperty("user.dir") + "\\Email file.txt"));
			String readLine = "";
			ArrayList<String> emailList = new ArrayList<>();
			while ((readLine = CSV.readLine()) != null) {
				String readLin = readLine.toString();
				System.out.println(readLin);
				// obj.register(readLin);
				if (!(readLin.equals("")))
				{
					emailList.add(readLin);
				}
			}
			sendmailcontent(emailList);
			CSV.close();
		} 
		catch (IOException e) {

			
		}
	}

	
	public static void main(String[] args)
	{

		try 
		{
			
			// initialize WebDriver to automate chrome

			initidriver();
			// Get access to HTML template mail format
			getTemplateHTML();

			Configurecopytest();

			// Get content for mail
			News news = new News();
			news.getNews();
			BdayandAnniv bday = new BdayandAnniv();
			bday.getBDayandAnniv();
			DateandVersion dtversion = new DateandVersion();
			dtversion.changeDateandVersion();
			Quote quote = new Quote();
			quote.getQuote();
			Word word = new Word();
			word.getWord();

			// send mail to recipients

			sendmail();
			driver.close();
		} 
		catch (Exception e) {
			System.out.println("Code didn't work as expected");
		}
	}
}
//
//
