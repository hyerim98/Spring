package hello.springmvc.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 문자열 반환
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 기본 요청
     * 둘다 허용 /hello-basic, /hello-go
     * @return
     */
    @RequestMapping({"/hello-basic", "/hello-go"})
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
        *  @PathVariable("userId") String userId -> @PathVariable String userId
     *  /mapping/userA
     * @param data
     * @return
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId = {}", data);
        return "ok";
    }

    /**
     * PathVariable 다중 사용
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }

     /**
      * 특정 파라마터 조건 매핑(사용X)
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
     @GetMapping(value = "/mapping-param", params = "mode=debug")
     public String mappingParam() {
        log.info("mappingParam");
        return "ok";
     }

     /**
      * 특정 헤더 조건 매핑
     * 특정 헤더로 추가 매핑
      * 헤더에 key : mode, value : debug가 있어야 해당 API 요청 가능
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
     @GetMapping(value = "/mapping-header", headers = "mode=debug")
     public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
     }

      /**
       * 미디어 타입 조건 매핑 - HTTP 요청 Content-Type, consume : 클라이언트가 서버에게 보내는 데이터 타입을 명시
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
     @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
     public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
     }

      /**
       * 미디어 타입 조건 매핑 - HTTP 요청 Accept, produce : 서버가 클라이언트에게 반환하는 데이터 타입을 명시
       * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
     @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
     }
}
