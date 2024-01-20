package com.kang98.service.servicenotificationemail.service.kafkaconsumer;

import com.kang98.data.dataorder.entity.Order;
import com.kang98.service.servicenotificationemail.client.MailgunEmailClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailConsumer {

    @Value("${mailgun.api-key}")
    private String apiSecret;

    private MailgunEmailClient emailClient;

    @Autowired
    public EmailConsumer(MailgunEmailClient emailClient) {
        this.emailClient = emailClient;
    }

    @KafkaListener(topics = "sse-create-orders-service", groupId = "send-new-order-email")
    public void consumeEvents(Order order) {
        log.info("Sending email for {} placed at {} ", order.getId(), order.getOrderDate());
        emailClient.sendEmail(apiSecret, order.getId());
        log.info("Email sent for {} placed at {} ", order.getId(), order.getOrderDate());
    }
}
