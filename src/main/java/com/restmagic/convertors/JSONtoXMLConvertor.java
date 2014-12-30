package com.restmagic.convertors;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.xml.XmlMapper;

public class JSONtoXMLConvertor extends RSFileConvertor {
	private static final String CONTENT_VALIDATE = "application/json";
	private static final String FILENAME = "JSONToXML.xml";
	private static final String FILE_LOCATION = "/Users/Pratik/Documents/new_workspace/restmagic-ws/src/main/resources/temp.xml";
	private static final String CONTENT_TYPE = "application/xml";

	@SuppressWarnings("resource")
	@Override
	public ResponseBuilder convert(InputStream in) throws IOException {
		// TODO Auto-generated method stub
		String str = convertStreamToString(in);

		ObjectMapper jsonMapper = new ObjectMapper();
		XmlMapper xmlMapper = new XmlMapper();

		String browsersAsXml = xmlMapper.writeValueAsString(jsonMapper.readTree(str));

		File file = new File(FILE_LOCATION);
		try (FileOutputStream fop = new FileOutputStream(file)) {

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = browsersAsXml.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File file2 = new File(this.getFileLocation());
		ResponseBuilder response = Response.ok((Object) file2);

		response.header("Content-Disposition", "attachment; filename=\"" + this.getFileName() + "\"");
		response.header("Content-Type", this.getContentType());
		System.out.println("JSON Converted");
		return response;
	}

	@Override
	public String getContentValidate() {
		// TODO Auto-generated method stub
		return CONTENT_VALIDATE;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return CONTENT_TYPE;
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

	static String convertStreamToString(InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
	public JSONtoXMLConvertor(){
		System.out.println("JSON Constructor called");
	}
}
