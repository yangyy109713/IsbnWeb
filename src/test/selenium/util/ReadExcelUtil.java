package test.selenium.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class ReadExcelUtil {

	static String filepath;
	static Log log = new Log(ReadExcelUtil.class);

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
					//log.info("locatorMap[" + row.getRowNum() + "][" + cell.getColumnIndex() + "]=" + value);
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
		//getLocatorMap();
		String s1 = "888888.0";
		System.out.println(s1.substring(0 , s1.length() - 2));
	}
}
