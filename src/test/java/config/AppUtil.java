package config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.LoginContext;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import commonfunctions.AdminLoginPage;
import commonfunctions.AdminLogoutPage;

public class AppUtil {
	public static WebDriver driver;
	public static Properties pop;
	@BeforeTest	
	public static void setup()throws Throwable {
		pop=new Properties();
		pop.load(new FileInputStream("./PropertyFiles/Enviornment.properties"));
		if(pop.getProperty("Browser").equalsIgnoreCase("Chrome")) {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().deleteAllCookies();
			driver.get(pop.getProperty("Url"));
			AdminLoginPage lpg=PageFactory.initElements(driver,AdminLoginPage.class);
			lpg.adminLogin("admin","master");
		}
		else if(pop.getProperty("Browser").equalsIgnoreCase("firefox")) {
			driver= new FirefoxDriver();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().deleteAllCookies();
			driver.get(pop.getProperty("Url"));
			AdminLoginPage lpg=PageFactory.initElements(driver, AdminLoginPage.class);
			lpg.adminLogin("admin", "master");

		}else {
			Reporter.log("Broowsers are not matching");
		}
	}
	
	@AfterTest
	public static void Teardown() {
		AdminLogoutPage log=PageFactory.initElements(driver,AdminLogoutPage.class);
		log.AdminLogout();
		driver.quit();
	}

}
