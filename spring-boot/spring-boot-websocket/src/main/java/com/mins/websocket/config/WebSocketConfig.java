package com.mins.websocket.config;

import com.mins.websocket.handler.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/15
 * No struggle, talent how to match the willfulness.
 * Description: WebSocket配置类
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MyWebSocketHandler handler;

    @Autowired
    public WebSocketConfig(MyWebSocketHandler handler) {
        this.handler = handler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(handler, "/ws").addInterceptors(new HandShake()).setAllowedOrigins("*");
        webSocketHandlerRegistry.addHandler(handler, "/sockjs/ws").addInterceptors(new HandShake()).withSockJS();
    }
}
