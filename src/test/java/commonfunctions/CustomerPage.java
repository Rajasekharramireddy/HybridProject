		package commonfunctions;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class CustomerPage {
	WebDriver driver;
public CustomerPage(WebDriver driver) {
	this.driver=driver;
}
@FindBy(xpath = "(//a[contains(text(),'Customers')])[2]")
WebElement objcustomerlink;
@FindBy(xpath = "(//span[@data-phrase='AddLink'])[1]")
WebElement objclickicon;
@FindBy(xpath = "//input[@id='x_Customer_Number']")
WebElement objcustomernumber;
@FindBy(xpath = "//input[@id='x_Customer_Name']")
WebElement objcustomername;
@FindBy(xpath = "(//textarea[@id='x_Address'])[1]")
WebElement objadress;
@FindBy(xpath = "(//input[@id='x_City'])[1]")
WebElement objcity;
@FindBy(xpath = "(//input[@id='x_Country'])[1]")
WebElement objcountry;
@FindBy(xpath = "(//input[@id='x_Contact_Person'])[1]")
WebElement objcontactperson;
@FindBy(xpath = "(//input[@id='x_Phone_Number'])[1]")
WebElement objphonenumber;
@FindBy(xpath = "(//input[@id='x__Email'])[1]")
WebElement objEmail;
@FindBy(xpath = "(//input[@id='x_Mobile_Number'])[1]")
WebElement objMnumber;
@FindBy(xpath = "(//input[@id='x_Notes'])[1]")
WebElement objnotes;
@FindBy(id = "btnAction")
WebElement objclickAddbutton;
@FindBy(xpath = "//button[text()='OK!']")
WebElement objclickonconformok;
@FindBy(xpath = "(//button[text()='OK'])[6]")
WebElement objclickonokalert;
@FindBy(xpath = "//span[@data-phrase='SearchBtn']")
WebElement objsearchpanal;
@FindBy(xpath = "//input[@id='psearch']")
WebElement objsearchtextbox;
@FindBy(xpath = "//button[@id='btnsubmit']")
WebElement objsearchbtn;
@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
WebElement webtable;
public boolean addcustomer(String cname,String Adress,String city,String country,String cperson,String pnumber,String Email,String Mnumber,String notes)throws Throwable
{
	Actions act=new Actions(driver);
	act.moveToElement(this.objcustomerlink).click().perform();
	act.pause(4000);
	act.moveToElement(this.objclickicon).click().perform();
	String exp_data = objcustomernumber.getAttribute("value");
	System.out.println(exp_data);
	this.objcustomername.sendKeys(cname);
	this.objadress.sendKeys(Adress);
	this.objcity.sendKeys(city);
	this.objcountry.sendKeys(country);
	this.objcontactperson.sendKeys(cperson); 
	this.objphonenumber.sendKeys(pnumber);
	this.objEmail.sendKeys(Email);
	this.objMnumber.sendKeys(Mnumber);
	this.objnotes.sendKeys(notes);
	this.objclickAddbutton.sendKeys(Keys.ENTER);
	act.pause(3000);
	objclickonconformok.click();
	Thread.sleep(2000);
	objclickonokalert.click();
	if(!objsearchtextbox.isDisplayed())
	objsearchpanal.click();
	objsearchtextbox.clear();
	objsearchtextbox.sendKeys(exp_data);
	objsearchbtn.click();
	String Act_data = webtable.getText();
	if(Act_data.equals(exp_data))
	{
		Reporter.log(exp_data+"      "+Act_data,true);
		return true;
	
	}
	else
	{
		Reporter.log(exp_data+"      "+Act_data,true);
		return false;
	}
}




}



