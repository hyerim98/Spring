package com.example.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 빈의 이름으로 URL을 매핑
/*
* OldController 핸들러 매핑, 어댑터
    * HandlerMapping = BeanNameUrlHandlerMapping
    * HandlerAdapter = SimpleControllerHandlerAdapter
*/
@Component("/springmvc/old-controller")
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");

        //  View 조회
        return new ModelAndView("new-form");
    }
}
