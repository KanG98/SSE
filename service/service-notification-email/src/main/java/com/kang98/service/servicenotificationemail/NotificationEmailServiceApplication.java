package com.kang98.service.servicenotificationemail;

import com.kang98.service.servicenotificationemail.client.MailgunEmailClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationEmailServiceApplication {
    /**
     * This call sends an email to one recipient.
     */
    public static void main(String[] args) {

        SpringApplication.run(NotificationEmailServiceApplication.class, args);
    }
}
