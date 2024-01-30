package com.example.servlet.web.frontcontroller.v5.adapter;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;
import com.example.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// ControllerV3를 지원하는 어댑터를 구현
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        // ControllerV3 을 처리할 수 있는 어댑터를 뜻한다
        return (handler instanceof ControllerV3);
    }

    /*
    * handler를 컨트롤러 V3로 변환한 다음에 V3 형식에 맞도록 호출한다.
    * supports() 를 통해 ControllerV3 만 지원하기 때문에 타입 변환은 걱정없이 실행해도 된다.
    * ControllerV3는 ModelView를 반환하므로 그대로 ModelView를 반환하면 된다.
    */
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3)handler;
        Map<String, String> paramMap = createParamMap(request);
        return controller.process(paramMap);
    }

    // HttpServletRequest에서 파라미터 정보를 꺼내서 Map으로 변환
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        // 모든 파라미터를 꺼내서 넣는다
        request.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}
