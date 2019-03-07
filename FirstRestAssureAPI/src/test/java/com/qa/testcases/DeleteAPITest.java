package com.qa.testcases;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.restclient.RestClient;
import com.qa.testbase.TestBase;

public class DeleteAPITest extends TestBase {

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
	public void testDeleteClient() {
		httpResponse =  RestClient.getInstance().delete(url);
		
		System.out.println("Delete response status code-->" + httpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), RESPONSE_CODE_204);
	}
	
}
