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

    private static Date date = new Date();

    @BeforeAll
    static void setUp() {
        date.setTime(System.currentTimeMillis());
    }

    @Test
    void createDeliverymen_callMethod_expectedSuccess() {
        Deliveryman deliveryman = createDeliverymenService.createDeliveryman(Deliveryman.builder().sinceDate(date).build());
        assert getDeliverymanService.getAllDeliverymen().stream().anyMatch(dm -> dm.getSinceDate().equals(date)) == true;
        deliverymenRepository.delete(deliveryman);
    }

}
