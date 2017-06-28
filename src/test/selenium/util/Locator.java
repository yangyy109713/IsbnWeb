package test.selenium.util;

public class Locator {
	/**
	 * class for element library
	 * @author yuanyuanyang1
	 */
	private String element;
	private long waitSec;
	private String elementValue;

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
		this.byType = ByType.id;
	}
	public Locator(String element , long waitSec){
		this.element = element;
		this.waitSec = waitSec;
		this.elementValue = "";
		this.byType = ByType.id;
	}
	public Locator(String element , long waitSec , ByType byType){
		this.element = element;
		this.waitSec = waitSec;
		this.byType = byType;
	}
	public Locator(String element , String elementValue , long waitSec , ByType byType){
		this.element = element;
		this.elementValue = elementValue;
		this.waitSec = waitSec;
		this.byType = byType;
	}
	public String getElement(){
		return this.element;
	}
	public long getWaitSec(){
		return this.waitSec;
	}
	public void setWaitSec(long waitSec){
		this.waitSec = waitSec;
	}
	public ByType getBy(){
		return this.byType;
	}
	public static ByType getBy(String byType){
		switch (byType){
			case "xpath":
				return ByType.xpath;
			case "linktext":
				return ByType.linktext;
			case "name":
				return ByType.name;
			case "className":
			 	return ByType.className;
			case "cssSelector":
				return ByType.cssSelector;
			case "partialLinkText":
				return ByType.partialLinkText;
			case "tagName":
				return ByType.tagName;
			default :
				return ByType.id;
		}
	}

	public void setBy(ByType byType){
		this.byType = byType;
	}

	public void setBy(String byType){
		switch (byType){
			case "xpath":
				this.byType = ByType.xpath;
				break;
			case "linktext":
				this.byType = ByType.linktext;
				break;
			case "name":
				this.byType = ByType.name;
				break;
			case "className":
				this.byType = ByType.className;
				break;
			case "cssSelector":
				this.byType = ByType.cssSelector;
				break;
			case "partialLinkText":
				this.byType = ByType.partialLinkText;
				break;
			case "tagName":
				this.byType = ByType.tagName;
				break;
			default :
				this.byType = ByType.id;
		}
	}

	public String getElementValue(){
		return this.elementValue;
	}
	public void setElementValue(String elementValue){
		this.elementValue = elementValue;
	}
	public String toString(){
		return this.element + " , " + this.elementValue + " , " + this.waitSec
				+ " , " + this.byType;
	}
}
