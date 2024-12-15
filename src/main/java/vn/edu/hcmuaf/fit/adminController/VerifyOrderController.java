package vn.edu.hcmuaf.fit.adminController;

import vn.edu.hcmuaf.fit.beans.Orders;

import vn.edu.hcmuaf.fit.services.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "VerifyOrderController", value = "/VerifyOrderController")
public class VerifyOrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderID = request.getParameter("orderId");
        OrderService orderService = new OrderService();
        Orders orders = orderService.getOrderByIdOrder(orderID);
        orderService.changeStatusVerify(orders.getOrderID(), 1);
        /**
         * chuyển order tới loadOrder
         * verify dựa trên messSign đã gửi và public key mới nhất trong db
         * trả về kết quả Chưa xác thực/Đã xác thực/Lỗi đơn
         */
        response.sendRedirect("/Petshop_website_final_war/ajax/admin-ajax-loadOrder.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}