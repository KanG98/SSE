package com.kang98.service.serviceorder.service;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.data.datadeliveryman.repository.DeliverymenRepository;
import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.entity.OrderStatusDetail;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.data.datashipment.repository.ShipmentsRepository;
import com.kang98.foundation.helper.Helpers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GetOrdersService {

    private final OrdersRepository ordersRepository;

    private final ShipmentsRepository shipmentsRepository;

    private final DeliverymenRepository deliverymenRepository;

    @Autowired
    GetOrdersService(OrdersRepository ordersRepository, ShipmentsRepository shipmentsRepository, DeliverymenRepository deliverymenRepository) {
        this.ordersRepository = ordersRepository;
        this.shipmentsRepository = shipmentsRepository;
        this.deliverymenRepository = deliverymenRepository;
    }

    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }

    public String getOrderStatusByOrderId(String orderId) {
        Optional<Order> order = ordersRepository.findById(orderId);
        if (!order.isPresent()) {
            return "no-such-order";
        }
        return order.get().getOrderStatus();
    }

    public String getDeliverymanIdByOrderId(String orderId) {
        Optional<Shipment> shipment = shipmentsRepository.findByOrderId(orderId);
        if (!shipment.isPresent()) {
            return "no-such-shipment";
        }
        List<String> deliverymanList = shipment.get().getDeliverymanId();
        // return the most recent assigned deliveryman in the list
        if(deliverymanList == null || deliverymanList.size() == 0) {
            return "no-deliveryman-assigned";
        }
        return deliverymanList.get(deliverymanList.size() - 1);
    }

    public String getCurrentLocationByDeliverymanId(String deliverymanId) {
        log.info("get Current location by deliverymanId: {}", deliverymanId);
        var deliveryman = deliverymenRepository.findById(deliverymanId);
        if(!deliveryman.isPresent()) {
            return "no-location-available";
        }
        return deliveryman.get().getCurrentLocation();
    }

    public OrderStatusDetail getOrdersStatusDetailsByOrderId(String orderId) {
        String orderStatus = getOrderStatusByOrderId(orderId);
        String statusDate = Helpers.getCurrentISODate();
        String deliverymanId = getDeliverymanIdByOrderId(orderId);
        String currentLocation = getCurrentLocationByDeliverymanId(deliverymanId);

        return OrderStatusDetail.builder()
                .orderId(orderId)
                .orderStatus(orderStatus)
                .statusDate(statusDate)
                .deliverymanId(deliverymanId)
                .currentLocation(currentLocation)
                .build();
    }
}
