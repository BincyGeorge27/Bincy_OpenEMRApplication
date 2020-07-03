package com.tieto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

	private WebDriver driver;
	private By messageCenterLocator=By.xpath("//span[text()='Message Center']");
	private By patientClientLocator=By.xpath("//div[contains(text(),'Patient/Client')]");
	private By patientsLocator=By.xpath("//div[text()='Patients']");
	
	public DashboardPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void waitForPresencOfMessageCenterText() {
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(messageCenterLocator));
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void mouseHoverOnPatientClient() {	
		JavascriptExecutor script=(JavascriptExecutor) driver;
		script.executeScript("arguments[0].click();", driver.findElement(patientClientLocator));
	}
	
	public void clickOnPatients() {
		JavascriptExecutor script=(JavascriptExecutor) driver;
		script.executeScript("arguments[0].click();", driver.findElement(patientsLocator));
	}

}
