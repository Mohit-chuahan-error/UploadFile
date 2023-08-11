package com.API.helper;

import java.io.InputStream;
import java.util.Iterator;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.API.model.Tutorial;

public class ExcelHelper {
	
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	  static String[] HEADERs = { "Id", "Title", "Description", "Published" };
	  static String Tutorials = "Tutorials";

	  
	  public static boolean hasExcelFormat(MultipartFile file) {

		    if (!TYPE.equals(file.getContentType())) {
		      return false;
		    }

		    return true;
		  }
	  
	  public static List<Tutorial> excelToTutorials(InputStream is) {
		  //static String SHEET = "Tutorials";
		  // Sheet sheet = workbook.getSheet(SHEET);
		  
		  //Workbook workbook = WorkbookFactory.create(is);
		  //Sheet sheet = workbook.getSheetAt(0);
		  try {
			
			  XSSFWorkbook workbook = new XSSFWorkbook(is);
			  
			  XSSFSheet sheet=workbook.getSheet("Sheet_name");
		      Iterator<Row> rows = sheet.iterator();

		      List<Tutorial> tutorials = new ArrayList<Tutorial>();

		      int rowNumber = 0;
		      while (rows.hasNext()) {
		        Row currentRow = rows.next();

		        // skip header
		        if (rowNumber == 0) {
		          rowNumber++;
		          continue;
		        }

		        Iterator<Cell> cellsInRow = currentRow.iterator();

		        Tutorial tutorial = new Tutorial();

		        int cellIdx = 0;
		        while (cellsInRow.hasNext()) {
		          Cell currentCell = cellsInRow.next();

		          switch (cellIdx) {
		          case 0:
		            tutorial.setId((long) currentCell.getNumericCellValue());
		            break;

		          case 1:
		            tutorial.setTitle(currentCell.getStringCellValue());
		            break;

		          case 2:
		            tutorial.setDescription(currentCell.getStringCellValue());
		            break;

		          case 3:
		            tutorial.setPublished(currentCell.getBooleanCellValue());
		            break;

		          default:
		            break;
		          }

		          cellIdx++;
		        }

		        tutorials.add(tutorial);
		      }

		      workbook.close();

		      return tutorials;
			  
		} catch (Exception e) {
			 throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	  }
}
