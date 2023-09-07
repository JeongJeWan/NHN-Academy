package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.Cart;
import com.nhnacademy.nhnmart.Food;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@WebServlet(name = "cartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 세션 정보 가져오기
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(true);

        // 장바구니 객체 가져오기
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
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

        if (!added) {
            cart.add(new Cart.Item(foodName, quantity, foodPrice));
        }


        // 상품 수량보다 더 많은 주문을 하지 않았는 지 검증
        ServletContext context = getServletContext();
        ArrayList<Food> foods = (ArrayList<Food>) context.getAttribute("foods");

        // 장바구니에 담은 수량만큼 상품매대에서 제거 처리
        for (Food food : foods) {
            if (food.getName().equals(foodName)) {
                food.setQuantity(food.getQuantity() - quantity);
                break;
            }
        }

        // 전체 품목 수량 다시 설정
        context.setAttribute("foods", foods);

        // 장바구니 페이지로 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/views/cart.jsp");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 장바구니 페이지로 포워드
        req.getRequestDispatcher("/views/cart.jsp").forward(req, resp);
    }
}