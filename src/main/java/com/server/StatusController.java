package com.server;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatusController {

	@Value("${DevServer}")
	String value;

	@Autowired
	ModelAndView mv;

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public ModelAndView isUp() throws InterruptedException, FileNotFoundException, IOException {

		System.setProperty("webdriver.chrome.driver", "D:\\JAVA\\Chrome_Jars\\chromedriver1_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

//		System.out.println(value);

		try {
			driver.get(value);
		} catch (Exception e) {

		}

		Thread.sleep(3000);

		String expected = "This site can’t be reached";

		try {
			String actual = driver.findElement(By.xpath("//*[text()='This site can’t be reached']")).getText();
			Thread.sleep(2000);
			System.out.println(actual);

			mv.setViewName("status");

			if (expected.equals(actual))
				mv.addObject("res", "Server status is : DOWN");

		} catch (NoSuchElementException e) {
			mv.addObject("res", "Server status is : UP");
		}
		driver.close();
		return mv;
	}
}
