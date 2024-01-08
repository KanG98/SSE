package com.kang98.data.dataorder.repository;

import com.kang98.data.dataorder.entity.Order;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.bulk.UpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderRepositoryImpl {
    @Autowired
    MongoTemplate mongoTemplate;

    public boolean updateOrderStatus(String orderId, String orderStatus) {
        log.info("updating order status for {} to {}", orderId, orderStatus);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(orderId));

        Update update = new Update();
        update.set("order_status", orderStatus);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Order.class);
        log.info("updated order status for {} to {} {}", orderId, orderStatus, updateResult.wasAcknowledged() == true ? "successfully" : "unsuccessfully");
        return updateResult.wasAcknowledged();
    }
}