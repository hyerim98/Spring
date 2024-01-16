package com.example.springbasic.web;

import com.example.springbasic.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    // myLogger는 웹 스코프(HTTP 요청 하나가 들어오고 나갈 때 까지 유지되는 스코프) 이므로 의존 관계 주입 시 스프링 컨테이너가 의존관계 주입 할게 없음
    // -> 스프링 애플리케이션을 실행하는 시점에 싱글톤 빈은 생성해서 주입이 가능하지만, request 스코프 빈은 아직 생성되지 않는다. 이 빈은 실제 고객의 요청이 와야 생성할 수 있다(MyLogger class에 'proxyMode = ScopedProxyMode.TARGET_CLASS'가 없을 경우)
    private final MyLogger myLogger;

    // ObjectProvider : 위 문제 해결방안1
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
        myLogger.log("service id = " + id);
    }
}
