package dd_core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import dd_util.TestConfig;
import dd_util.Xls_Reader;
import dd_util.monitoringMail;

public class TestCore {

	
/*
	Initializing properties file, xls, creating db connection, generating logs, 
	init webdriver
	*/
	
	public static Properties config = new Properties();
	public static Properties object = new Properties();
	public static FileInputStream fis;
	public static Xls_Reader excel = new Xls_Reader(System.getProperty("user.dir")+"\\src\\dd_properties\\testdata.xlsx");
	public static WebDriver driver ;
	
	@BeforeSuite		
	public static void init() throws IOException{
		
		if (driver == null)
		{
			//loading config properties file
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\dd_properties\\config.properties");
			config.load(fis);
			
			//loading object properties file
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\dd_properties\\object.properties");
			object.load(fis);
			

			
			if (config.getProperty("browser").equals("firefox"))
					{
				driver = new FirefoxDriver();
				
					}else if (config.getProperty("browser").equals("ie"))
					{
						System.setProperty("webdriver.ie.driver","IEDriverServer.exe");
						driver = new InternetExplorerDriver();
					}
					else if (config.getProperty("browser").equals("chrome"))
					{
						System.setProperty("webdriver.chrome.driver","chromedriver.exe");
						driver = new ChromeDriver();
					}
			
			
			driver.get(config.getProperty("testsite"));
			driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
		}
	}
	
	@AfterSuite
	public static void quitDriver() throws AddressException, MessagingException
	{
		
		monitoringMail mail = new monitoringMail();
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
		driver.quit();
	}
}