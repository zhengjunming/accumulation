package com.mins.sockjs.controller;

import com.mins.sockjs.model.Greeting;
import com.mins.sockjs.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.util.HtmlUtils;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/15
 * No struggle, talent how to match the willfulness.
 * Description:
 */
@Controller
@CrossOrigin
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        System.out.println(message.getName());
        // simulated delay
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
