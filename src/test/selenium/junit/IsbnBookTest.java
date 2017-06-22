package test.selenium.junit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.assertEquals;

import test.selenium.java.page.ManagerIsbnPage;
import test.selenium.util.Configs;
import test.selenium.util.Locator;
import test.selenium.util.Log;

public class IsbnBookTest {
	private WebDriver driver;
	private Base base = new Base();
	private List<String> list;
	private ManagerIsbnPage mp = new ManagerIsbnPage();
	Actions action;
	Locator locator;
	private Log log = new Log(this.getClass());
	
	@Before
	public void start(){
		driver = base.getDriver("chrome");
		driver.get(Configs.getMapValue("produrl"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		base.getCookies(driver, "cookies.data");
		log.info(" login with cookie success!");
		driver.get(Configs.getMapValue("produrl"));
		log.info(" open chrome browser success!");
		base.sleep(1000);
	}
	
	@Test
	public void test(){
		toIsbnManage();
		findIsbn();
		deleteIsbnBook();
		addIsbnBook();
		editIsbnBook();
		
		backToFirstWindow();
		findIsbn();
		handleIsbnBook();
		base.sleep(20000);
	}
	
	public void toIsbnManage(){
		driver.findElement(By.linkText(mp.ISBN_MANAGE)).click();
		base.sleep(1000);
		driver.findElement(By.xpath(mp.ISBN_MANAGER_XPATH)).click();
		base.sleep(3000);
	}
	
	public void findIsbn(){
		driver.findElement(By.id(mp.BARCOD_ID)).clear();
		driver.findElement(By.id(mp.BARCOD_ID)).sendKeys(mp.BARCODE);//find a ISBN barcode
		base.sleep(1000);
		driver.findElement(By.id(mp.BARCOD_SEARCH_ID)).click();
		base.sleep(500);
	}
	
	public void addIsbnBook(){
		driver.findElement(By.xpath(mp.ADD_BOOK_XPATH)).click();//when only a barcode
		base.sleep(1000);
		assertEquals(mp.ADD_BOOK_CONFIRM , base.closeAlertAndGetsItsTest());
		base.sleep(1000);
	}
	
	public void deleteIsbnBook(){
		List<WebElement> eleList = new ArrayList<WebElement>(driver.findElements(By.className(mp.DELETE_BOOK_CLASS)));
		if(eleList.size() != 0){
			for(int i = 0 ; i < eleList.size() ; i++){
				WebElement e = eleList.get(i);
				e.click();
				//System.out.println("delete " + (i+1) + " book!");
				assertEquals(mp.DELETE_BOOK_CONFIRM , base.closeAlertAndGetsItsTest());
				base.sleep(5000);
			}
		}
	}
	
	public void editIsbnBook(){
		driver.findElement(By.linkText(mp.EDIT_BOOK_LINKTEXT)).click();
		base.sleep(500);
		list = base.getUrls(driver);
		base.switchToNewWindow(driver , list.get(list.size() - 1));
		base.sleep(500);
		driver.findElement(By.name(mp.BOOK_NAME_NAME)).sendKeys(mp.BOOK_NAME);
		base.sleep(500);
		new Select(driver.findElement(By.name(mp.BOOK_TYPE))).selectByVisibleText(mp.BOOK_TYPE_SELECT);
		base.sleep(500);
		new Select(driver.findElement(By.name(mp.BOOK_SUBJECT))).selectByVisibleText(mp.BOOK_SUBJECT_SELECT);
		base.sleep(500);
		new Select(driver.findElement(By.name(mp.BOOK_GRADE))).selectByVisibleText(mp.BOOK_GRADE_SELECT);
		base.sleep(500);
		new Select(driver.findElement(By.name(mp.BOOK_TERM))).selectByVisibleText(mp.BOOK_TERM_SELECT);
		base.sleep(500);
		new Select(driver.findElement(By.name(mp.BOOK_EDITION))).selectByVisibleText(mp.BOOK_EDITION_SELECT);
		
		driver.findElement(By.name(mp.BOOK_PUBLISH_NAME)).sendKeys(mp.BOOK_PUBLISH_CONTENT);
		base.sleep(500);
		driver.findElement(By.name(mp.BOOK_PUBLISH_YEAR)).sendKeys(mp.BOOK_PUBLISH_YEAR_CONTENT);
		base.sleep(500);
		driver.findElement(By.name(mp.BOOK_PUBLISH_MONTH)).sendKeys(mp.BOOK_PUBLISH_MONTH_CONTENT);
		base.sleep(500);
		driver.findElement(By.name(mp.BOOK_PUBLISH_REVISION)).sendKeys(mp.BOOK_PUBLISH_REVISION_CONTENT);
		base.sleep(500);
		driver.findElement(By.name(mp.BOOK_PUBLISH_PRINTYEAR)).sendKeys(mp.BOOK_PUBLISH_PRINTYEAR_CONTENT);
		base.sleep(500);
		driver.findElement(By.name(mp.BOOK_PUBLISH_PRINTMONTH)).sendKeys(mp.BOOK_PUBLISH_PRINTMONTH_CONTENT);
		base.sleep(500);
		driver.findElement(By.name(mp.BOOK_PUBLISH_PRINTREVERSION)).sendKeys(mp.BOOK_PUBLISH_PRINTREVERSION_CONTENT);
		base.sleep(500);
		new Select(driver.findElement(By.name(mp.HAS_ANSWER))).selectByVisibleText(mp.HAS_ANSWER_SELECT);
		base.sleep(1000);
		new Select(driver.findElement(By.name(mp.VALIDATION))).selectByVisibleText(mp.VALIDATION_SELECT);
		base.sleep(1000);
		driver.findElement(By.id(mp.UPLOAD_IMG)).sendKeys(mp.UPLOAD_IMG_PATH);
		base.sleep(10000);
		driver.findElement(By.className(mp.SAVE_BOOK_CLASSNAME)).click();
		base.sleep(500);
		assertEquals(mp.SAVE_BOOK_CONFIRM , base.closeAlertAndGetsItsTest());
		base.sleep(10000);
	}
	
	public void backToFirstWindow(){
		list = base.getUrls(driver);
		action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).keyUp(Keys.CONTROL).sendKeys(Keys.NULL).perform();
		base.sleep(1000);
		base.switchToNewWindow(driver , list.get(0));
	}
	
	public void handleIsbnBook(){
		driver.findElement(By.className(mp.HANDLE_CLASS)).click();
		base.sleep(500);
		new Select(driver.findElement(By.id(mp.HANDLE_O_ID))).selectByVisibleText(mp.HANDLE_O_BUY);
		base.sleep(500);
		new Select(driver.findElement(By.name(mp.HANDLE_P_ID))).selectByVisibleText(mp.HANDLE_P_M);
		base.sleep(500);
		driver.findElement(By.id(mp.HANDLE_E_ID)).sendKeys(mp.HANDLE_E_CON);
		base.sleep(500);
		driver.findElement(By.className(mp.HANDLE_SUBMIT_CLASS)).click();
		base.sleep(500);
		assertEquals(mp.HANDLE_SUBMIT_CONTENT , base.closeAlertAndGetsItsTest());
	}
	
	@After
	public void end(){
		base.closeDriver();
	}
}
