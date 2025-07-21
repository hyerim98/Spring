package com.example.springbasic.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

// 이 빈은 HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸된다

//@Scope(value = "request")

/* 프록시 : 위 문제 해결방안2
    * proxyMode : 적용 대상이 인터페이스가 아닌 클래스면 TARGET_CLASS, 적용 대상이 인터페이스면 INTERFACES
    * 이렇게 하면 MyLogger의 가짜 프록시 클래스를 만들어두고 HTTP request와 상관 없이 가짜 프록시 클래스를
      다른 빈에 미리 주입해 둘 수 있다.
*/
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.printf("[%s][%s] %s\n", uuid, requestURL, message);
    }

    // 서버에 들어올 때 호출되는 메서드
    // 이 빈은 HTTP 요청 당 하나씩 생성되므로, uuid를 저장해두면 다른 HTTP 요청과 구분할 수 있다.
    @PostConstruct
    public void init() {
        // 절대 안겹치도록 uuid를 생성하는 방법
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] " + "request scope bean create : " + this);
    }

    // 서버에서 빠져나갈 때 호출되는 메서드
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] " + "request scope bean close : " + this);
    }
}
