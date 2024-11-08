package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {
    // JSON이 객체로 잘 변경이 되어야 Validate를 하든 다음 단계로 넘어간다 객체로 변경되지 않으면 그 다음 단계로 아예 못넘어간다(controller 호출X)
    // @RequestBody : 클라이언트가  보내는 HTTP 요청 본문을 JAVA 오브젝트로 변환하는 것(HttpMessageConverter를 통해 타입에 맞는 객체로 변환)
    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult) {
        log.info("API 컨트롤러 호출");

        if(bindingResult.hasErrors()) {
            log.info("검증 오류 발생 error = {}", bindingResult);
            return bindingResult.getAllErrors(); // JSON 객체로 전달됨
        }

        log.info("성공 로직 실행");
        return form;
    }
}
