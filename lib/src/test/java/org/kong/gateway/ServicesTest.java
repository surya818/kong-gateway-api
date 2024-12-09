package org.kong.gateway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.kong.edge.framework.http.HttpServiceClient;
import org.kong.edge.framework.models.*;
import org.kong.edge.framework.services.ControlPlaneAPI;
import org.kong.edge.framework.services.RouteAPI;
import org.kong.edge.framework.services.ServiceAPI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ServicesTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(ServicesTest.class);
    private static ServiceAPI serviceAPI;
    private static ControlPlaneAPI controlPlaneService;
    private static RouteAPI routeAPI;
    private static String controlPlaneId;
    private static String serviceId;

    private static String routeId;



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
        controlPlaneId = controlPlane.getId();
        int servicesCount = serviceAPI.listServices(controlPlaneId).getData().size();

        //Create Service
        ServiceModel responseModel = serviceAPI.create(controlPlaneId,createTodoServiceModel());
        ArrayList<ServiceModel> serviceData = serviceAPI.listServices(controlPlaneId).getData();
        serviceId = serviceData.getFirst().getId();

        //Create route
        routeAPI.setBearerToken(bearerToken);
        RouteResponse route = routeAPI.create(controlPlaneId,serviceId,createRouteRequest(serviceId));
        routeId = route.getId();
        assertNotNull(routeId);

        //Get All todos with the Kong proxy path
        HttpResponse<String> response;
        Todos todo = new Todos();
        String completePath = "";
        log.info("Waiting explicitly to make the API calls look human...grrr");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0;i<10;i++){
            //Get Proxy URL


            log.info("Polling for Proxy URL");
            GatewayConfiguration gwConfig = routeAPI.getProxyConfiguration();
            String hostname = gwConfig.getData().getFirst().getDataplane_groups().getFirst().getHostnames().getFirst();
            String path = route.getPaths().getFirst();
            completePath = "https://"+hostname+ path;
            log.info("PROXY URL "+completePath);

            //Polling the proxy api call
            response = new HttpServiceClient().get(completePath,bearerToken);
            todo = jsonObject.fromJson(response.body(), Todos.class);
            if(todo.getTodos() != null){
                break;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        assertNotNull(todo.getTodos(), "Get Todos failed, null todos value");
        assertTrue(todo.getTodos().size() > 1);

        //Get one specific todo with the specific todo item path
        int todoItemId = 1;
        completePath = completePath + "/" + todoItemId;
        response = new HttpServiceClient().get(completePath,bearerToken);
        log.info("Todo item with ID 1: "+response.body());
        Todo todo_item = jsonObject.fromJson(response.body(), Todo.class);
        assertNotNull(todo_item.getTodo());
        assertTrue(todo_item.getId() > 0);


    }


    @AfterAll
    static void tearDown(){
        routeAPI.delete(controlPlaneId, serviceId, routeId);
        serviceAPI.delete(controlPlaneId,serviceId);
        controlPlaneService.deleteControlPlane(controlPlaneId);
        log.info("Cleanup Successful");

    }


}
