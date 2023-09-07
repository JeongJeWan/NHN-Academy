package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.Cart;
import com.nhnacademy.nhnmart.Food;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Slf4j
public class CartPostController implements Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession(true);

        //  장바구니 객체 가져오기
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // 장바구니에 상품 추가
        String foodName = req.getParameter("name");
        int foodPrice = Integer.parseInt(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        boolean added = false;

        for (Cart.Item item : cart.getItemList()) {
            if (item.getName().equals(foodName)) {
                item.setAmount(item.getAmount() + quantity);
                added = true;
                break;
            }
        }


        if(!added) {
            cart.add(new Cart.Item(foodName, quantity, foodPrice));
        }

        //  상품 수량보다 더 많은 주문을 하지 않았는 지 검증
        ServletContext context = req.getServletContext();
        ArrayList<Food> foods = (ArrayList<Food>) context.getAttribute("foods");

        // 장바규니에 담은 수량만큼 상품매데에서 제거 처리
        for(Food food : foods) {
            log.error("food.getName: {}", food.getName());
            log.error("foodName: {}", foodName);
            if (food.getName().equals(foodName)) {
                food.setQuantity(food.getQuantity() - quantity);
                break;
            }
        }

        // 전체 품목 수량 다시 설정
        context.setAttribute("foods", foods);

        // 장바구니 페이지로 리다이렉트
        return "redirect:/cart.do";
    }
}
