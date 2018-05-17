package com.mins.sockjs.config;

import com.mins.sockjs.handler.MyHandler;
import com.mins.sockjs.interceptor.WebSocketHandShake;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/16
 * No struggle, talent how to match the willfulness.
 * Description:
 */
@Configuration
@EnableWebSocketMessageBroker
public class MyWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").addInterceptors(new WebSocketHandShake()).setHandshakeHandler(new MyHandler())
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
