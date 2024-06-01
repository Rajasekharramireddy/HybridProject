package commonfunctions;

import java.awt.RenderingHints.Key;
import java.time.Duration;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class Purchase {
	WebDriver driver;
	public Purchase (WebDriver driver) {
		this.driver=driver;	
	}
	@FindBy(xpath = "(//a[.='Purchases'])[2]")
	WebElement objpurchaselink;
	@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
	WebElement  objclickaddicon;
	@FindBy(xpath = "(//input[@id='x_Purchase_Number'])[1]")
	WebElement objpurchasenumber;
	@FindBy(xpath = "(//input[@id='x_Purchase_Date'])[1]")
	WebElement objpurchasedate;
	@FindBy(xpath = "(//select[@id='x_Supplier_ID'])[1]")
	WebElement objsupplierid;
	@FindBy(xpath = "//input[@id='x_Notes']")
	WebElement objnotes;
	@FindBy(xpath = "//input[@id='x_Total_Amount']")
	WebElement objtotalamount;
	@FindBy(xpath = "(//input[@id='x_Total_Payment'])[1]")
	WebElement objtotalpayment;
	@FindBy(xpath = "//input[@id='x_Total_Balance']")
	WebElement objtotalbalance;
	@FindBy(id = "btnAction")
	WebElement objclickaddbutton;
	@FindBy(xpath = "//button[contains(.,'OK!')]")
	WebElement objclickonconformok;
	@FindBy(xpath = "(//button[@class='ajs-button btn btn-primary'])[1]")
	WebElement objclickalertok;
	@FindBy(xpath = "//button[@class='ajs-button btn btn-primary']")
	WebElement objsearchpannal;
	@FindBy(xpath = "(//button[@class='btn btn-default ewSearchToggle'])[1]")
	WebElement objsearchtext;
	@FindBy(xpath = "(//button[@id='btnsubmit'])[1]")
	WebElement objsearchbutton;
	@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr/td[5]/div/span/span")
	WebElement webtable;

	public boolean addpurchase(String Notes,String Tamount,String Tpayment) throws Throwable{
		Actions act=new Actions(driver);
		act.moveToElement(this.objpurchaselink).click().perform();
		act.moveToElement(this.objclickaddicon).click().perform();
		String ACT_Pnumber=objpurchasenumber.getAttribute("value");
		String ACT_pdate=objpurchasedate.getAttribute("value");
		Select spi=new Select(objsupplierid);
		spi.selectByIndex(4);
		this.objnotes.sendKeys(Notes);
		this.objtotalamount.sendKeys(Tamount);
		this.objtotalpayment.sendKeys(Tpayment);
		this.objclickaddbutton.sendKeys(Keys.ENTER);
		act.pause(2000);
		this.objclickonconformok.click();
		Thread.sleep(2000);
		this.objclickalertok.click();
		act.pause(2000);
		if(!objsearchtext.isDisplayed()) {
			objsearchpannal.click();
			objsearchtext.clear();
			objsearchtext.sendKeys(ACT_Pnumber);
			objsearchbutton.click();
			String EXP_Pnumber=webtable.getText();
			if(ACT_Pnumber.equals(EXP_Pnumber)) {
				Reporter.log(ACT_Pnumber+" "+EXP_Pnumber,true);
				return true;

			}else {
				Reporter.log(ACT_Pnumber+" "+EXP_Pnumber,false);
				return false;
			}

		}
		return false;



	}






}
