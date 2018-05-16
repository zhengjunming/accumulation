package com.mins.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/15
 * No struggle, talent how to match the willfulness.
 * Description: WebSocketHandler
 */
@Component
@Slf4j
public class MyWebSocketHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        log.info("ConnectionEstablished: " + webSocketSession.getRemoteAddress().toString());
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        System.out.println("收到信息：" + webSocketMessage.getPayload());
        TextMessage response = new TextMessage(webSocketMessage.getPayload() + " received at server");
        webSocketSession.sendMessage(response);
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        log.info("WebSocket connection close");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        log.info("Connection closed..." + webSocketSession.getRemoteAddress().toString());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
