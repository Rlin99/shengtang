package banyuan.club.service;

import banyuan.club.dao.IProductDao;
import banyuan.club.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {

    public  static void productSelectByName(IProductDao productDao) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要查询商品名称：");
        String name = sc.nextLine();
        List<Product> productList = new ArrayList<>();
        productList = productDao.getProductByName(name);
        for(Product product : productList){
            System.out.println(product);
        }

    }


}
