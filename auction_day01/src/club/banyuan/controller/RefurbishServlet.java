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
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RefurbishServlet", urlPatterns = "/refurbish.do")
public class RefurbishServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("flashProductId");
        Product product = new Product();
        ProductServiceDao productServiceDao = new ProductServiceDaoImpl();
        AuctionServiceDao auctionServiceDao = new AuctionServiceDaoImpl();

        List<Auction> auctionList = new ArrayList<>();
        try {
            product = productServiceDao.getProductById(id);
            session.setAttribute("product", product);
            auctionList = auctionServiceDao.getByProductId(product.getId());
            session.setAttribute("auctionList", auctionList);

            for (Auction auction : auctionList) {
                System.out.println(auction);
            }
            PrintWriter writer = response.getWriter();

            for (Auction auction : auctionList) {
                writer.print("<ul class=\"rows\">\n" +
                        "                    <li>"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(auction.getCreateTime())+"</li>\n" +
                        "                    <li>"+auction.getPrice()+"</li>\n" +
                        "                    <li class=\"borderno\">"+auction.getUesrName()+"</li>\n" +
                        "                </ul>");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
