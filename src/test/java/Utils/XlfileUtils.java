package Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlfileUtils {
	XSSFWorkbook wb;
	public XlfileUtils(String Xlpath) throws Throwable {
		FileInputStream fi=new FileInputStream(Xlpath);
		wb=new XSSFWorkbook(fi);
		
	}
	public int rowcount(String Sheet) {
		return wb.getSheet(Sheet).getLastRowNum();
		
	}
	public String getcellData(String sheetname,int row,int column) {
		String data;
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC) {
			int celldata=(int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data=String.valueOf(celldata);
			
		}else {
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}return data;
		
		
	}
	public void setCellData(String sheetname,int row,int column,String status,String writeExcel) throws Throwable {
		XSSFSheet ws=wb.getSheet(sheetname);
		XSSFRow lastrownum=ws.getRow(row);
		XSSFCell cell=lastrownum.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass")) {
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			lastrownum.getCell(column).setCellStyle(style);			
		}else if(status.equalsIgnoreCase("fail")) {
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			lastrownum.getCell(column).setCellStyle(style);
			
		}
		else if(status.equalsIgnoreCase("blocked")) {
			XSSFCellStyle style=wb.createCellStyle();
			XSSFFont font=wb.createFont();
			font.setColor(IndexedColors.BLUE .getIndex());
			font.setBold(true);
			style.setFont(font);
			lastrownum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo=new FileOutputStream(writeExcel);
		wb.write(fo);
	}
	
		

}
