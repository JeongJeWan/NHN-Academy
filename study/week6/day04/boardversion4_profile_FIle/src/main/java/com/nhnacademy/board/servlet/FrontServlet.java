package com.nhnacademy.board.servlet;

import com.nhnacademy.board.controller.*;
import com.nhnacademy.board.controller.postController.*;
import com.nhnacademy.board.controller.userController.*;
import com.nhnacademy.board.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo 공통 처리 - 응답 content-type, character encoding 지정.
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        if (Objects.nonNull(CookieUtils.getCookie(req, "locale"))) {
            String locale = CookieUtils.getCookie(req, "locale").getValue();
            req.setAttribute("locale", locale);
        }

        try{
            //실제 요청 처리할 Command(Controller) 결정
            Command command = resolveCommand(req.getServletPath(), req.getMethod());
            log.error("ServletPath:{}",req.getServletPath());
            log.error("getMethod:{}",req.getMethod());
            String view = command.execute(req, resp);
            log.error("view:{}",view);
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length()+1));
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()+1));

            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);  // 밑에 로직이 있으면 실행되지 않고 forward에서 바로 끝남
            }
        }catch(Exception ex){
            ex.getStackTrace();
            //todo  forward - /error.jsp
            log.error("ex: 오류 발생 fromServlet");
            log.error("ex:{}", ex.getMessage());

        }
    }

    private Command resolveCommand(String servletPath, String method){
        //todo 실행할 Command 결정하기
        Command command = null;
        if("/userList.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new UserListGetController();
        }
        else if ("/login.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new LoginGetController();
        } else if ("/login.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new LoginPostController();
        } else if ("/logout.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new LogoutGetController();
        } else if ("/loginForm.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new LoginFormGetController();
        }

        if ("/postList.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new PostListGetController();
        } else if ("/postRegister.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new PostRegisterGetController();
        } else if ("/postRegister.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new PostRegisterPostController();
        } else if ("/postView.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new PostViewGetController();
        } else if ("/postDelete.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new PostDeletePostController();
        } else if ("/postUpdate.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new PostUpdateGetController();
        } else if ("/postUpdate.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new PostUpdatePostController();
        }

        if ("/userDelete.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new UserDeletePostController();
        } else if ("/userRegister.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new UserRegisterGetController();
        } else if ("/userRegister.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new UserRegisterPostController();
        } else if ("/userUpdate.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new UserUpdateGetController();
        } else if ("/userUpdate.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new UserUpdatePostController();
        } else if ("/userView.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new UserViewGetController();
        }

        if("/proFileFileUpload.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new ProfileFIleUploadController();
        }

        return command;
    }

}