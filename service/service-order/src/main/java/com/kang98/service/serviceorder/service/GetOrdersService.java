package com.kang98.service.serviceorder.service;

import com.kang98.data.datadeliveryman.entity.Deliveryman;
import com.kang98.data.datadeliveryman.repository.DeliverymenRepository;
import com.kang98.data.dataorder.entity.Order;
import com.kang98.data.dataorder.entity.OrderStatus;
import com.kang98.data.dataorder.repository.OrdersRepository;
import com.kang98.data.datashipment.entity.Shipment;
import com.kang98.data.datashipment.repository.ShipmentsRepository;
import com.kang98.foundation.helper.Helpers;
import com.kang98.service.serviceorder.dto.GetOrdersResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
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
        return deliverymanList.get(deliverymanList.size() - 1);
    }

    public String getCurrentLocationByDeliverymanId(String deliverymanId) {
        log.info("get Current location by deliverymanId: {}", deliverymanId);
        Optional<Deliveryman> deliveryman = deliverymenRepository.findById(deliverymanId);
        if(!deliveryman.isPresent()) {
            return "no-location-available";
        }
        return deliveryman.get().getCurrentLocation();
    }

    public OrderStatus getOrdersStatusesByOrderId(String orderId) {
        String orderStatus = getOrderStatusByOrderId(orderId);
        String statusDate = Helpers.getCurrentDate();
        String deliverymanId = getDeliverymanIdByOrderId(orderId);
        String currentLocation = getCurrentLocationByDeliverymanId(deliverymanId);

        return OrderStatus.builder()
                .orderId(orderId)
                .orderStatus(orderStatus)
                .statusDate(statusDate)
                .deliverymanId(deliverymanId)
                .currentLocation(currentLocation)
                .build();
    }
}
