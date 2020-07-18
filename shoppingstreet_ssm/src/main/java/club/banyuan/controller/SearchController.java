package club.banyuan.controller;

import club.banyuan.dao.ProductDao;
import club.banyuan.entity.Product;
import club.banyuan.service.ProductService;
import club.banyuan.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/search.do")
    public String searchProduct(String productName, HttpServletRequest request){
        List<Product> productList = new ArrayList<Product>();
        System.out.println(productName);
            productList = productService.getProductByName("%"+productName+"%");
            for (Product product : productList) {
                System.out.println(product);
            }
            request.setAttribute("size", productList.size());
            request.setAttribute("productList", productList);
        return "CategoryList";
    }
}
