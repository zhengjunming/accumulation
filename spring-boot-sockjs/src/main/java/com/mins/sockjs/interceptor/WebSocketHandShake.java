package com.mins.sockjs.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/16
 * No struggle, talent how to match the willfulness.
 * Description:
 */
@Component
@Slf4j
public class WebSocketHandShake implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        log.info("握手之前: " + serverHttpRequest.getRemoteAddress().toString());
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info("握手之后: " + serverHttpRequest.getRemoteAddress().toString());
    }
}
