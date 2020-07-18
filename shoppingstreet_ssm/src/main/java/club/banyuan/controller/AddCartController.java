package club.banyuan.controller;

import club.banyuan.entity.Product;
import club.banyuan.service.ProductService;
import club.banyuan.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AddCartController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/addCart.do")
    public String addCart(Integer num, Integer productId, HttpSession session){
        Product product = null;
            product = productService.getProductById(productId);

        if(product!=null) {
            Map<Product, Integer> cart = null;
            if(null == session.getAttribute("cart")){
                cart = new HashMap<>();
            }else {
                cart = (Map<Product,Integer>)session.getAttribute("cart");
            }
            cart.put(product,num);
            session.setAttribute("cart",cart);
        }
        return "buycar";
    }
}
