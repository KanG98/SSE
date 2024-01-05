package com.kang98.service.serviceshipment.controller.IT;

import com.kang98.service.serviceshipment.controller.GetShipmentsController;
import com.kang98.service.serviceshipment.dto.GetShipmentsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetShipmentControllerIT {

    @Autowired
    private GetShipmentsController getShipmentsController;

    @Test
    void getAllShipments_callMethod_expectedShipmentList() {
        GetShipmentsResponse getShipmentsResponse = getShipmentsController.getAllShipments();
        assert  getShipmentsResponse.getShipmentList().size() > 0;
    }
}
