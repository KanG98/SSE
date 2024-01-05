package com.kang98.service.serviceshipment.service.IT;

import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.service.serviceshipment.service.GetShipmentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GetShipmentServiceIT {

    @Autowired
    private GetShipmentsService getShipmentsService;

    @Test
    void getAllDeliverymen_callMethod_expectedDeliverymenList() {
        List<Shipment> shipmentList = getShipmentsService.getAllShipments();
        assert shipmentList.size() > 0;
    }
}