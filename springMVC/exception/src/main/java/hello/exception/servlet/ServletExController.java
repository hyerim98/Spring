package hello.exception.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/**
 * sendError 흐름
    * WAS(sendError 호출 기록 확인) <- 필터 <- 서블릿 <- 인터셉터 <- 컨트롤러(response.sendError())
 */
@Slf4j
@Controller
public class ServletExController {
    @GetMapping("/error-ex")
    public void errorEx() {
        throw new RuntimeException("예외 발생");
    }

    /**
     * response 내부에는 오류가 발생했다는 상태를 저장
     * 서블릿 컨테이너는 고객에서 응답 전에 response sendError()가 호출되었는지 확인
     * 호출되었다면  설정한 오류 코드에 맞추어 기본 오류 페이지를 보여줌
     */
    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        // sendError를 호출한다고 당장 예외가 발생하는 것은 아니지만, 서블릿 컨테이너에게 오류가 발생했다는 점을 전달 가능
        response.sendError(404, "404 오류"); // errorPage404 호출
    }
    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500); // errorPage500 호출
    }
}
