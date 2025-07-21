package com.example.servlet.web.frontcontroller.v3;

import com.example.servlet.web.frontcontroller.ModelView;

import java.util.Map;

// Model 추가를 위한 FrontController 인터페이스
// 컨트롤러 입장에서 HttpServletRequest, HttpServletResponse이 꼭 필요할까? -> request 객체를 Model로 사용하는 대신에 별도의 Model 객체를 만들어서 반환(서블릿 기술을 전혀 사용하지 않는다)
public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
