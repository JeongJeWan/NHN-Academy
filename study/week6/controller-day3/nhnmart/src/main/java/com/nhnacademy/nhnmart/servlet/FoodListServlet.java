package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.domain.Food;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "foodListServlet", urlPatterns = "/foods")
public class FoodListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        ArrayList<Food> foods = (ArrayList<Food>) context.getAttribute("foods");

        req.setAttribute("foods", foods);
        req.getRequestDispatcher("/views/foodList.jsp").forward(req, resp);

    }
}
