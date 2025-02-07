package hello.upload.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {

    @Value("${file.dir}") // application.properties에서 값 가져오기
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @ResponseBody
    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws ServletException, IOException {
        log.info("request = {}", request);

        String itemName = request.getParameter("itemName");
        log.info("itemName = {}", itemName);

        Collection<Part> parts = request.getParts(); // mutiparts form 데이터 받기
        log.info("parts = {}", parts);

        for (Part part : parts) {
            log.info("==== PARTS ====");
            log.info("name = {}", part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header {} : {}", headerName, part.getHeader(headerName));
            }

            // 편의 메서드
            // content-disposition; filename
            log.info("submittedFilename = {}", part.getSubmittedFileName()); // 클라이언트가 전달한 파일명
            log.info("size = {}", part.getSize()); // part body size

            // 데이터 읽기
            InputStream inputStream = part.getInputStream(); // Part의 정송 데이터를 읽을 수 있다
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("body = {}", body);

            // 파일에 저장
            if(StringUtils.hasText(part.getSubmittedFileName())) {
                String fullPath = fileDir + "/" + part.getSubmittedFileName();
                log.info("파일 저장 fullPath = {}", fullPath);
                part.write(fullPath); // Part를 통해 전송된 데이터를 저장할 수 있다
            }

        }

        return "ok";
    }
}
