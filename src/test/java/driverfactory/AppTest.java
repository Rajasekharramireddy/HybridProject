package driverfactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.mongodb.diagnostics.logging.Loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utils.XlfileUtils;
import commonfunctions.CustomerPage;
import config.AppUtil;
import freemarker.log.Logger;

public class AppTest extends AppUtil{
String inputpath="./FileInput/Customerdata.xlsx";
String outputpath="./FileOutput/DataDrivenResults.xlsx";
ExtentReports reports;
ExtentTest loggers;
String Testdata="customer";
@Test
public void startTest() throws Throwable {
	reports=new ExtentReports("./target/ExtentReports/Customer.html");
	XlfileUtils xl=new XlfileUtils(inputpath);
	int row=xl.rowcount(Testdata);
	Reporter.log("No of rows are ::"+row,true);
	for(int i=1;i<=row;i++) {
		loggers=reports.startTest("Customer module");
		loggers.assignAuthor("@Sekharreddy");
		String cname=xl.getcellData(Testdata, i, 0);
		String Adress=xl.getcellData(Testdata, i, 1);
		String city=xl.getcellData(Testdata, i, 2);
		String country=xl.getcellData(Testdata, i, 3);
		String cperson=xl.getcellData(Testdata, i, 4);
		String pnumber=xl.getcellData(Testdata, i, 5);
		String Email=xl.getcellData(Testdata, i, 6);
		String Mnumber=xl.getcellData(Testdata, i, 7);
		String notes=xl.getcellData(Testdata, i, 8);
		CustomerPage cop=PageFactory.initElements(driver, CustomerPage.class);	
		boolean res=cop.addcustomer(cname, Adress, city, country, cperson, pnumber, Email, Mnumber, notes);
		loggers.log(LogStatus.INFO,cname+" "+Adress+" "+city+" "+country+" "+cperson+" "+pnumber+" "+Email+" "+Mnumber+" "+notes);
		if(res) {
			xl.setCellData(Testdata, i, 9, "pass",outputpath);
			loggers.log(LogStatus.PASS,"Customer number found in table");
		}else {
			xl.setCellData(Testdata, i, 9, "Fail", outputpath);
			loggers.log(LogStatus.FAIL,"Customer  not number found in table");
			File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen,new File("./target/Screenshot.png"));
		}
		
		reports.endTest(loggers);
		reports.flush();
		
	}
	
}
}
