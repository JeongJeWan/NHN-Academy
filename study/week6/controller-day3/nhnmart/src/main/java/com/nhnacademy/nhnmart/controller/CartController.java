package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.Cart;
import com.nhnacademy.nhnmart.domain.Food;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class CartController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);

        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String foodName = req.getParameter("name");
        int foodPrice = Integer.parseInt(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        boolean added = false;

        for(Cart.Item item : cart.getItemList()) {
            if (item.getName().equals(foodName)) {
                item.setAmount(item.getAmount() + quantity);
                added = true;
                break;
            }
        }

        if(!added) {
            cart.add(new Cart.Item(foodName, foodPrice, quantity));
        }

        ServletContext context = req.getServletContext();
        ArrayList<Food> foods = (ArrayList<Food>) context.getAttribute("foods");

        for(Food food : foods) {
            if (food.getName().equals(foodName)) {
                food.setQuantity(food.getQuantity() - quantity);
                break;
            }
        }

        context.setAttribute("foods", foods);
        return "redirect:/views/cart.do";
    }
}
