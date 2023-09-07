package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.Food;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class InitController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ArrayList<Food> foods = new ArrayList<>();
        String foodList = req.getServletContext().getInitParameter("foodList");
        for(String s : foodList.split(";")) {
            String[] tokens = s.split(",");
            foods.add(new Food(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));
        }

        ServletContext context = req.getServletContext();
        context.setAttribute("foods", foods);

        req.setAttribute("foods", foods);
        return "/views/init.do";
    }
}
