package com.tieto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {

	private By userLocator = By.id("authUser");
	private By passLocator = By.id("clearPass");
	private By loginLocator=By.cssSelector("button.btn.btn-default.btn-lg");
	private By languageLocator=By.name("languageChoice");
	private By alertMessageLocator=By.xpath("//div[@class='alert alert-danger login-failure m-1']");
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}

	public void enterUserName(String userName) {
		driver.findElement(userLocator).sendKeys(userName);
	}

	public void enterPassword(String password) {
		driver.findElement(passLocator).sendKeys(password);
	}
	
	public void selectLanguageByValue(String lang) {
		Select language=new Select(driver.findElement(languageLocator));
		language.selectByValue(lang);
	}
	
	public void clickOnLogin() {
		driver.findElement(loginLocator).click();
	}
	
	public String getInvalidLoginErrorMessage() {
		return driver.findElement(alertMessageLocator).getText().trim();
	}
		
}
