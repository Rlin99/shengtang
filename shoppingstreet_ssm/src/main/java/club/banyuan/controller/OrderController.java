package club.banyuan.controller;

import club.banyuan.entity.Order;
import club.banyuan.entity.OrderDetail;
import club.banyuan.entity.Product;
import club.banyuan.entity.User;
import club.banyuan.service.OrderService;
import club.banyuan.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/order.do")
    public String order(HttpSession session){

        Double sumcost = (Double) session.getAttribute("sumcost");
        User user = (User)session.getAttribute("user");


        Order order = new Order();
        order.setUserId(user.getId());
        order.setLoginName(user.getLoginName());
        order.setUserAddress(session.getAttribute("defaultAddress").toString());
        order.setCost(sumcost);
        Date date = new Date();
        order.setCreateTime(date);

        //按照时间随机生成订单号
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate=sdf.format(new Date());
        String result="";
        Random random=new Random();
        for(int i=0;i<3;i++){
            result+=random.nextInt(10);
        }

        order.setSerialNumber(newDate+result);


            List<OrderDetail> orderDetailList = new ArrayList<>();
            Map<Product,Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
            for (Product product : cart.keySet()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProductId(product.getId());
                orderDetail.setQuantity(cart.get(product));
                orderDetail.setCost((Double) (cart.get(product) * product.getPrice()));
                orderDetailList.add(orderDetail);
            }
            orderService.createOrder(order, orderDetailList);

            session.setAttribute("serialNumber", order.getSerialNumber());

            //获取用户订单信息
            List<Order> orderList = new ArrayList<>();
            orderList = orderService.selectOrderByUserId(user.getId());
            session.setAttribute("orderList", orderList);


        return "BuyCar_Three";
    }
}
