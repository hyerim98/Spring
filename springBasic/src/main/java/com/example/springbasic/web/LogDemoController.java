package com.example.springbasic.web;

import com.example.springbasic.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    // myLogger는 웹 스코프(HTTP 요청 하나가 들어오고 나갈 때 까지 유지되는 스코프) 이므로 의존 관계 주입 시 스프링 컨테이너가 의존관계 주입 할게 없음
    // -> 스프링 애플리케이션을 실행하는 시점에 싱글톤 빈은 생성해서 주입이 가능하지만, request 스코프 빈은 아직 생성되지 않는다. 이 빈은 실제 고객의 요청이 와야 생성할 수 있다(MyLogger class에 'proxyMode = ScopedProxyMode.TARGET_CLASS'가 없을 경우)
    private final MyLogger myLogger;

    // ObjectProvider : 위 문제 해결방안1(request scope 빈의 생성을 지연)
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    @ResponseBody // 뷰 화면 없이 바로 문자로 반환 하고 싶을 때 사용(이 문자를 그대로 응답으로 보낼 때 사용)
    public String logDemo(HttpServletRequest request) { // HttpServletRequest : Http Request 요청 정보를 받을 수 있다
        // getRequestURI() : 고객이 어떤 url로 요청했는지 알 수 있다
        String requestURL = request.getRequestURI().toString();

        // myLogger 최초 생성 시점
        // getObject()를 호출하는 시점에는 HTTP 요청이 진행중이므로 request scope 빈의 생성이 정상 처리
        //MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger = " + myLogger.getClass());
        //  requestURL을 MyLogger에 저장하는 부분은 컨트롤러 보다는 공통 처리가 가능한 스프링 인터셉터나 서블릿 필터 같은 곳을 활용하는 것이 좋다
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
