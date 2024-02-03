package com.example.servlet.web.springmvc.old;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import java.io.IOException;

// 서블릿과 가장 유사한 형태의 핸들러
/*
* MyHttpRequestHandler 핸들러 매핑, 어댑터
    * HandlerMapping = BeanNameUrlHandlerMapping
    * HandlerAdapter = HttpRequestHandlerAdapter
*/
@Component("/springmvc/request-handler") // 빈의 이름으로 URL을 매핑
public class MyHttpRequestHandler implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
