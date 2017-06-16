package test.selenium.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class Configs {
	
	/**
	 * read :the content of config.properties
	 * @param	pathnmae :the path of config.properties
	 * @return	map	:be defined as static Field,store the key-value
	 * @author yuanyuanyang1 2017-06-09
	 */
	private static HashMap<String , String> map = new HashMap<String , String>();
	private static final String pathname = "src\\config.properties";//the path of config.properties
	
	@SuppressWarnings("rawtypes")
	public static HashMap<String , String> parseProperty(String pathname){
		Properties prop = new Properties();
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(pathname));
			prop.load(is);
			Iterator it = prop.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key = (String) it.next();
				String value = prop.getProperty(key);
				map.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}
	
	/**
	 * get :value by key from the map
	 * @param pathname	:the path of config.properties
	 * @param name	:the accurate key
	 * @return	:return the value
	 */
	public static String getMapValue(String name){
		map = parseProperty(pathname);
		if(map.containsKey(name)){
			return map.get(name);
		}
		return null;
	}
	
	public static void main(String[] args){
		HashMap<String , String> map = parseProperty(pathname);
		String driverName = map.get("browser.driver.name");
		String driverPath = map.get("browser.driver.path");
		System.out.println(driverName+","+driverPath);
	}
}
