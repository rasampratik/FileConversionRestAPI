package com.restmagic.accessibility;

import java.util.HashMap;
import java.util.Map;
import java.net.HttpURLConnection;
public class AccessibilityProvider {
public static Map<String,Map<String,String>> accessMap = new HashMap<String,Map<String,String>>();

public static Map<String,Map<String,String>> getElements(){
	accessMap.put("div", new HashMap<String,String>());
	accessMap.put("input", new HashMap<String,String>());
	accessMap.put("form", new HashMap<String,String>());
	accessMap.put("table", new HashMap<String,String>());
	accessMap.put("tr", new HashMap<String,String>());
	accessMap.get("div").put("key", "aria-label");
	accessMap.get("div").put("value", "This is a div tag");
	accessMap.get("input").put("key", "aria-label");
	accessMap.get("input").put("value", "This is a input tag");
	accessMap.get("form").put("key", "aria-label");
	accessMap.get("form").put("value", "This is a form tag");
	accessMap.get("table").put("key", "aria-label");
	accessMap.get("table").put("value", "aria-label");
	accessMap.get("tr").put("key", "aria-label");
	accessMap.get("tr").put("value", "This is a table row");
	//accessMap.get("table").put("aria-label", "This is a table element");
	return accessMap;
}
}
