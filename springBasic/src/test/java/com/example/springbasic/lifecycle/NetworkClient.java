package com.example.springbasic.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Setter;


@Setter
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        // 객체 생성 -> 의존관계 주입 순서로 진행되므로 생성자에서는 의미 없는 로직이다
        /*
        connect();
        call("초기화 연결 메시지");
        */
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    // 의존관계 주입이 끝나면 호출되는 메서드
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init()");
        connect();
        call("초기화 연결 메시지");
    }

    // 빈이 종료될 때 호출되는 메서드
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close()");
        disconnect();
    }
}
