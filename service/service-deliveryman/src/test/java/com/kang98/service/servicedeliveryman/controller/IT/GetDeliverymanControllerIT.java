package com.kang98.service.servicedeliveryman.controller.IT;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.service.servicedeliveryman.controller.GetDeliverymenController;
import com.kang98.service.servicedeliveryman.service.GetDeliverymanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GetDeliverymanControllerIT {

    @Autowired
    private GetDeliverymenController getDeliverymenController;

    @Test
    void getAllDeliveryman_callMethod_expectedDeliverymenList() {
        List<Deliveryman> deliverymanList = getDeliverymenController.getAllDeliveryman();
        assert deliverymanList.size() > 0;
    }
}
