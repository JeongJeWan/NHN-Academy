package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.Food;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InitServlet extends HttpServlet {
    private ArrayList<Food> foods = new ArrayList<>();

    public void init() throws ServletException {
        // context-param에서 foodList 값을 가져와서 식품 객체를 생성
        String foodList = getServletContext().getInitParameter("foodList");
        Arrays.stream(foodList.split(";"))
                .forEach(s -> {
                    String[] tokens = s.split(",");
                    foods.add(new Food(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
                });

        // ServletContext에 식품 리스트를 설정
        ServletContext context = getServletContext();
        context.setAttribute("foods", foods);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession(true);

        if (session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/loginForm.html"); // Redirect to login page if not logged in
            return;
        }
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head><title>Init</title></head>");
            out.println("<body>");
            out.println("<h2>식품 매대가 준비되었습니다.</h2>");
            out.println("<a href=\"foods\">상품 목록</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
