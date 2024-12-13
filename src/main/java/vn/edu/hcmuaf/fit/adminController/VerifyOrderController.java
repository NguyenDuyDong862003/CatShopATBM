package vn.edu.hcmuaf.fit.adminController;

import vn.edu.hcmuaf.fit.beans.OrderDetail;
import vn.edu.hcmuaf.fit.beans.Orders;
import vn.edu.hcmuaf.fit.beans.PublicKey;
import vn.edu.hcmuaf.fit.dao.KeyDAO;
import vn.edu.hcmuaf.fit.dao.OrderDAO;
import vn.edu.hcmuaf.fit.services.OrderService;
import vn.edu.hcmuaf.fit.tool.DSA;
import vn.edu.hcmuaf.fit.tool.Hash;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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