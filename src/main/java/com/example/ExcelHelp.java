package com.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelp extends VisionHelp {

	@Override
	public void writeData(String outDir, String[] inputFileNames, List<List<String[]>> dataList) {
		XSSFWorkbook wb = new XSSFWorkbook();
		
		for(int i = 0; i < inputFileNames.length; i ++){
			XSSFSheet sheet = wb.createSheet(inputFileNames[i]);
			List<String[]> datas = dataList.get(i);
			
			for(int j = 0; j < datas.size(); j++){
				XSSFRow row = sheet.createRow(j);
				String[] rowData = datas.get(j);
				
				XSSFCell desCell = row.createCell(0);
				desCell.setCellValue(rowData[0]);
				
				XSSFCell scoCell = row.createCell(1);
				scoCell.setCellValue(rowData[1]);
			}
		}
		
		try {
			FileOutputStream fis = new FileOutputStream(outDir + "/result.xlsx");
			
			wb.write(fis);
			fis.flush();
			fis.close();
			wb.close();

		} catch(IOException e){
			e.printStackTrace();
		}
	}
}


