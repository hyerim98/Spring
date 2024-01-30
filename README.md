# SpringBasic
## src 설명
* **AppConfig.java**
  * 스프링 컨테이너 생성
* **AutoAppConfig.java**
  * 스프링 컨테이너 생성
  * @Component 어노테이션이 붙은 클래스를 스캔하여 자동으로 스프링 빈으로 등록
* **member, order, discount**
![springBasic](https://github.com/hyerim98/Spring/assets/88373857/2edf8011-a732-4316-a87a-4ea6bd8af8d5)
  * OCP 원칙을 위하여 인터페이스를 생성(MemberService.java)하고 해당 인터페이스를 구현(MemberServiceImpl.java)하는 구현 클래스를 생성
  * 인터페이스(추상 클래스)에 의존해야 함(구현 클래스에 의존하면 OCP 원칙 지키지 못한 것)
    ```
    // EX)
    public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
    }
    ```

## test 설명
* test는 Assertions로 테스트를 진행(print로 진행 X)
```
// import org.assertj.core.api.Assertions;
Assertions.assertThat(discount).isEqualTo(1000);
Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

// import static org.junit.jupiter.api.Assertions;
// assertThrows(에러 발생 종류, 해당 코드 실행 시)
Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
```
<br/><br/>
# SpringMVC
## Servlet
* [Servlet](http://localhost:9090/basic.html)
* **com.example.servlet.basic.request**
  * request header 정보
  * request parameter 조회하는 방법들
  * request message body 조회하는 방법(string 타입, json 타입)
* **com.example.servlet.basic.response**
  * response header 설정
  * response html 형식, json 형식

## Servlet MVC
* [Servlet MVC](http://localhost:9090/index.html)
* **com.example.servlet.web.servlet**
  ```
  // 응답

  response.setContentType("text/html");
  response.setCharacterEncoding("utf-8");

  PrintWriter w = response.getWriter();
  w.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                " <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                " username: <input type=\"text\" name=\"username\" />\n" +
                " age: <input type=\"text\" name=\"age\" />\n" +
                " <button type=\"submit\">전송</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n");
  ```
* **com.example.servlet.web.servletmvc**
  ```
  // 응답
  
  String viewPath = "/WEB-INF/views/new-form.jsp";
  // controller -> view로 이동할 때 사용
  RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
  // 다른 서블릿이나 JSP로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출이 발생한다.
  dispatcher.forward(request, response);
  ```

## MVC 프레임워크
* [MVC 프레임워크](http://localhost:9090/index.html)
* **com.example.servlet.web.frontcontroller.**
 * v1: 프론트 컨트롤러를 도입
   * 기존 구조를 최대한 유지하면서 프론트 컨트롤러를 도입
 * v2: View 분류
   * 단순 반복 되는 뷰 로직 분리
 * v3: Model 추가
   * 서블릿 종속성 제거
   * 뷰 이름 중복 제거
 * v4: 단순하고 실용적인 컨트롤러
   * v3와 거의 비슷
   * 구현 입장에서 ModelView를 직접 생성해서 반환하지 않도록 편리한 인터페이스 제공
 * v5: 유연한 컨트롤러
   * 어댑터 도입
   * 어댑터를 추가해서 프레임워크를 유연하고 확장성 있게 설계
