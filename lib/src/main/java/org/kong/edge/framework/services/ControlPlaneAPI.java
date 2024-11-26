package org.kong.edge.framework.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kong.edge.framework.models.CreateControlPlaneRequest;
import org.kong.edge.framework.models.CreateControlPlaneResponse;
import org.kong.edge.framework.models.GetControlPlanesResponse;

import java.net.http.HttpResponse;

public class ControlPlaneAPI extends BaseAPI {
    private String bearerToken;
    private static final Logger log = LogManager.getLogger(ControlPlaneAPI.class);


    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public ControlPlaneAPI(String bearerToken) {
        super();
        this.bearerToken = bearerToken;
    }
        public ControlPlaneAPI() {
            super();
        }

        public CreateControlPlaneResponse create(CreateControlPlaneRequest request) {
            String path = "/v2/control-planes";
            String url = baseUrl + path;
            String payload = jsonObject.toJson(request, CreateControlPlaneRequest.class);
            HttpResponse<String> response = client.post(url, payload, this.bearerToken);
            assert (response != null) : "Response is null";
            assert (response.statusCode() == 201 ): "Response Status code is not 201 OK";
            CreateControlPlaneResponse controlPlane = jsonObject.fromJson(response.body(), CreateControlPlaneResponse.class);
            log.info("Control plane created: ");
            log.info(response.body());
            return controlPlane;
        }

    public GetControlPlanesResponse get() {
        String path = "/v2/control-planes";
        String url = baseUrl + path;
        HttpResponse<String> response = client.get(url, "Bearer "+bearerToken);
        assert (response != null) : "Response is null";
        assert (response.statusCode() == 200 ): "Response Status code is not 200 OK";
        log.info("Successful GET Control planes");
        log.info(response.body());
        return jsonObject.fromJson(response.body(), GetControlPlanesResponse.class);

    }

    public GetControlPlanesResponse getControlPlane(String id) {
        String path = "/v2/control-planes/"+ id;
        String url = baseUrl + path;
        HttpResponse<String> response = client.get(url, "Bearer "+bearerToken);
        assert (response != null) : "Response is null";
        assert (response.statusCode() == 200 ): "Response Status code is not 200 OK";
        log.info("Successful GET Control planes with ID "+id);
        log.info(response.body());
        return jsonObject.fromJson(response.body(), GetControlPlanesResponse.class);

    }

    public void deleteControlPlane(String id) {
        String path = "/v2/control-planes/"+ id;
        String url = baseUrl + path;
        HttpResponse<String> response = client.delete(url, this.bearerToken);
        log.info("Control plane deleted with ID: "+id);
        assert (response != null) : "Response is null";
        log.info("Delete response code: "+response.statusCode());
        assert (response.statusCode() == 204 ): "Response Status code is not 204";


    }




}
