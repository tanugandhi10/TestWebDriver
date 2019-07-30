package com.tanugandhi.MavenHybrid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Test;

public class LoginTest {

	
	public static WebDriver driver;
	
	@BeforeSuite
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void doLogin()
	{
		driver.get("http://twitter.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[1]/form/div[1]/input")).sendKeys("gandhitanu");
		driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[1]/form/div[2]/input")).sendKeys("test");
		driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/div[1]/form/input[1]")).click();
	}
	
	@AfterSuite
	public void aftertest()
	{
		driver.quit();
	}
	

}
