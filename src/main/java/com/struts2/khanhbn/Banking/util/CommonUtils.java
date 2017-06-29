package com.struts2.khanhbn.Banking.util;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

public class CommonUtils {
	
	public static final Logger logger = Logger.getLogger(CommonUtils.class);
	private static CommonUtils instance = null;
	private static final String PATTERN_DATE_1 = "dd MMMM yyyy";
	private static final String DECIMAL_FORMAT = "0.#####";
	
	private CommonUtils(){
		
	}
	
	public static CommonUtils getInstance(){
		if(instance == null ){
			instance = new CommonUtils();
		}
		return instance;
	}
	
	
	public String getDateToString(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonUtils.PATTERN_DATE_1, Locale.ITALY);
		Calendar cal = Calendar.getInstance();

		String date = dateFormat.format(cal.getTime());
		int indexMonth = date.indexOf(" ")+1;
		char monthUppCase = Character.toUpperCase(date.charAt(indexMonth));
		char[] charArrayMonth = date.toCharArray();
		charArrayMonth[indexMonth] = monthUppCase;
		
		String dateResult = String.valueOf(charArrayMonth);

		return dateResult;
	}
	
	public String getDateDDMMYYYToString(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		String dataExport = dateFormat.format(cal.getTime());
		return dataExport;
	}
	
	/**
	 * 
	 * @param inputDate
	 * @return
	 */
	public static String convertDateToString(Date inputDate){
		String result = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(inputDate != null){
				result = dateFormat.format(inputDate);
			}
		} catch (Exception e) {
			logger.error("convertDateToString():"+ e.getMessage());
		}
		return result;
		
	}
	
	public String convertDateToString(Date inputDate, String pattern){
		String result = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat(pattern);
			if(inputDate != null){
				result = dateFormat.format(inputDate);
			}
		} catch (Exception e) {
			logger.error("convertDateToString():"+ e.getMessage());
		}
		return result;
	}
	
	
	public CellStyle arial14Format(Workbook workBook){
		Font arial14Font = workBook.createFont();
		arial14Font.setFontHeightInPoints((short) 14);
		arial14Font.setFontName("Arial");
		arial14Font.setUnderline(Font.U_NONE);
		arial14Font.setBold(true);
		arial14Font.setColor(HSSFColorPredefined.BLUE.getIndex());
		CellStyle arial14Format = workBook.createCellStyle();
		arial14Format.setFont(arial14Font);
		return arial14Format;
	}
	
	public CellStyle arial10Format(Workbook workBook){
		Font arial10Font = workBook.createFont();
		arial10Font.setFontHeightInPoints((short) 12);
		arial10Font.setFontName("Arial");
		arial10Font.setUnderline(Font.U_NONE);
		arial10Font.setBold(true);
		arial10Font.setColor(HSSFColorPredefined.BLUE.getIndex());
		CellStyle arial10Format = workBook.createCellStyle();
		arial10Format.setFont(arial10Font);
		return arial10Format;
	}
	
	public CellStyle times10FormatBoldCenter(Workbook workBook){
		Font times10FontBold = workBook.createFont();
		times10FontBold.setFontHeightInPoints((short) 10);
		times10FontBold.setFontName("Arial");
		times10FontBold.setUnderline(Font.U_NONE);
		times10FontBold.setBold(true);
		times10FontBold.setColor(HSSFColorPredefined.BLACK.getIndex());
		CellStyle times10FormatBoldCenter = workBook.createCellStyle();
		times10FormatBoldCenter.setFont(times10FontBold);
		times10FormatBoldCenter.setAlignment(HorizontalAlignment.CENTER);
		times10FormatBoldCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		times10FormatBoldCenter.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex());
		times10FormatBoldCenter.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		times10FormatBoldCenter.setWrapText(false);
		times10FormatBoldCenter.setBorderTop(BorderStyle.THIN);
		times10FormatBoldCenter.setTopBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatBoldCenter.setBorderRight(BorderStyle.THIN);
		times10FormatBoldCenter.setRightBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatBoldCenter.setBorderBottom(BorderStyle.THIN);
		times10FormatBoldCenter.setBottomBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatBoldCenter.setBorderLeft(BorderStyle.THIN);
		times10FormatBoldCenter.setLeftBorderColor(HSSFColorPredefined.BLACK.getIndex());
		return times10FormatBoldCenter;
	}
	
	public CellStyle times10FormatCenter(Workbook workBook){
		Font times10Font = workBook.createFont();
		times10Font.setFontHeightInPoints((short) 10);
		times10Font.setFontName("Arial");
		times10Font.setUnderline(Font.U_NONE);
		times10Font.setColor(HSSFColorPredefined.BLACK.getIndex());
		CellStyle times10FormatCenter = workBook.createCellStyle();
		times10FormatCenter.setFont(times10Font);
		times10FormatCenter.setAlignment(HorizontalAlignment.CENTER);
		times10FormatCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		times10FormatCenter.setWrapText(true);
		times10FormatCenter.setBorderTop(BorderStyle.THIN);
		times10FormatCenter.setTopBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatCenter.setBorderRight(BorderStyle.THIN);
		times10FormatCenter.setRightBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatCenter.setBorderBottom(BorderStyle.THIN);
		times10FormatCenter.setBottomBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatCenter.setBorderLeft(BorderStyle.THIN);
		times10FormatCenter.setLeftBorderColor(HSSFColorPredefined.BLACK.getIndex());
		return times10FormatCenter;
	}
	
	public CellStyle times10FormatLeft(Workbook workBook){
		Font times10Font = workBook.createFont();
		times10Font.setFontHeightInPoints((short) 10);
		times10Font.setFontName("Arial");
		times10Font.setUnderline(Font.U_NONE);
		times10Font.setColor(HSSFColorPredefined.BLACK.getIndex());
		CellStyle times10FormatCenter = workBook.createCellStyle();
		times10FormatCenter.setFont(times10Font);
		times10FormatCenter.setAlignment(HorizontalAlignment.LEFT);
		times10FormatCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		times10FormatCenter.setWrapText(true);
		times10FormatCenter.setBorderTop(BorderStyle.THIN);
		times10FormatCenter.setTopBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatCenter.setBorderRight(BorderStyle.THIN);
		times10FormatCenter.setRightBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatCenter.setBorderBottom(BorderStyle.THIN);
		times10FormatCenter.setBottomBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatCenter.setBorderLeft(BorderStyle.THIN);
		times10FormatCenter.setLeftBorderColor(HSSFColorPredefined.BLACK.getIndex());
		return times10FormatCenter;
	}
	
	public CellStyle times10FormatRight(Workbook workBook){
		Font times10Font = workBook.createFont();
		times10Font.setFontHeightInPoints((short) 10);
		times10Font.setFontName("Arial");
		times10Font.setUnderline(Font.U_NONE);
		times10Font.setColor(HSSFColorPredefined.BLACK.getIndex());
		CellStyle times10FormatRight = workBook.createCellStyle();
		times10FormatRight.setFont(times10Font);
		times10FormatRight.setAlignment(HorizontalAlignment.RIGHT);
		times10FormatRight.setVerticalAlignment(VerticalAlignment.CENTER);
		times10FormatRight.setWrapText(true);
		times10FormatRight.setBorderTop(BorderStyle.THIN);
		times10FormatRight.setTopBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatRight.setBorderRight(BorderStyle.THIN);
		times10FormatRight.setRightBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatRight.setBorderBottom(BorderStyle.THIN);
		times10FormatRight.setBottomBorderColor(HSSFColorPredefined.BLACK.getIndex());
		times10FormatRight.setBorderLeft(BorderStyle.THIN);
		times10FormatRight.setLeftBorderColor(HSSFColorPredefined.BLACK.getIndex());
		return times10FormatRight;
	}

	public CellStyle msSansSerif10(Workbook workBook){
		Font msSansSerif10Font = workBook.createFont();
		msSansSerif10Font.setFontHeightInPoints((short) 10);
		msSansSerif10Font.setFontName("MS Sans Serif");
		CellStyle msSansSerif10 = workBook.createCellStyle();
		msSansSerif10.setFont(msSansSerif10Font);
		return msSansSerif10;
	}
	
	public String convertDecimalToString(String number){
		DecimalFormat numberFormat = new DecimalFormat("###");
		return numberFormat.format(Double.valueOf(number.replace(",", ".")));
	}
	
	public Row sheetAddRow(Sheet sheet, int row) {
		return sheet.createRow(row);
	}

	public void sheetAddCell(Row row, int columnN, String text, CellStyle styleCell) {
		Cell cell = null;
		cell = row.createCell(columnN);
		cell.setCellValue(text); // getText("header.title");
		cell.setCellStyle(styleCell);
	}
	
	public static String getStringFromNull(String strInput){
		if(strInput == null){
			return "";
		}
		return strInput;
	}
	
	public static boolean isNullObject(Object obj){
		if(obj==null){
			return true;
		}
		return false;
	}
	
	public static String getCellValue(Cell cell) {
		DecimalFormat decimalFormat = new DecimalFormat(DECIMAL_FORMAT);
		CellType cellType = cell.getCellTypeEnum();
		switch (cellType.getCode()) {
		case 1:
			return cell.getStringCellValue();
		case 0:
			return decimalFormat.format(cell.getNumericCellValue()).replace(",", ".");
		case 4:
			return Boolean.toString(cell.getBooleanCellValue());
		}
		return null;
	}
}
