package com.kang98.service.servicedeliveryman.service.IT;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.data.datadeliveryman.repository.DeliverymenRepository;
import com.kang98.service.servicedeliveryman.service.CreateDeliverymenService;
import com.kang98.service.servicedeliveryman.service.GetDeliverymanService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class CreateDeliverymanServiceIT {

    @Autowired
    private CreateDeliverymenService createDeliverymenService;

    @Autowired
    private GetDeliverymanService getDeliverymanService;

    @Autowired
    private DeliverymenRepository deliverymenRepository;

    @Test
    void createDeliverymen_callMethod_expectedSuccess() {
        Deliveryman deliveryman = createDeliverymenService.createDeliveryman(Deliveryman.builder().email("IT").build());
        assert getDeliverymanService.getAllDeliverymen().stream().anyMatch(dm -> dm.getEmail().equals("IT")) == true;
        deliverymenRepository.delete(deliveryman);
    }

}
