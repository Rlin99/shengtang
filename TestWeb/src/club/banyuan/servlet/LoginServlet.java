package club.banyuan.servlet;

import club.banyuan.dao.UserDao;
import club.banyuan.dao.impl.UserDaoImpl;
import club.banyuan.entity.User;
import club.banyuan.util.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        boolean flag = false;
        try {
            UserDao userdao = new UserDaoImpl(JdbcUtils.getConnection());
            User user = new User();
            user = userdao.getLoginUser(loginName, password);
            if(user != null){
                flag = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<meta charset='utf-8'/>");
        writer.println("<body>");
        writer.println(flag==true?"<h2>登陆成功</h1>" +
                "<a href='/TestWeb_war_exploded/Sell.html'>进入主页</a>":"<h1>登陆失败</h1>" +
                "<a href='/TestWeb_war_exploded/Login.html'>返回登陆</a>");
        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
        writer.close();

    }
}
