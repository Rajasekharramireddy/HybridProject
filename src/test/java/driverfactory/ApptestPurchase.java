package driverfactory;

import com.mongodb.diagnostics.logging.Loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import Utils.XlfileUtils;
import commonfunctions.Purchase;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;
import config.AppUtil;

public class ApptestPurchase extends AppUtil {
	String inputpath="./FileInput/Customerdata.xlsx";
	String outputpath="./FileOutput/DataDrivenResults.xlsx";
	ExtentReports reports;
	ExtentTest loggers;
	String Testdata="purchase";

	@Test
	public void starttest() throws Throwable {
		reports=new ExtentReports(".target/ExtentReports/purchase.html");
		XlfileUtils xl=new XlfileUtils(inputpath);
		int row=xl.rowcount(Testdata);
		Reporter.log("no of rows are::"+row,true);
		for(int i=1;i<=row;i++) 
		{System.out.println("asfhdf");
		reports.startTest("purchase module");
		reports.assignProject("@SekharReddy");
		String Notes=xl.getcellData(Testdata, i, 0);
		System.out.println(Notes);
		String Tamount=xl.getcellData(Testdata, i, 1);
		String Tpayment=xl.getcellData(Testdata, i, 2);
		Thread.sleep(5000);
		Purchase pa = PageFactory.initElements(driver, Purchase.class);
		boolean res = pa.addpurchase(Notes, Tamount, Tpayment);
		loggers.log(LogStatus.INFO,Notes+" "+Tamount+" "+Tpayment);
		if(res) 
		{
			xl.setCellData(Testdata, i, 3, "pass", outputpath);
			loggers.log(LogStatus.PASS,"purchase number founnd in table");

		}else {
			xl.setCellData(Testdata, i, 3,"Fail",outputpath);
			loggers.log(LogStatus.FAIL,"purchase number not found in table");

		}
		reports.endTest(loggers);
		reports.flush();

		}

	}


}
