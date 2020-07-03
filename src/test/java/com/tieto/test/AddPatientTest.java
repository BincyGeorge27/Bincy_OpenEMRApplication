package com.tieto.test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tieto.openemrbase.WebDriverWrapper;
import com.tieto.pages.DashboardPage;
import com.tieto.pages.LoginPage;
import com.tieto.utilities.ExcelUtils;

//Fix this, null pointer exception

public class AddPatientTest extends WebDriverWrapper{
	
	@DataProvider
	public Object[][] createPatientData() throws IOException{
		return ExcelUtils.getSheetIntoObject("testdata/OpenEMRData.xlsx", "createPatientData");
	}
		
	@Test(dataProvider="createPatientData")
	public void createPatientTest(String userName, String password, String language) {
		
		LoginPage login=new LoginPage(driver);
		login.enterUserName(userName);
		login.enterPassword(password);
		login.selectLanguageByValue(language);
		login.clickOnLogin();		
		DashboardPage dashboard=new DashboardPage(driver);
		dashboard.waitForPresencOfMessageCenterText();
		dashboard.mouseHoverOnPatientClient();
		dashboard.clickOnPatients();
	}

}
