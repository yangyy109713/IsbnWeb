package test.selenium.junit;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import test.selenium.java.page.LoginPage;
import test.selenium.util.Configs;

public class LoginTest {
	private WebDriver driver;
	private Base base = new Base();
	
	@Before
	public void start(){
		driver = base.getDriver("chrome");
		driver.get(Configs.getMapValue("qaurl"));
		base.sleep(1000);
	}
	
	@Test
	public void loginTest(){
		LoginPage lp = new LoginPage();
		driver.findElement(By.id(lp.USERNAME_ID)).sendKeys(lp.USERNAME);
		driver.findElement(By.id(lp.USERPASS_ID)).sendKeys(lp.USERPASS);
		driver.findElement(By.xpath(lp.SUBMIT_XPATH)).click();
		base.sleep(1000);
		Set<Cookie> cookies = driver.manage().getCookies();//get the login cookies
		base.saveCookies("cookies.data", cookies);//save cookies to cookies.data
		base.sleep(30000);
	}
	
	@After
	public void end(){
		base.closeDriver();
	}
}
