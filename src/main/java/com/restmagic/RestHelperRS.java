package com.restmagic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.restmagic.accessibility.AccessibilityProvider;
import com.restmagic.convertors.RSFileConvertor;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Path("/api")
public class RestHelperRS {
	@Autowired
	ApplicationContext appcontext;

	@GET
	@Path("/{attribute1}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Info(@PathParam("attribute1") String company1) throws IOException {
		Map<String, String> myMap = new HashMap<String, String>();
		myMap.put("name", company1);
		myMap.put("length", "1");
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(myMap);
		return Response.status(200).entity(json).build();

	}

	@POST
	@Path("/file/{attribute1}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@PathParam("attribute1") String convertor,
			@Multipart(required = false, value = "file") Attachment fileDetail) throws IOException {

		InputStream uploadInputStream = fileDetail.getObject(InputStream.class);
		String contentType = fileDetail.getContentType().toString();
		RSFileConvertor rf = (RSFileConvertor) appcontext.getBean(convertor);
		if (!rf.getContentValidate().equals(contentType)) {
			System.out.println(contentType);
			return Response.status(400).entity("Please enter valid file").build();
		}

		ResponseBuilder response = rf.convert(uploadInputStream);

		return response.build();
	}
	@GET
	@Path("/formats/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccesibilitySchema() throws IOException {
		String[] providers = new String[]{"csvToxls","jsonToxml"};
		ObjectWriter ow = new ObjectMapper().writer();
		String json = ow.writeValueAsString(providers);
		return Response.status(200).entity(json).build();
		
	}
	
	    @POST
	    @Path("/files/{attribute1}")
	    @Consumes(MediaType.MULTIPART_FORM_DATA)
	    public Response uploadAttachment(@PathParam("attribute1") String convertor, InputStream attachmentInputStream) throws IOException {
	        // do something with the input stream
			System.out.println("Reached");
		 	RSFileConvertor rf = (RSFileConvertor) appcontext.getBean(convertor);
			ResponseBuilder response = rf.convert(attachmentInputStream);
			return response.build();
	    }
	

}