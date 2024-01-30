package com.example.servlet.web.frontcontroller.v4;

import java.util.Map;

// ModelView -> ViewName으로 반환하는 컨트롤러로 변경
public interface ControllerV4 {
    /**
     *
     * @param paramMap
     * @param model
     * @return viweName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
