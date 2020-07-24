package club.banyuan.controller;

import club.banyuan.entity.Product;
import club.banyuan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/search.do")
    public String searchProduct(String productName, Model request){
        List<Product> productList = new ArrayList<Product>();
        System.out.println(productName);
            productList = productService.getProductByName("%"+productName+"%");
            for (Product product : productList) {
                System.out.println(product);
            }
            request.addAttribute("size", productList.size());
            request.addAttribute("productList", productList);
        return "CategoryList";
    }
}
