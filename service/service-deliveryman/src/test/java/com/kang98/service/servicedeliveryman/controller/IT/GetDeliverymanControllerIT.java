package com.kang98.service.servicedeliveryman.controller.IT;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.service.servicedeliveryman.controller.GetDeliverymenController;
import com.kang98.service.servicedeliveryman.dto.GetDeliverymanByPhoneNumberRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GetDeliverymanControllerIT {

    @Autowired
    private GetDeliverymenController getDeliverymenController;

    @Test
    void getAllDeliverymen_callMethod_expectedDeliverymenList() {
        List<Deliveryman> deliverymanList = getDeliverymenController.getAllDeliveryman();
        assert deliverymanList.size() > 0;
    }

    @Test
    void getDeliverymanByPhoneNumber_callMethod_expectedDeliverymenList() {
        GetDeliverymanByPhoneNumberRequest getDeliverymanByPhoneNumberRequest =
                GetDeliverymanByPhoneNumberRequest.builder().phoneNumber("+1234567890").build();
        List<Deliveryman> deliverymanList = getDeliverymenController.getDeliverymanByPhoneNumber(getDeliverymanByPhoneNumberRequest);
        assert deliverymanList.size() > 0;
    }

    @Test
    void getDeliverymanByPhoneNumber_callMethod_expectedEmtpyDeliverymenList() {
        GetDeliverymanByPhoneNumberRequest getDeliverymanByPhoneNumberRequest =
                GetDeliverymanByPhoneNumberRequest.builder().phoneNumber("Not in db").build();
        List<Deliveryman> deliverymanList = getDeliverymenController.getDeliverymanByPhoneNumber(getDeliverymanByPhoneNumberRequest);
        assert deliverymanList.size() == 0;
    }
}
