package org.kong.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.kong.edge.framework.models.*;
import org.kong.edge.framework.utils.JSONUtils;
import org.kong.edge.framework.utils.TestDataInitializer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class BaseTest {
	
	protected Gson jsonObject;
	protected JsonObject testData;
	protected String username;
	protected String password;
	protected static String bearerToken;
	protected String brokerUrl;
	protected String topic;

	BaseTest(){
		
		this.jsonObject = JSONUtils.jsonObject();
		testData = TestDataInitializer.loadTestData();
        assert testData != null;
        bearerToken = System.getenv("KONG_API_KEY");

	}

	CreateControlPlaneRequest createControlPlaneRequest(){
		CreateControlPlaneRequest request = new CreateControlPlaneRequest();
		String id = randomId();
		request.setName("control-plane-" + id);
		request.setDescription("New control plane "+id);
		request.setAuth_type("pinned_client_certs");
		request.setCloud_gateway(false);
		request.setCluster_type("CLUSTER_TYPE_SERVERLESS");
		return request;
	}

	ServiceModel createTodoServiceModel()
	{

		ServiceModel model = new ServiceModel();
		model.setName("todoService-"+randomId());
		model.setUrl("https://dummyjson.com/todos");
		model.setRetries(5);
		model.setRead_timeout(60000);
		model.setConnect_timeout(60000);
		return model;

	}

	protected String randomId() {
		StringBuilder builder = new StringBuilder();
		int randomNumber = (int) (Math.random() * 10000) + 1;
		builder.append(randomNumber);
		return builder.toString();
	}

	protected void verifyControlPlane(CreateControlPlaneRequest request, CreateControlPlaneResponse controlPlane){
		assertNotNull(controlPlane.getId());
		assertEquals(request.getName(), controlPlane.getName());
		Config config = controlPlane.getConfig();
		assertEquals(request.getAuth_type(), config.getAuth_type());
		assertEquals(request.getCluster_type(), config.getCluster_type());
		assertEquals(request.isCloud_gateway(),config.isCloud_gateway());
		assertNotNull(config.getControl_plane_endpoint());
		assertNotNull(config.getTelemetry_endpoint());


	}

	RouteRequest createRouteRequest(String serviceId){
		RouteRequest request = new RouteRequest();
		request.setName("route-"+randomId());
		ArrayList<String> protocols = new ArrayList<String>();
		protocols.add("http");
		protocols.add("https");
		request.setProtocols(protocols);
		Service service = new Service();
		service.setId(serviceId);
		request.setService(service);
		ArrayList<String> paths = new ArrayList<String>();
		String path = "/";
		paths.add(path);
		request.setPaths(paths);
		request.setPath_handling("v0");
		request.setRegex_priority(0);
		request.setHttps_redirect_status_code(426);
		return request;
	}

}
