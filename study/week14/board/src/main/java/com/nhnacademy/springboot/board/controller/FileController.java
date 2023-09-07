package com.nhnacademy.springboot.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@RequestMapping
public class FileController {
    private final String PATH = "/Users/yerin/Downloads/";

    @GetMapping(value = "/attach/{filename}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable("filename") String fileName) throws MalformedURLException {
        String encodedUploadFileName = UriUtils.encode(fileName, StandardCharsets.UTF_8);

        UrlResource resource = new UrlResource("file:" + PATH + encodedUploadFileName);

        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
