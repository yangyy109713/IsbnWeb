package test.selenium.junit;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.assertEquals;

import test.selenium.java.page.ManagerIsbnPage;
import test.selenium.util.Configs;
import test.selenium.util.Base;
import test.selenium.util.Utils;
import test.selenium.util.Log;

public class AddIsbnTest {
	private WebDriver driver;
	private Base base = new Base();
	private ManagerIsbnPage mp = new ManagerIsbnPage();
	private Log log = new Log(AddIsbnTest.class);
	@Before
	public void start(){
		driver = base.getDriver("chrome");
		//driver.get(Configs.getMapValue("produrl"));
		driver.get(Configs.getMapValue("qaurl"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		base.getCookies(driver, "cookies.data");
		//driver.get(Configs.getMapValue("produrl"));
		driver.get(Configs.getMapValue("qaurl1"));
		base.sleep(1000);
	}
	
	@Test
	public void addIsbn(){
		log.info("add isbn barcode beginning...");
		driver.findElement(By.linkText(mp.ISBN_MANAGE)).click();
		base.sleep(1000);
		driver.findElement(By.xpath(mp.ISBN_MANAGER_XPATH)).click();
		base.sleep(3000);
		driver.findElement(By.id(mp.ADD_ISBN)).click();
		base.sleep(500);
		driver.findElement(By.xpath(mp.NEW_BARCODE_BARCODE_XPATH)).sendKeys(Utils.getBarCode());//before run test , update the BARCODE
		//driver.findElement(By.cssSelector("div.col-sm-8 > #barcode")).sendKeys(Utils.getBarCode());
		base.sleep(500);
		driver.findElement(By.xpath(mp.NEW_BARCODE_SUBMIT_XPATH)).click();
		assertEquals("确定要新增条形码？",base.closeAlertAndGetsItsTest());
		log.info("add isbn barcode success...");
		base.sleep(5000);
	}
	
	/*
	 * verify the login of cookies
	 */
	public void NonLoginCookie(){
		base.getCookies(driver, "cookies-prod.data");
		driver.get(Configs.getMapValue("qaurl1"));
		base.sleep(10000);
	}
	
	@After
	public void end(){
		base.closeDriver();
	}
}
