package club.banyuan.controller;

import club.banyuan.entity.Product;
import club.banyuan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AddCartController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/addCart.do")
    public String addCart(Integer num, Integer productId, HttpSession session, Model model){
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

        Double sumcost = 0.0;
        int i = 0;
        Map<Product,Integer> cart = (Map<Product,Integer>)session.getAttribute("cart");
    for (Product product1 : cart.keySet()) {
      sumcost += product1.getPrice() * cart.get(product1);
      i++;
            }
    model.addAttribute("sumcost", sumcost);
        session.setAttribute("Pnum", i);
        return "buycar";
    }
}
