package club.banyuan.controller;

import club.banyuan.entity.Auction;
import club.banyuan.entity.Product;
import club.banyuan.service.AuctionServiceDao;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/auction.do")
public class AuctionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Product product = new Product();
        HttpSession session = request.getSession();
        ProductServiceDao productServiceDao = new ProductServiceDaoImpl();
        AuctionServiceDao auctionServiceDao = new AuctionServiceDaoImpl();

        List<Auction> auctionList = new ArrayList<>();
        try {
            product = productServiceDao.getProductById(id);
            session.setAttribute("product", product);
            auctionList = auctionServiceDao.getByProductId(product.getId());
            session.setAttribute("auctionList", auctionList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("auction.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
