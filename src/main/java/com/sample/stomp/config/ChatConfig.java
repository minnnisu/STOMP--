package com.sample.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Stomp를 사용하기위해 선언하는 어노테이션
public class ChatConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /**
         *  prefix로 /topic과 /queue을 가지는 경로에 메시지를 송신함
         *  /topic (Broadcast)
         *    - 구독자 패턴에 대한 토픽 매칭을 수행하며, 메시지를 여러 구독자에게 라우팅
         *  /queue (Unicast)
         *    - 큐 목적지는 각 메시지를 최대 한 명의 구독자에게 전달하며, 구독자가 없으면 구독자가 큐에 연결할 때까지 큐에 대기한다
         */
        registry.enableSimpleBroker("/queue", "/topic");

        /**
         * 클라이언트 -> 서버 메시지 전송
         *          @MessageMapping에서 처리 받음
         */
        registry.setApplicationDestinationPrefixes("/app");
    }
}
