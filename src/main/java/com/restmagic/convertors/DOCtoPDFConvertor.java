package com.restmagic.convertors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;


public class DOCtoPDFConvertor extends RSFileConvertor{
	//private static final String CONTENT_VALIDATE = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	private static final String CONTENT_VALIDATE = "application/msword";
	public String getContentValidate() {
		return CONTENT_VALIDATE;
	}
	public  String getFilename() {
		return FILENAME;
	}
	public String getFileLocation() {
		return FILE_LOCATION;
	}
	public  String getContentType() {
		return CONTENT_TYPE;
	}
	private static final String FILENAME = "DOCtoPDF.pdf";
	private static final String FILE_LOCATION = "/Users/Pratik/Documents/new_workspace/restmagic-ws/src/main/resources/temp.pdf";
	private static final String CONTENT_TYPE = "application/pdf";
	@Override
	public ResponseBuilder convert(InputStream in) throws IOException {
		// TODO Auto-generated method stub
		
		WordprocessingMLPackage wmlPackage;
		OutputStream os = null;
		
		try {
			wmlPackage = WordprocessingMLPackage.load(in);
			os = new FileOutputStream(FILE_LOCATION);
			
			Docx4J.toPDF(wmlPackage, os);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Docx4JException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		return null;
	}
}
