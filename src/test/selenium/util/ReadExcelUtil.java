package test.selenium.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class ReadExcelUtil {

	static String filepath;
	
	@SuppressWarnings("deprecation")
	public static String[][] getLocatorMap() {
		filepath = "src/UILibrary.xls";
		File file = new File(filepath);
		try {
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(fis));
			Sheet sheet = wb.getSheetAt(0);//get the first sheet
			Row header = sheet.getRow(0);//get the first row
			String[][] locatorMap = new String[sheet.getLastRowNum() + 1][header.getLastCellNum()];
			for(Row row : sheet){
				if(row == null){
					//this whole row is empty
					continue;
				}
				String value;
				for(Cell cell : row){
					if(cell == null){
						//this is cell is empty
						continue;
					}else{
						value = "";
					}
					switch(cell.getCellType()){
					case Cell.CELL_TYPE_STRING : 
						value = cell.getRichStringCellValue().toString();
						break;
					case Cell.CELL_TYPE_NUMERIC :
						if(DateUtil.isCellDateFormatted(cell)){
							value = cell.getDateCellValue().toString();
						}else{
							value = Double.toString(cell.getNumericCellValue());
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN : 
						value = Boolean.toString(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA : 
						value = cell.getCellFormula().toLowerCase();
						break;
					default : 
						value = " ";
					}
					locatorMap[row.getRowNum()][cell.getColumnIndex()] = value;
					//System.out.println("locatorMap[" + row.getRowNum() + "][" + cell.getColumnIndex() + "]=" + value);
				}
			}
			fis.close();
			wb.close();
			return locatorMap;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		getLocatorMap();
		/*
		locatorMap[0][0]=LocatorName
		locatorMap[0][1]=element
		locatorMap[0][2]=waitSec
		locatorMap[0][3]=byType
		locatorMap[1][0]=[com.dbyl.libarary.pageAction.HomePage]
		locatorMap[2][0]=profile
		locatorMap[2][1]=//div[@class='top-nav-profile']//img[@class='avatar']
		locatorMap[3][0]=[com.dbyl.libarary.pageAction.LoginPage]
		locatorMap[4][0]=loginEmailInputBox
		locatorMap[4][1]=//input[@name='email']
		locatorMap[5][0]=loginButton
		locatorMap[5][1]=//button[@class='sign-button']
		locatorMap[6][0]=profile
		locatorMap[6][1]=//div[@class='top-nav-profile']//img[@class='avatar']
		locatorMap[7][0]=loginPasswordInputBox
		locatorMap[7][1]=//input[@name='password'] 
		*/
	}
}
