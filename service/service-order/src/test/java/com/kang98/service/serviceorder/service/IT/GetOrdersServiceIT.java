package com.kang98.service.serviceorder.service.IT;

import com.kang98.service.serviceorder.service.GetOrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetOrdersServiceIT {

    @Autowired
    private GetOrdersService getOrdersService;

    @Test
    void getAllOrders_callMethod_expectedOrdersList() {
        assert getOrdersService.getAllOrders().size() > 0;
    }

}
