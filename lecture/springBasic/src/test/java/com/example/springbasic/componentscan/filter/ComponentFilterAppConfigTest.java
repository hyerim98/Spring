package com.example.springbasic.componentscan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 컴포넌트 스캔 시 포함 할, 제외 할 class 설정하여 테스트 진행
// 보통 필터 많이 사용하지 않음, includeFilters는 더더욱 사용하지 않음 excludeFilters는 아주 가끔 사용됨

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        // 컴포넌트 스캔 포함이므로 BeanA 존재
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        // 컴포넌트 스캔 제외시켰으므로 BeanB는 존재하지 않음
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(
        includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class), // 스캔 포함
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class) // 스캔 불포함
    )
    static class ComponentFilterAppConfig {

    }
}
