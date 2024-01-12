# Spring

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
