package club.banyuan.filter;

import club.banyuan.entity.Admin;
import club.banyuan.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        Admin admin = (Admin)session.getAttribute("admin");

        System.out.println("servletPath:"+request.getServletPath());
        String serletPath = request.getServletPath();

        if(serletPath.equals("/login.do")||serletPath.equals("/index.jsp")||serletPath.equals("/adminLogin.do")
        ||serletPath.equals("/adminLogin.jsp")||serletPath.equals("/login.jsp")){
            chain.doFilter(req, resp);
            return;
        }else if(admin == null && (serletPath.equals("/alter.do")||serletPath.equals("/alter.jsp")||
                serletPath.equals("/productControl.jsp")||serletPath.equals("/addProduct.jsp"))){
            request.getRequestDispatcher("adminLogin.jsp").forward(request, resp);
        }else {
            chain.doFilter(req, resp);
            return;
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
