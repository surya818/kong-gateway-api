package org.kong.edge.framework.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kong.edge.framework.services.BaseAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.time.Duration;

public class HttpServiceClient implements HttpMethods{
	private static final Logger log = LogManager.getLogger(HttpServiceClient.class);

	private HttpClient client;
	public HttpServiceClient(HttpClient client) {
		this.client = client;
	}
	public HttpServiceClient() {
		this.client = HttpClient.newBuilder()
			      .version(Version.HTTP_2)
			      .followRedirects(Redirect.NORMAL)
			      .build();
	}
	@Override
	public HttpResponse<String> get(String serverAddress, String header, String value) {
		
		
		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create(serverAddress))
		        .timeout(Duration.ofMinutes(2))
		        .header("Content-Type", "application/json")
		        .header("Accept", "application/json")
		        .header(header, value)
		        .GET()
		        .build();
		return executeRequest(request);
		
	}

	@Override
	public HttpResponse<String> post(String serverAddress, String payload, String header, String value) {
		
		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create(serverAddress))
		        .timeout(Duration.ofMinutes(2))
		        .header("Content-Type", "application/json")
		        .header("Accept", "application/json")
		        .header(header, value )
		        .POST(BodyPublishers.ofString(payload))
		        .build();
		
		return executeRequest(request);
	}
	
	public HttpResponse<String> post(String serverAddress, String payload) {
			
			HttpRequest request = HttpRequest.newBuilder()
			        .uri(URI.create(serverAddress))
			        .timeout(Duration.ofMinutes(2))
			        .header("Content-Type", "application/json")
			        .header("Accept", "application/json")
			        .POST(BodyPublishers.ofString(payload))
			        .build();
			
			return executeRequest(request);
		}


	@Override
	public HttpResponse<String> put(String serverAddress, String payload, String bearerToken) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(serverAddress))
				.timeout(Duration.ofMinutes(2))
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.header("Authorization", bearerToken)
				.PUT(BodyPublishers.ofString(payload))
				.build();

		return executeRequest(request);	}

	@Override
	public HttpResponse<String> get(String serverAddress, String bearerToken) {
		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create(serverAddress))
		        .timeout(Duration.ofMinutes(2))
		        .header("Content-Type", "application/json")
		        .header("Accept", "application/json")
		        .header("Authorization", bearerToken)
		        .GET()
		        .build();

		return executeRequest(request);
	}

	@Override
	public HttpResponse<String> delete(String serverAddress, String bearerToken) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(serverAddress))
				.timeout(Duration.ofMinutes(2))
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")
				.header("Authorization", bearerToken)
				.DELETE()
				.build();

		return executeRequest(request);
	}

	@Override
	public HttpResponse<String> post(String serverAddress, String payload, String bearerToken) {

		System.out.println("POST payload: "+payload);

		HttpRequest request = HttpRequest.newBuilder()
		        .uri(URI.create(serverAddress))
		        .timeout(Duration.ofMinutes(2))
		        .header("Content-Type", "application/json")
		        .header("Accept", "application/json")
		        .header("Authorization", bearerToken)
		        .POST(BodyPublishers.ofString(payload))
		        .build();
		
		return executeRequest(request);
	}
	
	private HttpResponse<String> executeRequest(HttpRequest request) {
		
			HttpResponse<String> response = null;
			log.info("Executing API method :"+ request.method() + " on url:  "+request.uri());
			if(request.method().equals("POST") || request.method().equals("PUT")){
				log.info("Request Payload: "+request.bodyPublisher().get().toString());
			}
			try {
				response = client.send(request, HttpResponse.BodyHandlers.ofString());
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("Response: \n" + response.body());

			return response;
			
		}
	
	}

	
	

	

	

