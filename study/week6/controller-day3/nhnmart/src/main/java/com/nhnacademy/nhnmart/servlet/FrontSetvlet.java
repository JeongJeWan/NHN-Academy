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
public class FrontSetvlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo 공통 처리 - 응답 content-type, character encoding 지정.
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        try{
            //실제 요청 처리할 Command(Controller) 결정
            Command command = resolveCommand(req.getServletPath(), req.getMethod());
            String view = command.execute(req, resp);
            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length()+1));
                resp.sendRedirect(view.substring(REDIRECT_PREFIX.length()+1));

            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);  // 밑에 로직이 있으면 실행되지 않고 forward에서 바로 끝남
            }
        }catch(Exception ex){

            //todo  forward - /error.jsp
            Command error = new ErrorController();
            String viewError = error.execute(req, resp);
            RequestDispatcher rd = req.getRequestDispatcher(viewError);
            rd.include(req, resp);

        }
    }

    private Command resolveCommand(String servletPath, String method){
        //todo 실행할 Command 결정하기
        Command command = null;
        if("/student/init.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new InitController();
        }else if("/student/foodList.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new FoodListController();
        }else if("/student/cart.do".equals(servletPath) && "GET".equalsIgnoreCase(method) ){
            command = new CartFormController();
        }else if("/student/cart.do".equals(servletPath) && "POST".equalsIgnoreCase(method) ){
            command = new CartController();
        }else if("/error.do".equals(servletPath)){
            command = new ErrorController();
        }
        return command;
    }

}
