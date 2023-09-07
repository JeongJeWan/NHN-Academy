package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.Food;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class FoodListController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext context = req.getServletContext();
        ArrayList<Food> foods = (ArrayList<Food>) context.getAttribute("foods");

        req.setAttribute("foods", foods);
        return "/views/foodList.do";
    }
}
