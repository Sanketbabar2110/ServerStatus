package com.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Controller;


public class TestStatusController {

	WebDriver driver = null;

	@Test
	public void isUp() throws InterruptedException, FileNotFoundException, IOException {

		System.setProperty("webdriver.chrome.driver", "D:\\\\JAVA\\\\Chrome_Jars\\\\chromedriver_win32\\\\chromedriver.exe");
		driver = new ChromeDriver();

		Properties props = new Properties();
		props.load(new FileInputStream("src\\\\main\\\\resources\\\\server.properties"));

		try {
			driver.get(props.getProperty("DevServer"));
		} catch (Exception e) {
			
		}
		
		Thread.sleep(3000);
		
		String expected = "This site can’t be reached";
		String actual = driver.findElement(By.xpath("//*[text()='This site can’t be reached']")).getText();
			Thread.sleep(2000);
			System.out.println(actual);
			Assert.assertEquals(expected, actual);
			
//		String expected = "http://localhost:8080/hello";
//		String actual = driver.getCurrentUrl();
//		
//		if (expected.equals(actual)) {
//			System.out.println("Server is UP !!!!!!");
//		}else {
//			System.out.println("Server is DOWN !!!!!!");
//		}

//		try {
//			
//			Assert.assertEquals(expected, actual);
//			System.out.println("Server is UP !!!!!!");
//			
//		} catch (Exception e) {
//			
//			System.out.println("Server is DOWN !!!!!!");
//			
//		}finally {
//			driver.close();
//		}
	}

}
