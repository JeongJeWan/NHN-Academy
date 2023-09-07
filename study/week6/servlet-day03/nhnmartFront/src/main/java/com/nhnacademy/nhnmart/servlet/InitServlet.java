package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.Food;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "initServlet", urlPatterns = "/init")
public class InitServlet extends HttpServlet {
    private ArrayList<Food> foods = new ArrayList<>();

    public void init() throws ServletException {
        // context-param에서 foodList 값을 가져와서 식품 객체를 생성
        String foodList = getServletContext().getInitParameter("foodList");
        for (String s : foodList.split(";")) {
            String[] tokens = s.split(",");
            foods.add(new Food(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
        }

        // ServletContext에 식품 리스트를 설정
        ServletContext context = getServletContext();
        context.setAttribute("foods", foods);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("foods", foods);

//        req.getRequestDispatcher("/views/init.jsp").forward(req, resp);
        req.setAttribute("view", "/views/init.jsp");
    }
}
