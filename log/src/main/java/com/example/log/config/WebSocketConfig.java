package com.example.log.config;

import com.example.log.common.queue.LogQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("*")
                .withSockJS();
    }


    @PostConstruct
    private void sendMessage() {
        new Thread(() -> {
            while (true) {
                Object o =  LogQueue.getLogQueue().poll();
//                System.out.println(o.toString());
                simpMessagingTemplate.convertAndSend("/topic/log", o);
            }
        }).start();
    }
}
