package com.kang98.foundation.kafka;

import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CompletableFuture;

public class Producer {
    public void sendEventsToTopic(KafkaTemplate<String, Object> template, String topic, Object event) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send(topic, event);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + event.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            event.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("ERROR : "+ ex.getMessage());
        }
    }
}
