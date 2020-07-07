package club.banyuan.controller;

import club.banyuan.service.UserService;
import club.banyuan.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ValidateLoginNameServlet", urlPatterns = "/validate.do")
public class ValidateLoginNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean result = true;
        String loginName = request.getParameter("loginName");
         if(loginName != null && loginName != ""){
             UserService userService = new UserServiceImpl();
             try {
                 if(userService.selectLoginName(loginName)==false){
                     result = false;
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
        PrintWriter writer = response.getWriter();
        writer.println(result);
        writer.flush();
        writer.close();
         System.out.println(loginName);
         System.out.println(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
