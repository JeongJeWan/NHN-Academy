package com.nhnacademy.board.controller.userController;

import com.nhnacademy.board.controller.Command;
import com.nhnacademy.board.repository.UserRepository;
import com.nhnacademy.board.user.User;
import com.nhnacademy.board.user.UserImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@MultipartConfig(
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100, //   100MB
        location = "/Users/jeongjewan/IdeaHomework/week6/day04/boardversion4_profile_FIle/upload/temp" //  실제로 저장
)
@Slf4j
public class UserRegisterPostController implements Command {

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/Users/jeongjewan/IdeaHomework/week6/day04/boardversion4_profile_FIle/upload";    // 임시 디렉토리

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository userRepository = (UserRepository) req.getServletContext().getAttribute("userRepository");

        String id = req.getParameter("id");
        if(Objects.isNull(id)) {
            throw new RuntimeException("아이디가 존재하지 않습니다,");
        }
        String password = req.getParameter("password");
        if(Objects.isNull(password)) {
            throw new RuntimeException("비밀번호가 존재하지 않습니다.");
        }
        String name = req.getParameter("name");
        if(Objects.isNull(name)) {
            throw new RuntimeException("이름이 존재하지 않습니다.");
        }

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

        String profileFileName = req.getParameter("profileFileName");
        if(Objects.isNull(profileFileName)) {
            throw new RuntimeException("프로필파일이름이 존재하지 않습니다,");
        }


        User user = new UserImpl(id, password, name, profileFileName);
        userRepository.add(user);

        return "redirect:/userView.do?id=" + user.getId();
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
