package test.selenium.junit;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import test.selenium.java.page.LoginPage;
import test.selenium.util.*;

public class LoginTest {
	private WebDriver driver;
	private Base base = new Base();
	private Log log = new Log(this.getClass());

	@Before
	public void start(){
		driver = base.getDriver("chrome");
		driver.get(Configs.getMapValue("produrl"));
		base.sleep(100);
	}
	
	@Test
	public void test() throws Exception{
		//login by compiling excel , get username and password
		base.type(driver , new Locator("username"));
		base.sleep(1000);
		base.type(driver , new Locator("userpassword"));
		base.sleep(1000);
		base.click(driver , new Locator("submitbotton"));
		base.sleep(1000);
		Set<Cookie> cookies = driver.manage().getCookies();//get the login cookies
		base.saveCookies("cookies.data", cookies);//save cookies to cookies.data
		log.info(" save cookie success!");
		base.sleep(1000);
	}

	public void loginTest(){
		LoginPage lp = new LoginPage();
		driver.findElement(By.id(lp.USERNAME_ID)).sendKeys(lp.USERNAME);
		log.info(" find and input username success!");
		driver.findElement(By.id(lp.USERPASS_ID)).sendKeys(lp.USERPASS);
		log.info(" find and input password success!");
		driver.findElement(By.xpath(lp.SUBMIT_XPATH)).click();
		log.info(" find and submit login success!");
		base.sleep(1000);
		Set<Cookie> cookies = driver.manage().getCookies();//get the login cookies
		base.saveCookies("cookies.data", cookies);//save cookies to cookies.data
		log.info(" save cookie success!");
		base.sleep(30000);
	}
	
	@After
	public void end(){
		base.closeDriver();
	}
}
