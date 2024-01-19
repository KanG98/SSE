package com.kang98.service.serviceorder.controller.IT;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.service.serviceorder.controller.UpdateOrdersController;
import com.kang98.service.serviceorder.dto.UpdateOrderStatusRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateOrdersControllerIT {

    private UpdateOrdersController updateOrdersController;

    private OrdersRepository ordersRepository;

    @Autowired
    public UpdateOrdersControllerIT(UpdateOrdersController updateOrdersController, OrdersRepository ordersRepository) {
        this.updateOrdersController = updateOrdersController;
        this.ordersRepository = ordersRepository;
    }

    @Test
    public void updateOrderStatus_givenOrderIdAndStatus_expectedSuccess() {
        String orderId = "32049231jf80afd5cf0bdd1e";
        String orderStatus = "fd";
        Order order = ordersRepository.findById(orderId).get();
        UpdateOrderStatusRequest updateOrderStatusRequest = UpdateOrderStatusRequest.builder()
                .orderId(orderId)
                .orderStatus(orderStatus)
                .build();
        boolean acknowledge = updateOrdersController.updateOrderStatus(updateOrderStatusRequest).isDbAcknowledgement();
        assert acknowledge == true;

        // reset order status
        updateOrdersController.updateOrderStatus(
                UpdateOrderStatusRequest.builder()
                        .orderId(orderId)
                        .orderStatus(order.getOrderStatus()).build());
    }
}
