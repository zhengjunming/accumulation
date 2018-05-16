package com.mins.sockjs.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeFailureException;
import org.springframework.web.socket.server.HandshakeHandler;

import java.util.Map;

/**
 * @author <a href="http://minsming.com">小铭</a>
 * Date: 2018/4/16
 * No struggle, talent how to match the willfulness.
 * Description:
 */
@Component
@Slf4j
public class MyHandler implements HandshakeHandler {

    @Override
    public boolean doHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Map<String, Object> map) throws HandshakeFailureException {
        log.info("处理信息");
        return false;
    }
}
