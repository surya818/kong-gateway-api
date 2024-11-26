package org.kong.gateway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kong.edge.framework.models.CreateControlPlaneRequest;
import org.kong.edge.framework.models.CreateControlPlaneResponse;
import org.kong.edge.framework.services.ControlPlaneAPI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControlPlaneTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(ControlPlaneTest.class);
    private static ControlPlaneAPI controlPlaneService = new ControlPlaneAPI();


    @BeforeAll
    static void setUp(){
        controlPlaneService = new ControlPlaneAPI();

    }

    @Test
    void CreateControlPlane_HappyPath() {
        controlPlaneService.setBearerToken(bearerToken);
        int totalNumberOfControlPlanes = controlPlaneService.get().getData().size();
       CreateControlPlaneRequest request = createControlPlaneRequest();
       CreateControlPlaneResponse controlPlane = controlPlaneService.create(request);
       verifyControlPlane(request,controlPlane);
       assertEquals(controlPlaneService.get().getData().size(), totalNumberOfControlPlanes + 1);
       String controlPlaneId = controlPlane.getId();
       controlPlaneService.deleteControlPlane(controlPlaneId);
    }

    @Test
    void Delete_ControlPlane() {
        controlPlaneService.setBearerToken(bearerToken);

        int totalNumberOfControlPlanes = controlPlaneService.get().getData().size();
        CreateControlPlaneRequest request = createControlPlaneRequest();
        CreateControlPlaneResponse controlPlane = controlPlaneService.create(request);
        assertEquals(controlPlaneService.get().getData().size(), totalNumberOfControlPlanes + 1);
        String controlPlaneId = controlPlane.getId();
        controlPlaneService.deleteControlPlane(controlPlaneId);
        assertEquals(controlPlaneService.get().getData().size(), totalNumberOfControlPlanes);

    }





}
