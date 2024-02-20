package com.kang98.service.serviceorder.service.IT;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.service.serviceorder.service.UpdateOrdersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UpdateOrdersServiceIT {

    private UpdateOrdersService updateOrdersService;

    private OrdersRepository ordersRepository;


    @Autowired
    public UpdateOrdersServiceIT(UpdateOrdersService updateOrdersService,
                                 OrdersRepository ordersRepository) {
        this.updateOrdersService = updateOrdersService;
        this.ordersRepository = ordersRepository;
    }

    @Test
    void updateOrderStatus_givenOrderIdAndStatus_expectedSuccess() {
        String orderId = "32049231jf80afd5cf0bdd1e";
        Order order = ordersRepository.findById(orderId).get();
        boolean acknowledge = updateOrdersService.updateOrderStatus(orderId, "test");
        assertEquals(true, acknowledge);
        updateOrdersService.updateOrderStatus(orderId, order.getOrderStatus());
    }
}
