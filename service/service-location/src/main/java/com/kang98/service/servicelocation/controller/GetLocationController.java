package com.kang98.service.servicelocation.controller;

import com.kang98.service.servicelocation.entity.Greeting;
import com.kang98.service.servicelocation.entity.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GetLocationController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        return new Greeting("Bye, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}