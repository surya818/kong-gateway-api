package org.kong.edge.framework.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kong.edge.framework.models.*;

import java.net.http.HttpResponse;

public class RouteAPI extends BaseAPI {
    private String bearerToken;
    private static final Logger log = LogManager.getLogger(RouteAPI.class);


    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }


        public RouteAPI() {
            super();
        }
        public RouteAPI(String bearerToken) {
            super();
            this.bearerToken = bearerToken;
        }

        public RouteResponse create(String controlPlaneId, String serviceId, RouteRequest request) {
            String path = "/v2/control-planes/"+controlPlaneId+"/core-entities/services/"+serviceId+"/routes";
            String url = baseUrl + path;
            String payload = jsonObject.toJson(request, RouteRequest.class);
            HttpResponse<String> response = client.post(url, payload, this.bearerToken);
            assert (response != null) : "Response is null";
            assert (response.statusCode() == 201 ): "Response Status code is not 201 OK, it's "+response.statusCode();
            RouteResponse route = jsonObject.fromJson(response.body(), RouteResponse.class);
            log.info("Route created: ");
            log.info(response.body());
            return route;
        }

    public ListServices listServices(String controlPlaneId) {
        String path = "/v2/control-planes/"+controlPlaneId+"/core-entities/services";
        String url = baseUrl + path;
        HttpResponse<String> response = client.get(url, "Bearer "+bearerToken);
        assert (response != null) : "Response is null";
        assert (response.statusCode() == 200 ): "Response Status code is not 200 OK, it's "+response.statusCode();
        log.info("Successful List Services");
        log.info(response.body());
        return jsonObject.fromJson(response.body(), ListServices.class);

    }

    public ServiceModel getService(String controlPlaneId, String serviceId) {
        String path = "/v2/control-planes/"+controlPlaneId+"/core-entities/services/"+serviceId;
        String url = baseUrl + path;
        HttpResponse<String> response = client.get(url, "Bearer "+bearerToken);
        assert (response != null) : "Response is null";
        assert (response.statusCode() == 200 ): "Response Status code is not 200 OK, it's "+response.statusCode();
        log.info("Successful GET Service with ID "+serviceId);
        log.info(response.body());
        return jsonObject.fromJson(response.body(), ServiceModel.class);

    }

    public void delete(String controlPlaneId, String serviceId, String routeId) {
        String path = "/v2/control-planes/"+controlPlaneId+"/core-entities/services/"+serviceId+"/routes/"+routeId;
        String url = baseUrl + path;
        HttpResponse<String> response = client.delete(url, this.bearerToken);
        log.info("Service deleted with ID: "+serviceId);
        assert (response != null) : "Response is null";
        log.info("Delete response code: "+response.statusCode());
        assert (response.statusCode() == 204 ): "Response Status code is not 204, it's "+response.statusCode();


    }

    public GatewayConfiguration getProxyConfiguration(){
        String path = "/v3/cloud-gateways/configurations";
        String url = baseUrl_global + path;
        log.info("Global Gateway URL with route: "+url);
        HttpResponse<String> response = client.get(url, this.bearerToken);
        assert (response != null) : "Response is null";
        assert (response.statusCode() == 200 ): "Response Status code is not 200 OK, it's "+response.statusCode();
        log.info("Successful GET Config ");
        log.info(response.body());
        return jsonObject.fromJson(response.body(), GatewayConfiguration.class);


    }


}
