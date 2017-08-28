package com.automation.framework;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Demo {
	WebDriver driver;
	public static String GECO_Driver = ".//libs//geckodriver.exe";
	
	@BeforeTest
	public void openBrowser(){
		System.setProperty("webdriver.gecko.driver", GECO_Driver);
		driver = new FirefoxDriver();
		driver.get("http://opensource.demo.orangehrmlive.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	public void loginToOrangeHRM(){
		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin");
		driver.findElement(By.id("btnLogin")).click();
	}
	
	@Test(priority = 1)
	public void verifyPageTitle(){
		String actualTitle = "OrangeHRM";
		String expectedTitle = driver.getTitle(); 
		assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(priority = 2)
	public void verifySuccessfulLoginToOrangeHRM(){
		loginToOrangeHRM();
		
		String actualText = "Dashboard";
		String expectedText = driver.findElement(By.xpath(".//div[@id='content']/div/div[1]/h1")).getText();
		
		assertEquals(actualText, expectedText);
	}
}
