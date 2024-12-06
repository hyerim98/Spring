package hello.jdbc.connection;

// 객체 생성을 못하도록 abstract로 클래스 생성
public abstract class ConnectionConst {
    public static final String URL = "jdbc:h2:tcp://localhost/~/test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";
}
