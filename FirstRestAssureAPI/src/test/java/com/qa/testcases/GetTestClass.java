package com.qa.testcases;

import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.restclient.RestClient;
import com.qa.testbase.TestBase;
import com.qa.util.TestUtil;

public class GetTestClass extends TestBase {

	TestBase testBase;
	String sUrl;
	String sAppUrl;
	String url;
	CloseableHttpResponse httpResponse;

	
	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		sUrl = prop.getProperty("URL");
		sAppUrl = prop.getProperty("ServiceURL");
		url = sUrl + sAppUrl;
	}
	
	@Test
	public void testGetCall() {
		
		//Get status code
		httpResponse =  RestClient.getInstance().get(url);
		int statusCode =  httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_CODE_200, "Status code is not 200");
		
		//Get JSON string response
		JSONObject responseJSON =  RestClient.getInstance().getJSONResponse();
		System.out.println("JSON response code-->" + responseJSON);
		Assert.assertEquals(Integer.parseInt((TestUtil.getValueByJPath(responseJSON, "/per_page"))), 3);
		Assert.assertEquals(Integer.parseInt((TestUtil.getValueByJPath(responseJSON, "/total_pages"))), 4);
		//Assert Data Array
		Assert.assertEquals((TestUtil.getValueByJPath(responseJSON, "/data[0]/first_name")), "Eve");
		
		//Return all headers
		System.out.println("All Headers-->" + RestClient.getInstance().getAllHeaders());
		Assert.assertEquals(TestUtil.getValueByHeaderPath(RestClient.getInstance().getAllHeaders(), "Transfer-Encoding"), "chunked", "Header doesn't exist");
	}
	
	@Test
	public void getAPIWithHeaders() {
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		httpResponse = RestClient.getInstance().get(url, headerMap);
		
		int statusCode =  httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_CODE_200, "Status code is not 200");
		
		//Get JSON string response
		JSONObject responseJSON =  RestClient.getInstance().getJSONResponse();
		System.out.println("JSON response code-->" + responseJSON);
		Assert.assertEquals(Integer.parseInt((TestUtil.getValueByJPath(responseJSON, "/per_page"))), 3);
		Assert.assertEquals(Integer.parseInt((TestUtil.getValueByJPath(responseJSON, "/total_pages"))), 4);
		//Assert Data Array
		Assert.assertEquals((TestUtil.getValueByJPath(responseJSON, "/data[0]/first_name")), "Eve");
		
		//Return all headers
		System.out.println("All Headers-->" + RestClient.getInstance().getAllHeaders());
		Assert.assertEquals(TestUtil.getValueByHeaderPath(RestClient.getInstance().getAllHeaders(), "Transfer-Encoding"), "chunked", "Header doesn't exist");
	}
}
