package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.Food;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class InitGetController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ArrayList<Food> foods = new ArrayList<>();

        String foodList = req.getServletContext().getInitParameter("foodList");
        for (String s : foodList.split(";")) {
            String[] token = s.split(",");
            foods.add(new Food(token[0], Integer.parseInt(token[1]), Integer.parseInt(token[2])));
        }

        ServletContext context = req.getServletContext();
        context.setAttribute("foods", foods);

        req.setAttribute("foods", foods);

        return "/views/init.jsp";
    }
}
