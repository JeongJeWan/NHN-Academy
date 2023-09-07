package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.controller.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo 공통 처리 - 응답 content-type, character encoding 지정.
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try{
            //실제 요청 처리할 servlet을 결정
//            String servletPath = resolveServlet(req.getServletPath());
//            RequestDispatcher rd = req.getRequestDispatcher(servletPath);
//            rd.include(req, resp);  //  include는 잡고 있다가 밑에 로직 이후에 포함시켜서 보냄

            Command command = resolveCommand(req.getServletPath(), req.getMethod());
            //실제 요청을 처리한 servlet이 'view'라는 request 속성값으로 view를 전달해 줌.
            String view = command.execute(req, resp);

            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length()+1));
                // todo  `redirect:`로 시작하면 redirect 처리.
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()+1));

            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);  // 밑에 로직이 있으면 실행되지 않고 forward에서 바로 끝남
            }
        } catch (Exception ex) {
            Command error = new ErrorController();
            String viewError = error.execute(req, resp);
            RequestDispatcher rd = req.getRequestDispatcher(viewError);
            rd.forward(req, resp);
        }
    }

//    private String resolveServlet(String servletPath){
//        //todo 실행할 servlet 결정하기
//        String processingServlet = null;
//        if("/init.do".equals(servletPath)){
//            processingServlet = "/init";
//        } else if("/foods.do".equals(servletPath)){
//            processingServlet = "/foods";
//        } else if("/cart.do".equals(servletPath)){
//            processingServlet = "/cart";
//        } else if("/login.do".equals(servletPath)){
//            processingServlet = "/login";
//        } else if("/logout.do".equals(servletPath)){
//            processingServlet = "/logout";
//        } else if("/index.do".equals(servletPath)) {
//            processingServlet = "/index";
//        } else if("/loginForm.do".equals(servletPath)) {
//            processingServlet = "/loginForm";
//        }
//
//        return processingServlet;
//    }

    private Command resolveCommand(String servletPath, String method) {
        Command command = null;
        if ("/init.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new InitGetController();
        } else if ("/foods.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new FoodListGetController();
        } else if ("/cart.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new CartGetController();
        } else if ("/cart.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new CartPostController();
        } else if ("/login.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new LoginGetController();
        } else if ("/login.do".equals(servletPath) && "POST".equalsIgnoreCase(method)) {
            command = new LoginPostController();
        } else if ("/logout.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new LogoutGetController();
        } else if ("/index.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new IndextGetController();
        } else if ("/loginForm.do".equals(servletPath) && "GET".equalsIgnoreCase(method)) {
            command = new LoginFormGetController();
        }

        return command;
    }

}