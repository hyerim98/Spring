package com.example.springbasic.componentscan.filter;

import java.lang.annotation.*;

// 어노테이션 생성(사용자 정의) 시 필요한 어노테이션
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
