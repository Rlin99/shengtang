package club.banyuan.controller;

import club.banyuan.entity.Product;
import club.banyuan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductDetailController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/detail.do")
    public String detail(String id, Model request){
        int productId = Integer.valueOf(id);
            Product product = productService.getProductById(productId);
            request.addAttribute("product",product);
        return "product.html";
    }
}
