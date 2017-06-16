package test.selenium.junit;

import java.util.HashMap;
import java.util.Map;

import test.selenium.util.Configs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Alert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Date;

public class Base {
	private WebDriver driver;//browser driver
	private boolean acceptNextAlert = true;//accept the dialog
	
	public WebDriver getDriver(String browserName){
		if(driver == null){
			generateDriver(browserName);
			/*在jvm中增加一个关闭的钩子，当jvm关闭的时候，会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子，当系统执行完这些钩子后jvm才会关闭。
			 * 所以这些钩子可以在jvm关闭的时候进行内存清理、对象销毁等操作。
			*/
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				public void run() {
					if (driver != null) {
						driver.quit();
					}
				}
			}));
			return driver;
		}
		return null;
	}
	
	public void closeDriver(){
		driver.quit();
		driver = null;//initialize the dirver
	}
	
	public void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
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
			driver = new ChromeDriver(setChromeOptions());//config chrome then new driver
			sleep(1000);
		}else{
			System.setProperty(Configs.getMapValue("webdriver.ie.driver.name"),Configs.getMapValue("webdriver.ie.driver.path"));
			driver = new InternetExplorerDriver();
		}
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
			file.delete();//if there is same file,delete it
			file.createNewFile();//create a new file
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for(Cookie ck : cookies){
				bw.write(ck.getName() + ";"  + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";" +ck.getExpiry() + ";" +ck.isSecure());
				bw.newLine();
			}
			bw.flush();
			bw.close();
			fw.close();
		}catch(IOException e){
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
					driver.manage().addCookie(cookie);
				}
			}
			br.close();
			fr.close();
		} catch(IOException e){
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
			}else{
				alert.dismiss();
			}
			return alertText;
		}finally{
			acceptNextAlert = true;
		}
	}
	
	//get all open urls
	public List<String> getUrls(WebDriver driver){
		return new ArrayList<String>(driver.getWindowHandles());
	}
	
	//switch to the new window
	public void switchToNewWindow(WebDriver driver,String s){
		driver.switchTo().window(s);
	}
	
}
