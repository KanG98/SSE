package com.kang98.service.servicedeliveryman.service.IT;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.service.servicedeliveryman.service.GetDeliverymanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GetDeliverymanServiceIT {

    @Autowired
    private GetDeliverymanService getDeliverymanService;

    @Test
    void getAllDeliverymen_callMethod_expectedDeliverymenList() {
        List<Deliveryman> deliverymanList = getDeliverymanService.getAllDeliverymen();
        assert deliverymanList.size() > 0;
    }

    @Test
    void getDeliverymanByPhoneNumber_callMethod_expectedDeliverymenList() {
        List<Deliveryman> deliverymanList = getDeliverymanService.getAllDeliverymanByPhoneNumber("+1234567890");
        assert deliverymanList.size() > 0;
    }

    @Test
    void getDeliverymanByPhoneNumber_callMethod_expectedEmptyDeliverymenList() {
        List<Deliveryman> deliverymanList = getDeliverymanService.getAllDeliverymanByPhoneNumber("not in db");
        assert deliverymanList.size() == 0;
    }
}
