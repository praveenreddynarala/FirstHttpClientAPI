package com.qa.util;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestUtil {

	
	public static String getValueByJPath(JSONObject responsejson, String jpath){
		Object obj = responsejson;
		try {
			for(String s : jpath.split("/")) 
				if(!s.isEmpty()) 
					if(!(s.contains("[") || s.contains("]")))
						obj = ((JSONObject) obj).get(s);
					else if(s.contains("[") || s.contains("]"))
						obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		}catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	public static String getValueByHeaderPath(HashMap<String, String> allHeadersMap, String sKey){
		String sHeader = "";
		if(sKey != null) {
			sHeader = allHeadersMap.get(sKey);
		}
		return sHeader;
	}
	
}
