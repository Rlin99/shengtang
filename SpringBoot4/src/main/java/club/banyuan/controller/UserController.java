package club.banyuan.controller;

import club.banyuan.entity.Address;
import club.banyuan.entity.Order;
import club.banyuan.entity.User;

import club.banyuan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

//    @Autowired
//    private OrderService orderService;

    @RequestMapping("/login.do")
    public String login(HttpSession session, String loginName, String password){
        Map map = new HashMap();
        map.put("loginName", loginName);
        map.put("password", password);
        User user = null;
        user = userService.login(map);
        System.out.println(map.get("loginName")+"    "+map.get("password"));
        if(user != null){
            session.setAttribute("user", user);
            System.out.println(user);

            //获取地址address
            List<Address> addressList = new ArrayList<Address>();
                addressList = userService.getAddress((user.getId()));
                for (Address address : addressList) {
                    if(address.getIsDefault() == 1){
                        session.setAttribute("defaultAddress", address.getAddress());
                    }
                }
                session.setAttribute("addressList", addressList);


//            //获取用户订单信息
//            List<Order> orderList = new ArrayList<Order>();
//            orderList = orderService.selectOrderByUserId(user.getId());
//            session.setAttribute("orderList", orderList);

            return "index.html";
        }else {
            System.out.println("登陆失败");
            return "login.html";
        }
    }
}
