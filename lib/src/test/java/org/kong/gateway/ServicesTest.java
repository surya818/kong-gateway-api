package org.kong.gateway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kong.edge.framework.http.HttpServiceClient;
import org.kong.edge.framework.models.*;
import org.kong.edge.framework.services.ControlPlaneAPI;
import org.kong.edge.framework.services.RouteAPI;
import org.kong.edge.framework.services.ServiceAPI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ServicesTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(ServicesTest.class);
    private static ServiceAPI serviceAPI;
    private static ControlPlaneAPI controlPlaneService;
    private static RouteAPI routeAPI;


    @BeforeAll
    static void setUp(){
        controlPlaneService = new ControlPlaneAPI();
        serviceAPI = new ServiceAPI();
        routeAPI = new RouteAPI();

    }

    @Test
    void CreateAndDeleteService_HappyPath() {
        controlPlaneService.setBearerToken(bearerToken);
        serviceAPI.setBearerToken(bearerToken);
        CreateControlPlaneResponse controlPlane = controlPlaneService.create(createControlPlaneRequest());
        String controlPlaneId = controlPlane.getId();
        int servicesCount = serviceAPI.listServices(controlPlaneId).getData().size();
        ServiceModel responseModel = serviceAPI.create(controlPlaneId,createTodoServiceModel());
        ArrayList<ServiceModel> serviceData = serviceAPI.listServices(controlPlaneId).getData();
        int latestServicesCount = serviceData.size();
        assertEquals(servicesCount+1, latestServicesCount);
        serviceAPI.delete(controlPlaneId,serviceData.getFirst().getId());
        serviceData = serviceAPI.listServices(controlPlaneId).getData();
        latestServicesCount = serviceData.size();
        assertEquals(servicesCount, latestServicesCount);
        //cleanup
        controlPlaneService.deleteControlPlane(controlPlaneId);

    }

    @Test
    void CreateGatewayRouteAndVerifyNewRoute() {

        //Create control plane
        controlPlaneService.setBearerToken(bearerToken);
        serviceAPI.setBearerToken(bearerToken);
        CreateControlPlaneResponse controlPlane = controlPlaneService.create(createControlPlaneRequest());
        String controlPlaneId = controlPlane.getId();
        int servicesCount = serviceAPI.listServices(controlPlaneId).getData().size();

        //Create Service
        ServiceModel responseModel = serviceAPI.create(controlPlaneId,createTodoServiceModel());
        ArrayList<ServiceModel> serviceData = serviceAPI.listServices(controlPlaneId).getData();
        String serviceId = serviceData.getFirst().getId();

        //Create route
        routeAPI.setBearerToken(bearerToken);
        RouteResponse route = routeAPI.create(controlPlaneId,serviceId,createRouteRequest(serviceId));
        String routeId = route.getId();
        assertNotNull(routeId);

        //Get Proxy URL

        GatewayConfiguration gwConfig = routeAPI.getProxyConfiguration();
        String hostname = gwConfig.getData().getFirst().getDataplane_groups().getFirst().getHostnames().getFirst();
        String path = route.getPaths().getFirst();
        String completePath = "https://"+hostname+ path;
        log.info("PROXY URL "+completePath);

        //Get All todos with the Kong proxy path
        var response = new HttpServiceClient().get(completePath,bearerToken);
        Todos todo = jsonObject.fromJson(response.body(), Todos.class);
        assertTrue(todo.getTodos().size() > 1);

        //Get one specific todo with the specific todo item path
        int todoItemId = 1;
        completePath = completePath + "/" + todoItemId;
        response = new HttpServiceClient().get(completePath,bearerToken);
        log.info("Todo item with ID 1: "+response.body());
        Todo todo_item = jsonObject.fromJson(response.body(), Todo.class);
        assertNotNull(todo_item.getTodo());
        assertTrue(todo_item.getId() > 0);

        //cleanup
        routeAPI.delete(controlPlaneId, serviceId, routeId);
        serviceAPI.delete(controlPlaneId,serviceId);
        controlPlaneService.deleteControlPlane(controlPlaneId);








    }





}
