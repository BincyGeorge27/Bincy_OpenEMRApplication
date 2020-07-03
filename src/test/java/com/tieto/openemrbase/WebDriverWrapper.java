package com.tieto.openemrbase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.tieto.utilities.PropUtils;

public class WebDriverWrapper {
		
		protected WebDriver driver;
		
		@Parameters({"browser"})
		@BeforeMethod
		public void init(@Optional("ch")String browserName) throws IOException{
			
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
			if(browserName.toLowerCase().equals("ie"))
			{
				driver=new InternetExplorerDriver();
			}
			else if(browserName.toLowerCase().equals("ff"))
			{
				driver=new FirefoxDriver();
			}
			else
			{
				driver=new ChromeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			String baseUrl=PropUtils.getValueFromKey("testdata/data.properties", "url");
			driver.get(baseUrl);
		}
		
		@AfterMethod
		public void end() {
			driver.quit();
		}

	}

