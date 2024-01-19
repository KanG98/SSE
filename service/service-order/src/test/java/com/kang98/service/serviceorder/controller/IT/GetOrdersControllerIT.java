package com.kang98.service.serviceorder.controller.IT;

import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.service.serviceorder.controller.GetOrdersController;
import com.kang98.service.serviceorder.dto.GetOrdersStatusesRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetOrdersControllerIT {

    @Autowired
    private GetOrdersController getOrdersController;

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void getAllOrders_callMethod_expectedOrdersList() {
        assert getOrdersController.getAllOrders().getOrders().size() > 0;
    }

    @Test
    void getOrderStatusDetails_callMethod_expectedOrderStatusDetails() {
        GetOrdersStatusesRequest getOrdersStatusesRequest = GetOrdersStatusesRequest.builder().orderId("1").build();
        assert getOrdersController.getOrderStatusesByOrderId(getOrdersStatusesRequest).getOrderStatus() != null;
    }
}