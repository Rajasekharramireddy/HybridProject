
package commonfunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage {
@FindBy(id="btnreset")
WebElement objbutton;
@FindBy (name = "username")
WebElement objuser;
@FindBy(xpath = "//input[@id='password']")
WebElement objpassword;
@FindBy(id="btnsubmit")
WebElement objloginbutton;
public void adminLogin(String user,String password) {
	objbutton.click();
	objuser.sendKeys(user);
	objpassword.sendKeys(password);
	objloginbutton.click();
	
}




}
