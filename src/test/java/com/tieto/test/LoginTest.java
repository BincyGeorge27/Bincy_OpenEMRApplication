package com.tieto.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tieto.openemrbase.WebDriverWrapper;
import com.tieto.pages.DashboardPage;
import com.tieto.pages.LoginPage;
import com.tieto.utilities.ExcelUtils;

public class LoginTest extends WebDriverWrapper{
	
	@DataProvider
	public Object[][] validCredentialData() throws IOException{
		return ExcelUtils.getSheetIntoObject("testdata/OpenEMRData.xlsx", "validCredentialData");
	}

	@Test(priority=1,dataProvider="validCredentialData")
	public void validCredentialTest(String userName, String password, String language, String expectedTitle){
		
		LoginPage login=new LoginPage(driver);
		login.enterUserName(userName);
		login.enterPassword(password);
		login.selectLanguageByValue(language);
		login.clickOnLogin();		
		DashboardPage dashboard=new DashboardPage(driver);
		dashboard.waitForPresencOfMessageCenterText();
		Assert.assertEquals(dashboard.getPageTitle(), expectedTitle);
	}
	
	@DataProvider
	public Object[][] invalidCredentialData(){
		
		Object[][] data=new Object[2][4];
		
		data[0][0]="John";
		data[0][1]="John123";
		data[0][2]="8";
		data[0][3]="Invalid username or password";
		
		data[1][0]="Peter";
		data[1][1]="Peter123";
		data[1][2]="14";
		data[1][3]="Invalid username or password";
		
		return data;
	}
	
	@Test(priority=2,dataProvider = "invalidCredentialData")
	public void invalidCredentialTest(String userName, String password, String language, String expectedError){
		
		LoginPage login=new LoginPage(driver);
		login.enterUserName(userName);
		login.enterPassword(password);
		login.selectLanguageByValue(language);
		login.clickOnLogin();	
		Assert.assertEquals(login.getInvalidLoginErrorMessage(),expectedError);
	}
}
