package test.selenium.util;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.*;

public class Base {
	private WebDriver driver;//browser driver
	private boolean acceptNextAlert = true;//accept the dialog
	private Log log = new Log(this.getClass());
	private String[][] locatorMap;

	public Base(){
		
	}
	
	public Base(WebDriver driver) throws IOException{
		this.driver = driver;
		//locatorMap = ReadExcelUtil.getLocatorMap();//get html locator from .xls
	}
	public WebDriver getDriver(){
		return this.driver;
	}
	public void setDriver(WebDriver driver){
		this.driver = driver;
	}
	
	public WebDriver getDriver(String browserName){
		if(driver == null){
			generateDriver(browserName);
			/*在jvm中增加一个关闭的钩子，当jvm关闭的时候，会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子，当系统执行完这些钩子后jvm才会关闭。
			 * 所以这些钩子可以在jvm关闭的时候进行内存清理、对象销毁等操作。
			*/
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					if (driver != null) {
						log.info("driver will quit!");
						driver.quit();
					}
				}
			}));
			locatorMap = ReadExcelUtil.getLocatorMap();//get html locator from .xls
			log.info("get locatorMap success! size=" + locatorMap.length);
			return driver;
		}
		log.warn("driver is null!");
		return null;
	}

	public void type(WebDriver driver , Locator locator) throws IOException{
		Locator loc = getLocator(locator.getElement());
		log.info("locator == " + loc.toString());
		WebElement e = findElement(driver , loc);
		e.sendKeys(loc.getElementValue());
	}

	public void click(WebDriver driver , Locator locator) throws Exception{
		Locator loc = getLocator(locator.getElement());
		WebElement e = findElement(driver , loc);
		log.info("click button!");
		e.click();
	}

	public void select(Locator locator , String value) throws Exception{
		Locator loc = getLocator(locator.getElement());
		WebElement e = findElement(driver , loc);
		Select s = new Select(e);
		try{
			log.info("select by value : " + value);
			s.selectByValue(value);
		}catch (Exception notByValue){
			log.info("select by VisibleText " + value);
			s.selectByVisibleText(value);
		}
	}

	/**
	 * get Locator from locatorMap array
	 * @param locatorName
	 * @return Locator
	 * @throws IOException
	 */
	public Locator getLocator(String locatorName) throws IOException {
		log.info("get Locator from locatorMap == " + locatorName);
		for(int i = 1 ; i < locatorMap.length ; i++){
			if(locatorMap[i][0].endsWith(locatorName)){
				Locator locator = new Locator();
				locator.setElement(locatorMap[i][1]);//element_id = locatorMap[i][1]
				locator.setBy(Locator.getBy(locatorMap[i][4]));
				locator.setWaitSec(Long.valueOf(getSubStr(locatorMap[i][3])));
				if(null != locatorMap[i][2]){
					if(locatorMap[i][2].endsWith(".0")){
						locator.setElementValue(getSubStr(locatorMap[i][2]));
					}else{
						locator.setElementValue(locatorMap[i][2]);
					}
				}
				return locator;
			}
		}
		return new Locator(locatorName);
	}


	public WebElement findElement(WebDriver driver , Locator locator){
		WebElement element = (new WebDriverWait(driver , locator.getWaitSec())).until(new ExpectedCondition<WebElement>(){
			@Override
			public WebElement apply(WebDriver d){
				try{
					return getElement(d , locator);
				}catch(IOException e){
					log.error("can't find element " + locator.getElement());
					return null;
				}
			}
		});
		return element;
	}
	
	public WebElement getElement(Locator locator) throws Exception{
		return getElement(this.getDriver() , locator);
	}

	/**
	 * parse locator , then get element
	 * @param driver
	 * @return WebElement
	 * @throws IOException
	 * @author yuanyuanyang1 2017-06-20
	 */
	public WebElement getElement(WebDriver driver , Locator locator) throws IOException{
		/*Locator locator = getLocator(sourcelocator.getElement());
		if(locator == null){
			locator = new Locator(sourcelocator.getElement() , sourcelocator.getElementValue() , sourcelocator.getWaitSec() , sourcelocator.getBy());
		}*/
		WebElement e;
		switch(locator.getBy()){
		case xpath : 
			log.debug(" find element by xpath");
			e = driver.findElement(By.xpath(locator.getElement()));
			break;
		case id :
			log.debug(" find element by id");
			e = driver.findElement(By.id(locator.getElement()));
			break;
		case name :
			log.debug(" find element by name");
			e = driver.findElement(By.name(locator.getElement()));
			break;
		case cssSelector :
			log.debug(" find element by cssSelector");
			e = driver.findElement(By.cssSelector(locator.getElement()));
			break;
		case className :
			log.debug(" find element by className");
			e = driver.findElement(By.className(locator.getElement()));
			break;
		case tagName :
			log.debug(" find element by tagName");
			e = driver.findElement(By.tagName(locator.getElement()));
			break;
		case partialLinkText :
			log.debug(" find element by partialLinkText");
			e = driver.findElement(By.partialLinkText(locator.getElement()));
			break;
		default :
			e = driver.findElement(By.id(locator.getElement()));
		}
		return e;
	}
	
	/**
	 * 
	 * @param driver
	 * @param mylocator
	 * @param timeout
	 * @return
	 * @throws IOException
	 */
	public boolean isElementPresent(WebDriver driver , Locator mylocator , long timeout) throws IOException{
		final Locator locator = getLocator(mylocator.getElement()); 
		boolean isPresent = false;
		WebDriverWait wait = new WebDriverWait(driver , 60);
		isPresent = wait.until(new ExpectedCondition<WebElement>(){
			@Override
			public WebElement apply(WebDriver d){
				return findElement(d , locator);
			}
		}).isDisplayed();
		return isPresent;
	}
	
	/**
	 * check isPresent element
	 * @param locator
	 * @param timeout
	 * @return	false/true
	 * @throws IOException
	 */
	public boolean isElementPresent(Locator locator , long timeout) throws IOException{
		return isElementPresent(driver , locator , timeout);
	}

	//change configs of Chrome browser
	public ChromeOptions setChromeOptions(){
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");//max Chrome browser
		options.addArguments("--disable-popup-blocking");//not allow the popup of if store the username/password
		options.addArguments("no-sandbox");
		options.addArguments("disable-extensions");
		options.addArguments("no-default-browser-check");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile_password_manage_enabled", false);

		options.setExperimentalOption("prefs", prefs);
		return options;
	}

	//save cookies into the file
	public void saveCookies(String path , Set<Cookie> cookies){
		File file;
		FileWriter fw;
		BufferedWriter bw;
		try{
			file = new File(path);
			log.info("delete cookies-prod.data success!");
			file.delete();//if there is same file,delete it
			log.info("create cookies-prod.data success!");
			file.createNewFile();//create a new file
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for(Cookie ck : cookies){
				bw.write(ck.getName() + ";"  + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";" +ck.getExpiry() + ";" +ck.isSecure());
				log.info("a new line write success!");
				bw.newLine();
			}
			bw.flush();
			bw.close();
			fw.close();
			log.info(" save cookies success!");
		}catch(IOException e){
			log.error("write cookies-prod.data IOException!");
			e.printStackTrace();
		}
	}

	//get the cookies from the file
	public void getCookies(WebDriver driver , String filepath){
		File file;
		FileReader fr;
		BufferedReader br;
		try{
			file = new File(filepath);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null){
				/*
				 * String[] sa = line.split(";");
				 * for(String tmp : sa){
						System.out.println(tmp);
					}
				 */
				log.info(" read cookies-prod.data !");
				StringTokenizer st = new StringTokenizer(line , ";");
				while(st.hasMoreTokens()){
					String name = st.nextToken();
					String value = st.nextToken();
					String domain = st.nextToken();
					String path = st.nextToken();
					String expiryStrign = st.nextToken();//for ignore the Date,can not omit
					Date expiry = null;
					boolean isSecure = new Boolean(st.nextToken()).booleanValue();
					Cookie cookie = new Cookie(name , value , domain , path , expiry , isSecure);
					log.info(" create a cookie success!");
					driver.manage().addCookie(cookie);
					log.info(" the new cookie is " + cookie.toString());
					log.info(" driver.manage().addCookie() success!");
				}
			}
			br.close();
			fr.close();
		} catch(IOException e){
			log.error(" read cookies-prod.data IOException!");
			e.printStackTrace();
		}
	}

	//alert() dialog
	public String closeAlertAndGetsItsTest(){
		try{
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if(acceptNextAlert){
				alert.accept();
				log.info(" accept success !");
			}else{
				alert.dismiss();
				log.info(" cancel success !");
			}
			return alertText;
		}finally{
			acceptNextAlert = true;
		}
	}

	public void closeDriver(){
		//driver.close();
		log.info("driver will quit!");
		driver.quit();
		driver = null;//initialize the dirver
	}

	public void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			log.error("InterruptedException in sleep......");
			e.printStackTrace();
		}
	}

	/**
	 * generate browser driver	:Chrome or InternetExplorer
	 * @param browserName	:the name of browser
	 * @author yuanyuanyang1	2017-06-13
	 */
	public void generateDriver(String browserName){
		if(browserName.equals("chrome")){
			System.setProperty(Configs.getMapValue("webdriver.chrome.driver.name"),Configs.getMapValue("webdriver.chrome.driver.path"));
			log.info(" setProperty for ChromeDriver success!");
			driver = new ChromeDriver(setChromeOptions());//config chrome then new driver
			log.info(" create ChromeDriver！success!");
			sleep(500);
		}else{
			System.setProperty(Configs.getMapValue("webdriver.ie.driver.name"),Configs.getMapValue("webdriver.ie.driver.path"));
			log.info(" setProperty for InternetExplorerDriver success!");
			driver = new InternetExplorerDriver();
			log.info(" create InternetExplorerDriver success！");
			sleep(500);
		}
	}
	//get all open urls
	public List<String> getUrls(WebDriver driver){
		return new ArrayList<String>(driver.getWindowHandles());
	}

	//switch to the new window
	public void switchToNewWindow(WebDriver driver,String s){
		driver.switchTo().window(s);
		log.info("driver.switchTo().window(X)!");
	}

	public String getSubStr(String s){
		return s.substring(0 , s.length() - 2);//得到字符串前你-2位子串
	}

}
