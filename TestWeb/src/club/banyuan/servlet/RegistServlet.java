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

@WebServlet(name = "RegistServlet",urlPatterns = "/regist.do")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setLoginName(request.getParameter("loginName"));
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        String sex = request.getParameter("sex");
        if(sex.equals("男")){
            user.setSex(1);
        }else {
            user.setSex(0);
        }
        user.setEmail(request.getParameter("email"));
        user.setMobile(request.getParameter("mobile"));
        boolean flag = false;
        try {
            UserDao userDao = new UserDaoImpl(JdbcUtils.getConnection());
            int id = userDao.add(user);
            if(id>0){
                flag = true;
            }
            System.out.println(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(user);

        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<meta charset='utf-8'/>");
        writer.println("<body>");
        writer.println(flag==true?"<h1>注册成功</h1>" +
                "<a href='/TestWeb_war_exploded/Login.html'>返回登陆</a>":"<h1>注册失败</h1>" +
                "<a href='/TestWeb_war_exploded/Regist.html'>重新注册</a>");
        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
