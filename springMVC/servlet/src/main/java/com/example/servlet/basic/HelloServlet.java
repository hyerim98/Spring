package com.example.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// name : 서블릿 이름, urlPatterns : URL 매핑
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // URL(서블릿) 호출되면 service() 메서드 실행
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 웹 브라우저에 응답하는 response-http 응답 메세지에 해당 데이터가 담겨져 간다(개발자 도구에서 확인 F12)
        response.setContentType("text/plain"); // 단순 문자(헤더에 들어가는 정보)
        response.setCharacterEncoding("utf-8");// 문자 인코딩 정보(헤더에 들어가는 정보)
        response.getWriter().write("hello " + username); // HTTP 메세지 바디에 들어가는 데이터(바디에 들어가는 정보 - 웹 페이지에 보이는 정보)

    }
}
