package com.qa.restclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RestClient {

	private static RestClient restClient_instance = null;
	
	public static RestClient getInstance() {
		if(restClient_instance == null)
			restClient_instance = new RestClient();
		return restClient_instance;
	}
	
	CloseableHttpClient httpClient;
	HttpGet httpGet;
	CloseableHttpResponse httpResponse;
	
	HttpPost httpPost;
	HttpDelete httpDelete;
	/*
	 * Get response
	 */
	public CloseableHttpResponse get(String url) {
		try {
			//Get Http Response
			httpClient =  HttpClients.createDefault();
			httpGet = new HttpGet(url);
			httpResponse =  httpClient.execute(httpGet);
			
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return httpResponse;
	}
	
	public JSONObject getJSONResponse() {
		JSONObject jsonResponse = null;
		try {
			String responseString =  EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			
			jsonResponse = new JSONObject(responseString);
		}catch(IOException e) {
			e.printStackTrace();
		}catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonResponse;
	}
	
	public HashMap<String, String> getAllHeaders() {
		Header[] headers =  httpResponse.getAllHeaders();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		for(Header header : headers) {
			hashMap.put(header.getName(), header.getValue());
		}
		return hashMap;
	}
	
	/*
	 * Get method with Headers
	 */
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) {
		
		httpClient = HttpClients.createDefault();
		httpGet = new HttpGet(url);
		
		for(Map.Entry<String, String> entryObj : headerMap.entrySet()) {
			httpGet.addHeader(entryObj.getKey(), entryObj.getValue());
		}
		
		try {
			httpResponse = httpClient.execute(httpGet);
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return httpResponse;
	}
	
	/*
	 * Post Method with headers
	 */
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) {
		httpClient = HttpClients.createDefault();
		
		//This is for Post call with requested url
		httpPost = new HttpPost(url);
		try {
			httpPost.setEntity(new StringEntity(entityString)); //Payload
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//Add headers
		for(Map.Entry<String, String> header : headerMap.entrySet())
			httpPost.addHeader(header.getKey(), header.getValue());
		try {
			httpResponse = httpClient.execute(httpPost);
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return httpResponse;
	}
	
	/*
	 * Delete call method
	 */
	public CloseableHttpResponse delete(String url) {
		httpClient = HttpClients.createDefault();
		httpDelete = new HttpDelete(url);
		try {
			
			httpResponse = httpClient.execute(httpDelete);
		}catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return httpResponse;
	}
	
}