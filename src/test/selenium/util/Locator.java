package test.selenium.util;

public class Locator {
	/**
	 * class for element library
	 * @author yuanyuanyang1
	 */
	private String element;
	private long waitSec;
	
	/**
	 * an enum variable for By
	 * @author yuanyuanyang1
	 *
	 */
	public enum ByType{
		xpath, id, linktext, name, className, cssSelector, partialLinkText, tagName
	}
	private ByType byType;
	
	public Locator(){
		
	}
	public Locator(String element){
		this.element = element;
		this.waitSec = 3;
		this.byType = ByType.xpath;
	}
	public Locator(String element , long waitSec){
		this.element = element;
		this.waitSec = waitSec;
		this.byType = ByType.xpath;
	}
	public Locator(String element , long waitSec , ByType byType){
		this.element = element;
		this.waitSec = waitSec;
		this.byType = byType;
	}
	
	public String getElement(){
		return this.element;
	}
	public long getWaitSec(){
		return this.waitSec;
	}
	public ByType getBy(){
		return this.byType;
	}
	public void setBy(ByType byType){
		this.byType = byType;
	}
}
