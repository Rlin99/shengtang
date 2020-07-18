package club.banyuan.controller;

import club.banyuan.entity.Address;
import club.banyuan.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ConfrimController {

    @RequestMapping("/confirmOrder.do")
    public String confirm(HttpSession session){
        if((User)session.getAttribute("user") == null){
            return "login";
        }
        //商品总价，默认地址

        //获取address
        List<Address> addressList = new ArrayList<Address>();
        addressList = (ArrayList<Address>)session.getAttribute("addressList");
        for (Address address : addressList) {
            if(address.getIsDefault() == 1){
                session.setAttribute("buyCarAddress", address);
            }
        }
        return "BuyCar_Two";
    }
}
