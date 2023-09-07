package com.nhnacademy.board.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100, //   100MB
        location = "/Users/jeongjewan/IdeaHomework/week6/day04/boardversion4_profile_FIle/upload/temp" //  실제로 저장
)
@Slf4j
public class ProfileFIleUploadController implements Command{

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/Users/jeongjewan/IdeaHomework/week6/day04/boardversion4_profile_FIle/upload";    // 임시 디렉토리

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(Part part : req.getParts()) {
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

            if(contentDisposition.contains("filename=")) {
                String fileName = getFileName(contentDisposition);
                if(part.getSize() > 0) {
//                    String fileName = getFileName(contentDisposition);
                    part.write(UPLOAD_DIR + File.separator + fileName); //  File.separator 파일 구분자
                    part.delete();
                }
            }else {
                String formValue = req.getParameter(part.getName());
                log.error("name: {}, value : {}", part.getName(), formValue);
            }
        }
        return null;
    }

    private String getFileName(String contentDisposition) {
        for(String token : contentDisposition.split(";")) {
            if(token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=")+2, token.length()-1);
            }
        }
        return null;
    }
}
