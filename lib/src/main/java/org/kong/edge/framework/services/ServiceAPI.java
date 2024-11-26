package org.kong.edge.framework.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kong.edge.framework.models.ListServices;
import org.kong.edge.framework.models.ServiceModel;

import java.net.http.HttpResponse;

public class ServiceAPI extends BaseAPI {
    private String bearerToken;
    private static final Logger log = LogManager.getLogger(ServiceAPI.class);


    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }


        public ServiceAPI() {
            super();
        }
        public ServiceAPI(String bearerToken) {
            super();
            this.bearerToken = bearerToken;
        }

        public ServiceModel create(String controlPlaneId, ServiceModel request) {
            String path = "/v2/control-planes/"+controlPlaneId+"/core-entities/services";
            String url = baseUrl + path;
            String payload = jsonObject.toJson(request, ServiceModel.class);
            HttpResponse<String> response = client.post(url, payload, this.bearerToken);
            assert (response != null) : "Response is null";
            assert (response.statusCode() == 201 ): "Response Status code is not 201 OK, it's "+response.statusCode();
            ServiceModel controlPlane = jsonObject.fromJson(response.body(), ServiceModel.class);
            log.info("Service created: ");
            log.info(response.body());
            return controlPlane;
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

    public void delete(String controlPlaneId, String serviceId) {
        String path = "/v2/control-planes/"+controlPlaneId+"/core-entities/services/"+serviceId;
        String url = baseUrl + path;
        HttpResponse<String> response = client.delete(url, this.bearerToken);
        log.info("Service deleted with ID: "+serviceId);
        assert (response != null) : "Response is null";
        log.info("Delete response code: "+response.statusCode());
        assert (response.statusCode() == 204 ): "Response Status code is not 204, it's "+response.statusCode();


    }


}
