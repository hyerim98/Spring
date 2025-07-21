package com.example.springbasic.singleton;

// 싱글톤 패턴
// 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다 -> 싱글톤 컨테이너 사용 권장
public class SingletonService {
    // static 영역에 객체를 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();

    // public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    public static  SingletonService getInstance() {
        return instance;
    }

    // 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
