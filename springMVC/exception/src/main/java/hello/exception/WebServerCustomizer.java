package hello.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

// 서블릿 ERROR 페이지 설정
//@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");
        // RuntimeException이 발생하면 /error-page/500이 호출됨
        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/error-page/500"); // WAS는 해당 예외를 처리하는 오류 페이지 정보를 확인

        factory.addErrorPages(errorPage404, errorPage500, errorPageEx);
    }
}
