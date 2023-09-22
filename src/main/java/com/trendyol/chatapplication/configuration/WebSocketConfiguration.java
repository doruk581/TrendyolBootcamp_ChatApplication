package com.trendyol.chatapplication.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/*
*
* Author: Doruk S. 20.09.2023
* Contact: doruk.su@trendyol.com
*/

/*
* Bu WebSocketConfiguration sınıfı, Spring Boot uygulamanızda WebSocket ve STOMP protokolü üzerinden mesajlaşmayı konfigüre eder.
* Sınıf, WebSocketMessageBrokerConfigurer interface'ini implemente eder, bu sayede belirli metodlar üzeride WebSocket ve STOMP ayarları yapılır.
*
* @EnableWebSocketMessageBroker: WebSocket mesaj broker'ını etkinleştirir. Bu sayede STOMP mesajlarını WebSocket üzerinden gönderebilir ve alabilirsiniz.
*
* registerStompEndpoints(StompEndpointRegistry registry) : Bu metod, WebSocket endpoint'ini /ws olarak tanımlar ve SockJS desteği ekler.

* registry.addEndpoint("/ws").withSockJS();: WebSocket endpoint olarak /ws'i belirtir. withSockJS() metodu ile SockJS desteği eklenir.
*  SockJS, WebSocket özelliğini desteklemeyen tarayıcılarda yedek mekanizmalar sunar.

*configureMessageBroker(MessageBrokerRegistry registry): Bu metod, mesaj broker ayarlarını yapar.

*registry.setApplicationDestinationPrefixes("/app");: Uygulamanın kullandığı destinasyon önekini belirtir. Client'lar (örn. tarayıcılar) bu prefix ile mesaj gönderirler.

*registry.enableSimpleBroker("/topic");: Simple broker özelliklerini etkinleştirir ve /topic prefix'i ile başlayan destinasyonlara abonelik ve mesaj gönderme izni verir.
*
*
*
*   Client bir WebSocket bağlantısı kurmak istediğinde, /ws endpoint'ine bir istek yapar.
    Bağlantı kurulduğunda, client /app prefix'li bir adrese mesaj gönderebilir.
    Mesaj broker bu mesajı işler ve ilgili /topic'e abone olan tüm client'lara mesajı gönderir.
* */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
