package org.kong.edge.framework.services;

import org.kong.edge.framework.http.HttpServiceClient;
import org.kong.edge.framework.utils.JSONUtils;
import org.kong.edge.framework.utils.TestDataInitializer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class BaseAPI {
	
	protected HttpServiceClient client;
	protected Gson jsonObject;
	protected JsonObject testData;
	protected String baseUrl;
	protected String baseUrl_global;
	protected String bearerToken;
	protected String brokerUrl;

	BaseAPI(){
		
		this.client = new HttpServiceClient();
		this.jsonObject = JSONUtils.jsonObject();
		testData = TestDataInitializer.loadTestData();
		baseUrl = testData.get("baseUrl").getAsString();
		bearerToken = System.getenv("KONG_API_KEY");
		baseUrl_global = testData.get("baseUrl_global").getAsString();
	}

}
