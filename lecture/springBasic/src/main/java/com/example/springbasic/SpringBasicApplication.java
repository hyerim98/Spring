package com.example.springbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Configuration : 스프링 빈이 싱글톤을 유지하도록 추가 처리
 * @ComponentScan : 스프링 컨테이너에 @Component 어노테이션이 붙은 클래스를 스프링 빈으로 등록(@Autowired는 스프링 컨테이너에 등록 된 스프링 빈을 찾아서 의존관계를 맺어줌)
 * @SpringBootApplication = @Configuration + @ComponentScan
 */
@SpringBootApplication
public class SpringBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicApplication.class, args);
	}

}
