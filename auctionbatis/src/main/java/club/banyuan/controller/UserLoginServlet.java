package club.banyuan.controller;

import club.banyuan.entity.Auction;
import club.banyuan.entity.Product;
import club.banyuan.entity.User;
import club.banyuan.service.UserServiceDao;
import club.banyuan.service.impl.UserServiceDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        Map map = new HashMap();
        map.put("userName", userName);
        map.put("password", password);
        UserServiceDao userServiceDao = new UserServiceDaoImpl();


        User user = null;
        HttpSession session = request.getSession();

            user = userServiceDao.login(map);
            if(user != null){
//                Integer isSelled = 0;//1售出 0 为售出
//                //查询正在出售中的商品
//                List<Product> updateNotSelled = new ArrayList<Product>();
//                updateNotSelled = productServiceDao.getProductBySell(isSelled);
//
//
//                //根据当前时间判断正在拍卖中的商品的结束时间是否结束来更改商品是否已经售出
//                Date date = new Date();
//                AuctionServiceDao auctionServiceDao = new AuctionServiceDaoImpl();
//                for (Product product : updateNotSelled) {
//                    if(date.after(product.getFinishTime())){
//                        Auction auction = new Auction();
//                        auction = auctionServiceDao.getProductHighPrice(product.getId());
//                        //判断商品的出价表 中的最高价是否 大于商品的 底价
//                        //如果大于 就 卖出， 如果不大于，就将日期自动延长1天
//                        if(auction != null && auction.getPrice()>=product.getBasePrice()){
//                            System.out.println(product.getName()+""+product.getFinishTime()+"超时间"+date);
//                            product.setIsSelled(1);
//                            System.out.println(product.getIsSelled());
//                            System.out.println(product);
//                            productServiceDao.updateProduct(product);
//                        }
//                        else {
//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
//                            Calendar cd = Calendar.getInstance();
//                            cd.setTime(sdf.parse(product.getFinishTime()));
//                            cd.add(Calendar.DATE, 1);//增加一天
//                            product.setFinishTime(cd.getTime());
//                            productServiceDao.updateProduct(product);
//                        }
//                    }
//                }

//                //最后再次查询正在出售中的商品
//                List<Product> productListNotSelled = new ArrayList<Product>();
//                productListNotSelled = productServiceDao.getProductBySell(isSelled);
//                for (Product product : productListNotSelled) {
//                    System.out.println(product.getName());
//                }
//                session.setAttribute("productListNotSelled", productListNotSelled);
                session.setAttribute("user", user);
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", user.getUserName());
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

        request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
