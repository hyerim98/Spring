package com.example.springintro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello World");

        // ViewResolver가 화면을 찾아서 처리 -> resources:templates/{viewName}.html
        return "hello";
    }
    
    // MVC와 템플릿 엔진
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) { // "http://localhost:8080/hello-mvc?name=aa" -> aa 파라미터로 받는다 하지만, 파라미터가 필수는 아님
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API
    @GetMapping("hello-string")
    @ResponseBody // http 응답 body 부분에 내가 직접 값을 넣겠다
    public String helloString(@RequestParam(value = "name") String name) { // "http://localhost:8080/hello-string?name=aa" -> aa 파라미터로 받는다 (파라미터 필수)
        return "hello " + name; // 이 return 값을 화면에 그대로 보여줌
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam(value = "name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체는 JSON 형식으로 전달됨(기본)
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
