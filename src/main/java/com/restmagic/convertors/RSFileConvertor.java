package com.restmagic.convertors;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * @author Pratik
 *
 */
public abstract class RSFileConvertor {
	public abstract ResponseBuilder convert(InputStream in) throws IOException;

	public abstract String getContentValidate();

	public abstract String getContentType();

	public abstract String getFileName();

	public abstract String getFileLocation();
}
