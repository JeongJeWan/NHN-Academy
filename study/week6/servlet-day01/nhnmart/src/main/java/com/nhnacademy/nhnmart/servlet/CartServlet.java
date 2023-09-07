package com.nhnacademy.nhnmart.servlet;

import com.nhnacademy.nhnmart.Cart;
import com.nhnacademy.nhnmart.Food;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class CartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 세션 정보 가져오기
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);

        // 장바구니 객체 가져오기
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // 선택한 상품 정보 가져오기
        String foodName = request.getParameter("name");
        int foodPrice = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // 상품 수량보다 더 많은 주문을 하지 않았는 지 검증
        ServletContext context = getServletContext();
        ArrayList<Food> foods = (ArrayList<Food>) context.getAttribute("foods");
        for (Food food : foods) {
            if (food.getName().equals(foodName) && food.getQuantity() < quantity) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "주문 수량이 재고 수량을 초과합니다.");
                return;
            }
        }

        // 장바구니에 상품 추가
        cart.add(new Cart.Item(foodName, quantity, foodPrice));

        // 장바구니에 담은 수량만큼 상품매대에서 제거 처리
            for (Food food : foods) {
                if (food.getName().equals(foodName)) {
                    food.setQuantity(food.getQuantity() - quantity);
                    break;
                }
            }


        // 전체 품목 수량 다시 설정
        context.setAttribute("foods", foods);


        // 응답 화면에 장바구니 화면으로 이동할 수 있는 링크 제공
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>장바구니</title></head>");
            out.println("<body>");
            out.println("<h2>장바구니</h2>");
            out.println("<p>상품이 추가되었습니다.</p>");
            out.println("<p><a href=\"/cart\">장바구니 보기</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head><title>장바구니</title></head>");
            out.println("<body>");

            if (cart != null && !cart.getItemList().isEmpty()) {
                out.println("<h2>장바구니</h2>");
                out.println("<table border=\"1\">");
                out.println("<tr><th>상품명</th><th>가격</th><th>수량</th><th>합계</th></tr>");
                int totalAmount = 0;
                for (Cart.Item item : cart.getItemList()) {
                    out.println("<tr>");
                    out.println("<td>" + item.getName() + "</td>");
                    out.println("<td>" + item.getPrice() + "</td>");
                    out.println("<td>" + item.getAmount() + "</td>");
                    int itemTotalAmount = item.getPrice() * item.getAmount();
                    out.println("<td>" + itemTotalAmount + "</td>");
                    totalAmount += itemTotalAmount;
                    out.println("</tr>");
                }
                out.println("<tr>");
                out.println("<th colspan=\"3\">전체 금액</th>");
                out.println("<td>" + totalAmount + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<a href=\"foods\">상품 목록</a>");
            } else {
                out.println("<h2>장바구니가 비어 있습니다.</h2>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}