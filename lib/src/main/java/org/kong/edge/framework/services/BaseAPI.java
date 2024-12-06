package org.kong.edge.framework.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kong.edge.framework.http.HttpServiceClient;
import org.kong.edge.framework.utils.JSONUtils;
import org.kong.edge.framework.utils.TestDataInitializer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class BaseAPI {
	private static final Logger log = LogManager.getLogger(BaseAPI.class);

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
        log.info("Bearer Token Found: {}", bearerToken.startsWith("kpat"));
		baseUrl_global = testData.get("baseUrl_global").getAsString();
	}

}
