package com.restmagic.convertors;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class CSVtoXLConvertor extends RSFileConvertor {
	private static final String CONTENT_VALIDATE = "text/csv";
	private static final String FILENAME = "CSVToXL.xls";
	private static final String FILE_LOCATION = "/Users/Pratik/Documents/new_workspace/restmagic-ws/src/main/resources/temp.xls";

	public String getContentValidate() {
		return CONTENT_VALIDATE;
	}

	public String getContentType() {
		return CONTENT_TYPE;
	}

	private static final String CONTENT_TYPE = "application/vnd.ms-excel";

	@SuppressWarnings("deprecation")
	public ResponseBuilder convert(InputStream in) throws IOException {

		ArrayList<ArrayList<String>> arList = null;
		ArrayList<String> al = null;
		String fname = "/Users/Pratik/Documents/new_workspace/restmagic-ws/src/main/resources/temp.xls";
		String thisLine;
		DataInputStream myInput = new DataInputStream(in);
		int i = 0;
		arList = new ArrayList<ArrayList<String>>();
		while ((thisLine = myInput.readLine()) != null) {
			al = new ArrayList<String>();
			String strar[] = thisLine.split(",");
			for (int j = 0; j < strar.length; j++) {
				al.add(strar[j]);
			}
			arList.add(al);
			i++;
		}
		try {
			HSSFWorkbook hwb = new HSSFWorkbook();
			HSSFSheet sheet = hwb.createSheet("new sheet");
			for (int k = 0; k < arList.size(); k++) {
				ArrayList ardata = (ArrayList) arList.get(k);
				HSSFRow row = sheet.createRow((short) 0 + k);
				for (int p = 0; p < ardata.size(); p++) {
					HSSFCell cell = row.createCell((short) p);
					String data = ardata.get(p).toString();
					if (data.startsWith("=")) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						data = data.replaceAll("\"", "");
						data = data.replaceAll("=", "");
						cell.setCellValue(data);
					} else if (data.startsWith("\"")) {
						data = data.replaceAll("\"", "");
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(data);
					} else {
						data = data.replaceAll("\"", "");
						cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell.setCellValue(data);
					}

				}
				
			}
			FileOutputStream fileOut = new FileOutputStream(fname);
			hwb.write(fileOut);
			fileOut.close();
			System.out.println("Your excel file has been generated");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		File file = new File(this.getFileLocation());
		ResponseBuilder response = Response.ok((Object) file);

		response.header("Content-Disposition", "attachment; filename=\"" + this.getFileName() + "\"");
		response.header("Content-Type", this.getContentType());
		return response;
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return FILENAME;
	}

	@Override
	public String getFileLocation() {
		// TODO Auto-generated method stub
		return FILE_LOCATION;
	}
	public CSVtoXLConvertor(){
		System.out.println("CSV Constructor called");
	}
}
