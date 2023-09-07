package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.Food;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FoodListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ServletContext context = getServletContext();
        ArrayList<Food> foods = (ArrayList<Food>) context.getAttribute("foods");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head><title>Food List</title></head>");
            out.println("<body>");
            out.println("<h2>상품 목록</h2>");
            out.println("<table border=\"1\">");
            out.println("<tr><th>상품명</th><th>가격</th><th>재고</th><th>장바구니</th></tr>");
            for (Food food : foods) {
                out.println("<tr>");
                out.println("<td>" + food.getName() + "</td>");
                out.println("<td>" + food.getPrice() + "</td>");
                out.println("<td>" + food.getQuantity() + "</td>");
                out.println("<td>");
                out.println("<form method=\"post\" action=\"cart\">");
                out.println("<input type=\"hidden\" name=\"name\" value=\"" + food.getName() + "\">");
                out.println("<input type=\"hidden\" name=\"price\" value=\"" + food.getPrice() + "\">");
                out.println("<input type=\"number\" name=\"quantity\" min=\"1\" max=\"" + food.getQuantity() + "\">");
                out.println("<input type=\"submit\" value=\"담기\">");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
