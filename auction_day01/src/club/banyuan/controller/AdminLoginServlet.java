package club.banyuan.controller;

import club.banyuan.entity.Admin;
import club.banyuan.entity.Auction;
import club.banyuan.entity.Product;
import club.banyuan.service.AdminServiceDao;
import club.banyuan.service.AuctionServiceDao;
import club.banyuan.service.Impl.AdminServiceDaoImpl;
import club.banyuan.service.Impl.AuctionServiceDaoImpl;
import club.banyuan.service.Impl.ProductServiceDaoImpl;
import club.banyuan.service.ProductServiceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AdminLoginServlet", urlPatterns = "/adminLogin.do")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        AdminServiceDao adminServiceDao = new AdminServiceDaoImpl();
        HttpSession session = request.getSession();
        ProductServiceDao productServiceDao = new ProductServiceDaoImpl();

        Admin admin = null;
        System.out.println(userName+""+password);
        try {
            admin = adminServiceDao.login(userName, password);
            if(admin != null){
                Integer isSelled = 0;//1售出 0 为售出

                //查询正在出售中的商品
                List<Product> updateNotSelled = new ArrayList<Product>();
                updateNotSelled = productServiceDao.getProductBySell(isSelled);
                //根据当前时间判断正在拍卖中的商品的结束时间是否结束来更改商品是否已经售出
                Date date = new Date();
                AuctionServiceDao auctionServiceDao = new AuctionServiceDaoImpl();
                for (Product product : updateNotSelled) {
                    if(date.before(product.getFinishTime())){
                        Auction auction = new Auction();
                        auction = auctionServiceDao.getProductHighPrice(product.getId());
                        //判断商品的出价表 中的最高价是否 大于商品的 底价
                        //如果大于 就 卖出， 如果不大于，就将日期自动延长1天
                        if(auction != null && auction.getPrice()>=product.getBasePrice()){
                            product.setIsSelled(1);
                            productServiceDao.updateProduct(product);
                        }
//                        else {
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
//                            Calendar cd = Calendar.getInstance();
//                            cd.setTime(sdf.parse(product.getFinishTime().toString()));
//                            cd.add(Calendar.DATE, 1);//增加一天
//                            product.setFinishTime(cd.getTime());
//                            productServiceDao.updateProduct(product);
//                        }
                    }
                }


                List<Product> productListNotSelled = new ArrayList<Product>();
                productListNotSelled = productServiceDao.getProductBySell(isSelled);
                for (Product product : productListNotSelled) {
                    System.out.println(product.getName());
                }
                session.setAttribute("productListNotSelled", productListNotSelled);
                session.setAttribute("admin", admin);
                session.setAttribute("adminId", admin.getId());
                session.setAttribute("adminName", admin.getUserName());
                request.getRequestDispatcher("productControl.jsp").forward(request, response);
                return;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
